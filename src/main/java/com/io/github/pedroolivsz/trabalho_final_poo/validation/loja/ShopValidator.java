package com.io.github.pedroolivsz.trabalho_final_poo.validation.loja;

public class ShopValidator {
    public static ShopValidation validar(String nome, String localizacao, String CNPJ, String senha) {
        if(nome == null || nome.isBlank()) { return ShopValidation.NOME_INVALIDO; }
        if(localizacao == null || localizacao.isBlank()) { return ShopValidation.LOCALIZACAO_INVALIDA; }
        if(CNPJ == null || CNPJ.isBlank()) { return ShopValidation.CNPJ_INVALIDO; }
        if(senha == null || senha.isBlank()) { return ShopValidation.SENHA_INVALIDA; }

        return ShopValidation.SUCESSO;
    }
}
