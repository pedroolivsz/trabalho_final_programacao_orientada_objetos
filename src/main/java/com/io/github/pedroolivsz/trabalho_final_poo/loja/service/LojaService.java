package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PasswordUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;

import java.util.List;

public class LojaService {
    private final LojaRepository lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public void salvarLoja(String nome, String localizacao, String cnpj, String senha) {
        ShopValidator.validarDadosDaLoja(nome, localizacao, cnpj, senha);

        if(existeCnpj(cnpj)) throw new ShopValidationException("CNPJ já cadastrado");

        lojaRepository.salvarLoja(criarLoja(nome, localizacao, cnpj, senha));
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

    private boolean existeCnpj(String cnpj) {
        return procurarPorCNPJ(cnpj) != null;
    }

    private Loja criarLoja(String nome, String localizacao, String cnpj, String senha) {
        String hash = PasswordUtil.gerarHash(senha);
        return new Loja(nome, localizacao, cnpj, hash);
    }
}
