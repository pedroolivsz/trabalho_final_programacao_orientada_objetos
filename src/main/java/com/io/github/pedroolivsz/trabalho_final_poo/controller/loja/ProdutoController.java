package com.io.github.pedroolivsz.trabalho_final_poo.controller.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.service.loja.ProdutoService;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ProductValidation;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public ProductValidation criarProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        return produtoService.criarProduto(nome, descricao, quantidade, valorUnitario);
    }

    public ProductValidation subtrairQuantidadeProduto(String nome, int quantidade) {
        return produtoService.subtrairQuantidade(nome, quantidade);
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoService.procurarProdutoPorNome(nome);
    }

    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
}
