package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public enum StatusLoja {
    ATIVA(1),
    BLOQUEADA(2);

    private final int id;

    StatusLoja(int id) {
        this.id = id;
    }
}
