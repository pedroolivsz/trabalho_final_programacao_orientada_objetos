package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public enum Categoria {
    VESTAURIO(1),
    ELETRONICOS(2),
    ALIMENTOS(3);

    private final int id;

    private Categoria(int id) {
        this.id = id;
    }
}