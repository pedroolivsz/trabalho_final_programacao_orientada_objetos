package com.io.github.pedroolivsz.trabalho_final_poo.validation.loja;

public enum ShopValidation {
    NOME_INVALIDO("Nome inválido"),
    LOCALIZACAO_INVALIDA("Localização inválida"),
    CNPJ_INVALIDO("CNPJ inválido"),
    CNPJ_JA_CADASTRADO("CNPJ já cadastrado"),
    SENHA_INVALIDA("Senha inválida"),
    SUCESSO("");

    private final String message;

    ShopValidation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
