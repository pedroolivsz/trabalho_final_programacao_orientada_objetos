package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.regex.ValidadorEmail;
import com.io.github.pedroolivsz.trabalho_final_poo.regex.ValidadorTelefone;
import com.io.github.pedroolivsz.trabalho_final_poo.regex.ValiladorNumeroRua;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PadronizarDadosUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PasswordUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;

import java.math.BigDecimal;
import java.util.List;

public class LojaService {
    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public void salvarLoja(String nome,
                           String cnpj,
                           String cep,
                           String estado,
                           String cidade,
                           String bairro,
                           String rua,
                           String numero,
                           Categoria categoria,
                           String telefone,
                           String email,
                           String senha) {

        nome = PadronizarDadosUtil.normalizarTextoMaiusculo(nome);
        cnpj = PadronizarDadosUtil.normalzarCnpj(cnpj);
        cep = PadronizarDadosUtil.normalzarCep(cep);
        estado = PadronizarDadosUtil.normalizarTextoMaiusculo(estado);
        cidade = PadronizarDadosUtil.normalizarTextoMaiusculo(cidade);
        bairro = PadronizarDadosUtil.normalizarTextoMaiusculo(bairro);
        rua = PadronizarDadosUtil.normalizarTextoMaiusculo(rua);
        numero = PadronizarDadosUtil.normalizarNumeroDaRua(numero);
        telefone = PadronizarDadosUtil.normalzarTelefone(telefone);
        email = PadronizarDadosUtil.normalizarEmail(email);

        validarDados(nome, cnpj, cep, estado, cidade, bairro, rua, numero, telefone, email, senha);
        validarRegrasDeNegocio(cnpj, email, telefone);

        Endereco endereco = criarEnderecoPadrao(cep, estado, cidade, bairro, rua, numero);
        Contatos contatos = criarContatosPadrao(telefone, email);

        Loja loja = criarLojaPadrao(nome, cnpj, endereco, categoria, contatos, senha);

        lojaRepository.salvarLoja(loja);
    }

    private Endereco criarEnderecoPadrao(String cep,
                                         String estado,
                                         String cidade,
                                         String bairro,
                                         String rua,
                                         String numero) {
        return new Endereco(cep, estado, cidade, bairro, rua, numero);
    }

    private Contatos criarContatosPadrao(String telefone, String email) {
        telefone = telefone.replaceAll("[^0-9]", "");
        return new Contatos(telefone, email);
    }

    private Loja criarLojaPadrao(String nome,
                                 String cnpj,
                                 Endereco endereco,
                                 Categoria categoria,
                                 Contatos contatos,
                                 String senha) {
        String hash = PasswordUtil.gerarHash(senha);
        return new Loja(nome, cnpj, endereco, categoria, contatos, StatusLoja.ATIVA, hash, BigDecimal.ZERO);
    }

    public List<Loja> listarLojas() {
        return lojaRepository.listarLojas();
    }

    public void login(String cnpj, String senhaDigitada) {
        Loja lojaProcurada = procurarPorCNPJ(cnpj);

        ShopValidator.validarExistenciaDeLoja(lojaProcurada);

        if(!PasswordUtil.verificarSenha(senhaDigitada, lojaProcurada.getSenha())) throw new ShopValidationException("Senha inválida");
    }

    private void validarDados(String nome,
                              String cnpj,
                              String cep,
                              String estado,
                              String cidade,
                              String bairro,
                              String rua,
                              String numero,
                              String telefone,
                              String email,
                              String senha) {
        ShopValidator.validarDadosDaLoja(nome, cnpj, senha);
        ShopValidator.validarDadosDeEndereco(cep, estado, cidade, bairro, rua, numero);
        ShopValidator.validarDadosDeContato(telefone, email);

        if(!ValidadorTelefone.validarTelefone(telefone)) throw new ShopValidationException("Número de telefone inválido");
        if(!ValidadorEmail.validarEmail(email)) throw new ShopValidationException("E-mail inválido");
        if(!ValiladorNumeroRua.validarNumeroDaRua(numero)) throw new ShopValidationException("Número da rua inválido");
    }

    private void validarRegrasDeNegocio(String cnpj, String email, String telefone) {
        if(existeCnpj(cnpj)) throw new ShopValidationException("CNPJ já cadastrado");
        if(existeEmail(email)) throw new ShopValidationException("E-mail já cadastrado");
        if(existeTelefone(telefone)) throw new ShopValidationException("Telefone já cadastrado");
    }

    public Loja procurarPorCNPJ(String cnpj) {
        return lojaRepository.procurarPorCNPJ(cnpj);
    }

    public Loja procurarPorEmail(String email) {
        return lojaRepository.procurarPorEmail(email);
    }

    public Loja procurarPorTelefone(String telefone) {
        return lojaRepository.procurarPorTelefone(telefone);
    }

    private boolean existeCnpj(String cnpj) {
        return procurarPorCNPJ(cnpj) != null;
    }

    private boolean existeEmail(String email) {
        return procurarPorEmail(email) != null;
    }

    private boolean existeTelefone(String telefone) {
        return procurarPorTelefone(telefone) != null;
    }
}
