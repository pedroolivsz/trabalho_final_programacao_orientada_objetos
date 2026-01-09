package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public enum StatusCompra {
    PENDENTE(1),
    FINALIZADA(2),
    CANCELADA(3);

    private final int id;

    StatusCompra(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
