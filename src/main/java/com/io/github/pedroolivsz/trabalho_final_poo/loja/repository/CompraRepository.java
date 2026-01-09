package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Compra;

import java.util.List;

public class CompraRepository {
    private final List<Compra> compras;

    public CompraRepository(List<Compra> compras) {
        this.compras = compras;
    }

    public void salvarCompra(Compra compra) {
        compras.add(compra);
    }
}
