package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public enum StatusLoja {
    ATIVA(1),
    DESATIVADA(2),
    PENDENTE(3),
    BLOQUEADA(4);

    private final int id;

    StatusLoja(int id) {
        this.id = id;
    }
}
