package com.io.github.pedroolivsz.trabalho_final_poo.service.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ProductValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ProductValidator;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CodigoDeBarrasUtil codigoDeBarrasUtil;

    public ProdutoService(ProdutoRepository produtoRepository, CodigoDeBarrasUtil codigoDeBarrasUtil) {
        this.produtoRepository = produtoRepository;
        this.codigoDeBarrasUtil = codigoDeBarrasUtil;
    }

    public ProductValidation criarProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        ProductValidation validation = ProductValidator.validarProduto(nome, descricao, quantidade, valorUnitario);

        if(validation != ProductValidation.SUCESSO) {
            return validation;
        }

        Produto produto = new Produto(nome, descricao, quantidade, valorUnitario);

        String codigoDeBarras = codigoDeBarrasUtil.gerarCodigoDeBarras();
        produto.setCodigoDeBarras(codigoDeBarras);

        produtoRepository.criarProduto(produto);

        return ProductValidation.SUCESSO;
    }

    public ProductValidation subtrairQuantidade(String nome, int quantidade) {
        ProductValidation validarQuantidade = ProductValidator.validarQuantidade(quantidade);

        if(validarQuantidade != ProductValidation.SUCESSO) {
            return validarQuantidade;
        }

        Produto produtoEditado = procurarProdutoPorNome(nome);

        if(produtoEditado == null) {
            return ProductValidation.NOME_INVALIDO;
        }

        if(produtoEditado.getQuantidade() < quantidade) {
            return ProductValidation.SUBTRACAO_INVALIDA;
        }

        produtoEditado.setQuantidade(produtoEditado.getQuantidade() - quantidade);

        produtoRepository.editarProduto(produtoEditado);

        return ProductValidation.SUCESSO;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarProdutos();
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoRepository.procurarProdutoPorNome(nome);
    }
}
