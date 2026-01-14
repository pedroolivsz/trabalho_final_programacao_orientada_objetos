package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public enum StatusTransacao {
    PENDENTE(1),
    FINALIZADA(2),
    CANCELADA(3);

    private final int id;

    StatusTransacao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
