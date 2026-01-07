package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public void cadastrarProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        produtoService.cadastrarProduto(nome, descricao, quantidade, valorUnitario);
    }

    public void subtrairQuantidadeProduto(String nome, int quantidade) {
        produtoService.subtrairQuantidade(nome, quantidade);
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoService.procurarPorNome(nome);
    }

    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
}
