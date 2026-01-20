package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.CodigoDeBarras;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ProductValidator;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CodigoDeBarras codigoDeBarras;

    public ProdutoService(ProdutoRepository produtoRepository, CodigoDeBarras codigoDeBarras) {
        this.produtoRepository = produtoRepository;
        this.codigoDeBarras = codigoDeBarras;
    }

    public void cadastrarProduto(long idLoja, String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        ProductValidator.validarDadosDoProduto(nome, descricao, quantidade, valorUnitario);
        validarRegrasDeNegocio(idLoja, nome);

        Produto produto = criarProdutoPadrao(nome, descricao, quantidade, valorUnitario);

        String codigoDeBarras = this.codigoDeBarras.gerarCodigoDeBarras();
        produto.setIdLoja(idLoja);
        produto.setCodigoDeBarras(codigoDeBarras);

        produtoRepository.salvarProduto(produto);
    }

    public void subtrairProdutosVendidosDoEstoque(long idLoja, String nome, int quantidade) {
        ProductValidator.validarQuantidade(quantidade);
        ProductValidator.validarNome(nome);

        Produto item = procurarPorNome(idLoja, nome);

        ProductValidator.validarExistenciaDeProduto(item);

        if(item.getQuantidade() < quantidade) throw new ProductValidationException("Estoque insuficiente");

        item.setQuantidade(item.getQuantidade() - quantidade);

        produtoRepository.editarQuantidadeProduto(item);
    }

    public void adicionarEstoque(long idComprador, long idFornecedor, String nome, int quantidade) {
        ProductValidator.validarQuantidade(quantidade);
        ProductValidator.validarNome(nome);

        Produto produtoLoja = procurarPorNome(idComprador, nome);
        Produto produtoComprado;

        if(produtoLoja != null) {
            produtoLoja.setQuantidade(produtoLoja.getQuantidade() + quantidade);

            produtoRepository.editarQuantidadeProduto(produtoLoja);
        } else if(produtoLoja == null) {
            Produto produtoFornecedor = procurarPorNome(idFornecedor, nome);

            ProductValidator.validarExistenciaDeProduto(produtoFornecedor);

            produtoComprado = new Produto(produtoFornecedor.getNome(),
                    produtoFornecedor.getDescricao(), quantidade, produtoFornecedor.getValorUnitario());

            produtoComprado.setCodigoDeBarras(produtoFornecedor.getCodigoDeBarras());
            produtoComprado.setIdLoja(idComprador);

            produtoRepository.salvarProduto(produtoComprado);
        }
    }

    public void editarProduto(Produto produto, String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        ProductValidator.validarExistenciaDeProduto(produto);
        ProductValidator.validarDadosDoProduto(nome, descricao, quantidade, valorUnitario);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setValorUnitario(valorUnitario);
    }

    public void excluirProduto(Produto produto) {
        ProductValidator.validarExistenciaDeProduto(produto);
        produtoRepository.removerProduto(produto);
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
