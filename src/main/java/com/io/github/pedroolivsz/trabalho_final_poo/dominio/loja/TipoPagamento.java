package com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja;

public enum TipoPagamento {
	CARTAO_CREDITO,
	CARTAO_DEBITO,
	PIX;

    public static TipoPagamento fromOpcao(int opcao) {
        return switch(opcao) {
            case 1 -> PIX;
            case 2 -> CARTAO_DEBITO;
            case 3 -> CARTAO_DEBITO;
            default -> null;
        };
    }
}
