package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ProductValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ProductValidator;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CodigoDeBarrasUtil codigoDeBarrasUtil;

    public ProdutoService(ProdutoRepository produtoRepository, CodigoDeBarrasUtil codigoDeBarrasUtil) {
        this.produtoRepository = produtoRepository;
        this.codigoDeBarrasUtil = codigoDeBarrasUtil;
    }

    public void cadastrarProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        ProductValidator.validarDadosDoProduto(nome, descricao, quantidade, valorUnitario);

        Produto produto = new Produto(nome, descricao, quantidade, valorUnitario);

        String codigoDeBarras = codigoDeBarrasUtil.gerarCodigoDeBarras();
        produto.setCodigoDeBarras(codigoDeBarras);

        produtoRepository.cadastrarProduto(produto);
    }

    public void subtrairQuantidade(String nome, int quantidade) {
        ProductValidator.validarQuantidade(quantidade);
        ProductValidator.validarNome(nome);

        Produto produtoEditado = procurarProdutoPorNome(nome);

        ProductValidator.validarExistenciaDeProduto(produtoEditado);

        if(produtoEditado.getQuantidade() < quantidade) throw new ProductValidationException("Estoque insuficiente");

        produtoEditado.setQuantidade(produtoEditado.getQuantidade() - quantidade);

        produtoRepository.editarProduto(produtoEditado);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarProdutos();
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoRepository.procurarProdutoPorNome(nome);
    }
}
