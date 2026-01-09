package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;
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

    public void cadastrarProduto(long idLoja, String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        ProductValidator.validarDadosDoProduto(nome, descricao, quantidade, valorUnitario);
        validarRegrasDeNegocio(idLoja, nome);

        Produto produto = criarProdutoPadrao(nome, descricao, quantidade, valorUnitario);

        String codigoDeBarras = codigoDeBarrasUtil.gerarCodigoDeBarras();
        produto.setIdLoja(idLoja);
        produto.setCodigoDeBarras(codigoDeBarras);

        produtoRepository.salvarProduto(produto);
    }

    public void adicionarProodutoAoEstoque(Produto produto) {
        produtoRepository.salvarProduto(produto);
    }

    public void subtrairQuantidade(long idLoja, String nome, int quantidade) {
        ProductValidator.validarQuantidade(quantidade);
        ProductValidator.validarNome(nome);

        Produto produtoEditado = procurarPorNome(idLoja, nome);

        ProductValidator.validarExistenciaDeProduto(produtoEditado);

        if(produtoEditado.getQuantidade() < quantidade) throw new ProductValidationException("Estoque insuficiente");

        produtoEditado.setQuantidade(produtoEditado.getQuantidade() - quantidade);

        produtoRepository.editarProduto(produtoEditado);
    }

    public List<Produto> listarProdutos(long idLoja) {
        return produtoRepository.listarProdutos(idLoja);
    }

    private Produto criarProdutoPadrao(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        return new Produto(nome, descricao, quantidade, valorUnitario);
    }

    private void validarRegrasDeNegocio(long idLoja, String nome) {
        if(existeProduto(idLoja, nome)) throw new ProductValidationException("O produto já está cadastrado");
    }

    public Produto procurarPorNome(long idLoja, String nome) {
        return produtoRepository.procurarPorNome(idLoja, nome);
    }

    private boolean existeProduto(long idLoja, String nome) {
        return procurarPorNome(idLoja, nome) != null;
    }
}
