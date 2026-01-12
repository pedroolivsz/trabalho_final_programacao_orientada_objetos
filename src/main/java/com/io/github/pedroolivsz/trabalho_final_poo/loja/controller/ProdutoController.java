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

    public void cadastrarProduto(long idLoja, String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        produtoService.cadastrarProduto(idLoja, nome, descricao, quantidade, valorUnitario);
    }

    public void subtrairQuantidadeProduto(long idLoja, String nome, int quantidade) {
        produtoService.subtrairProdutosVendidosDoEstoque(idLoja, nome, quantidade);
    }

    public Produto procurarProdutoPorNome(long idLoja, String nome) {
        return produtoService.procurarPorNome(idLoja, nome);
    }

    public List<Produto> listarProdutos(long idLoja) {
        return produtoService.listarProdutos(idLoja);
    }
}
