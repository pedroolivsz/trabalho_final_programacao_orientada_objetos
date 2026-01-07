package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.regex.ValidadorTelefone;
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

        ShopValidator.validarDadosDaLoja(nome, cnpj, senha);
        ShopValidator.validarDadosDeEndereco(cep, estado, cidade, bairro, rua, numero);
        ShopValidator.validarDadosDeContato(telefone, email);

        if(existeCnpj(cnpj)) throw new ShopValidationException("CNPJ já cadastrado");
        if(existeEmail(email)) throw new ShopValidationException("E-mail já cadastrado");
        if(existeTelefone(telefone)) throw new ShopValidationException("Telefone já cadastrado");

        if(!ValidadorTelefone.validarTelefone(telefone)) throw new ShopValidationException("Número de telefone inválido");

        Endereco enderecoDaLoja = new Endereco(cep, estado, cidade, bairro, rua, numero);

        telefone = telefone.replaceAll("[^0-9]", "");
        Contatos contatosDaLoja = new Contatos(telefone, email);

        StatusLoja statusDaLoja = StatusLoja.ATIVA;
        BigDecimal caixa = BigDecimal.ZERO;

        lojaRepository.salvarLoja(criarLoja(nome,
                                            cnpj,
                                            enderecoDaLoja,
                                            categoria,
                                            contatosDaLoja,
                                            statusDaLoja,
                                            senha,
                                            caixa));
    }

    private Loja criarLoja(String nome,
                           String cnpj,
                           Endereco endereco,
                           Categoria categoria,
                           Contatos contatos,
                           StatusLoja status,
                           String senha,
                           BigDecimal caixa) {
        String hash = PasswordUtil.gerarHash(senha);
        return new Loja(nome, cnpj, endereco, categoria, contatos, status, hash, caixa);
    }

    public List<Loja> listarLojas() {
        return lojaRepository.listarLojas();
    }

    public void login(String cnpj, String senhaDigitada) {
        Loja lojaProcurada = procurarPorCNPJ(cnpj);

        ShopValidator.validarExistenciaDeLoja(lojaProcurada);

        if(!PasswordUtil.verificarSenha(senhaDigitada, lojaProcurada.getSenha())) throw new ShopValidationException("Senha inválida");
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
