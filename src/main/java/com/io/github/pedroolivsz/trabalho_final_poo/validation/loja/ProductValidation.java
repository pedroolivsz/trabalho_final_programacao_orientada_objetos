package com.io.github.pedroolivsz.trabalho_final_poo.validation.loja;

public enum ProductValidation {
    NOME_INVALIDO("Nome inválido"),
    DESCRICAO_INVALIDA("Descrição inválida"),
    QUANTIDADE_INVALIDA("Quantidade inválida"),
    SUBTRACAO_INVALIDA("Estoque insuficiente"),
    PRECO_INVALIDO("Preço inválido"),
    SUCESSO("");

    private final String message;

    ProductValidation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
