package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;

public class ShopValidator {
    public static void validarDadosDaLoja(String nome, String localizacao, String CNPJ, String senha) {
        if(nome == null || nome.isBlank()) throw new ShopValidationException("Nome inválido");
        if(localizacao == null || localizacao.isBlank()) throw new ShopValidationException("Localização inválida");
        if(CNPJ == null || CNPJ.isBlank()) throw new ShopValidationException("CNPJ inválido");
        if(senha == null || senha.isBlank()) throw new ShopValidationException("Senha inválida");
    }

    public static void validarExistenciaDeLoja(Loja loja) {
        if(loja == null) {
            throw new ShopValidationException("CNPJ inválido");
        }
    }
}
