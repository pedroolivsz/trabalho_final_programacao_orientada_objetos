package com.io.github.pedroolivsz.trabalho_final_poo.service.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PasswordUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ShopValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ShopValidator;

import java.util.List;

public class LojaService {
    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public ShopValidation salvarLoja(String nome, String localizacao, String cnpj, String senha) {
        ShopValidation validation = validarCadastro(nome, localizacao, cnpj, senha);

        if(validation != ShopValidation.SUCESSO) return validation;

        if(existeCnpj(cnpj)) return ShopValidation.CNPJ_JA_CADASTRADO;

        lojaRepository.salvarLoja(criarLoja(nome, localizacao, cnpj, senha));
        return ShopValidation.SUCESSO;
    }

    public List<Loja> listarLojas() {
        return lojaRepository.listarLojas();
    }

    public ShopValidation login(String cnpj, String senhaDigitada) {
        Loja lojaProcurada = procurarPorCNPJ(cnpj);
        if(lojaProcurada == null) return ShopValidation.CNPJ_INVALIDO;

        return validarSenha(lojaProcurada, senhaDigitada);
    }

    public Loja procurarPorCNPJ(String cnpj) {
        return lojaRepository.procurarPorCNPJ(cnpj);
    }

    private ShopValidation validarSenha(Loja lojaProcurada, String senhaDigitada) {
        if(!PasswordUtil.verificarSenha(senhaDigitada, lojaProcurada.getSenha())) return ShopValidation.SENHA_INVALIDA;

        return ShopValidation.SUCESSO;
    }

    private ShopValidation validarCadastro(String nome, String localizacao, String cnpj, String senha) {
        return ShopValidator.validar(nome, localizacao, cnpj, senha);
    }

    private boolean existeCnpj(String cnpj) {
        return procurarPorCNPJ(cnpj) != null;
    }

    private Loja criarLoja(String nome, String localizacao, String cnpj, String senha) {
        String hash = PasswordUtil.gerarHash(senha);
        return new Loja(nome, localizacao, cnpj, hash);
    }
}
