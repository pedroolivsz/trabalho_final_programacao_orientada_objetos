package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

public enum VendaValidation {
	LISTA_VAZIA("Lista vazia"),
	TIPO_DE_PAGAMENTO_INVALIDO("Tipo de pagamento inválido"),
	SUCESSO("");

    private final String message;

    VendaValidation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
