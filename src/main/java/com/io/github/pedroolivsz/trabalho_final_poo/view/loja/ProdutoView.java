package com.io.github.pedroolivsz.trabalho_final_poo.view.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ProductValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoView {
    private final ProdutoController produtoController;

    public ProdutoView(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    public void tratarErro(ProductValidation productValidation) {
        if(productValidation != ProductValidation.SUCESSO) {
            MessageUtil.error(productValidation.getMessage(), "Erro");
        }
    }

    public void criarProduto() {
        String nome = InputUtil.lerString("Nome: ", "Cadastro de produtos");
        String descricao = InputUtil.lerString("Descrição: ", "Cadastro de produtos");
        int quantidade = InputUtil.lerInteiro("Quantidade: ", "Cadastro de produtos");
        BigDecimal valorUnitario = InputUtil.lerBigDecimal("Valor unitário: ", "Cadastro de produtos");
        ProductValidation productValidation = produtoController.criarProduto(nome, descricao, quantidade, valorUnitario);

        tratarErro(productValidation);
    }

    public void subtrairQuantidadeProduto() {
        String nome = InputUtil.lerString("Nome: ", "Informações do produto");
        int quantidade = InputUtil.lerInteiro("Quantidade: ", "Informações do produto");
        subtrairQuantidadeProduto(nome, quantidade);
    }

    public void subtrairQuantidadeProduto(String nome, int quantidade) {
        ProductValidation productValidation = produtoController.subtrairQuantidadeProduto(nome, quantidade);
        tratarErro(productValidation);
    }

    public void listarProdutos() {
        MessageUtil.plain(formatarListaProdutos(), "Produtos");
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoController.procurarProdutoPorNome(nome);
    }

    public String formatarListaProdutos() {
        return formatarListaCompletaProdutos(produtoController.listarProdutos());
    }

    public String formatarListaSimplesProdutos(List<Produto> produtos) {
        List<Produto> listaDeProdutos = produtos;

        if (listaDeProdutos.isEmpty()) {
            MessageUtil.error(listaVazia(), "Erro");
            return "Lista vazia";
        }

        StringBuilder stringLista = new StringBuilder();

        for (Produto produto : listaDeProdutos) {
            stringLista.append("│").append("Nome: ").append(produto.getNome()).append(" ")
                        .append("│").append("Qtd: ").append(produto.getQuantidade()).append(" ")
                        .append("│").append("Valor Un.: R$ ").append(produto.getValorUnitario())
                        .append("\n");
        }

        return stringLista.toString();
    }

    public String formatarListaCompletaProdutos(List<Produto> produtos) {
        List<Produto> listaDeProdutos = produtos;

        if (listaDeProdutos.isEmpty()) {
            MessageUtil.error(listaVazia(), "Erro");
            return "Lista vazia";
        }

        StringBuilder stringLista = new StringBuilder();

        for (Produto produto : listaDeProdutos) {
            stringLista.append("│").append("Código de barras: ").append(produto.getCodigoDeBarras()).append(" ")
                    .append("│").append("Nome: ").append(produto.getNome()).append(" ")
                    .append("│").append("Descrição: ").append(produto.getDescricao()).append(" ")
                    .append("│").append("Qtd: ").append(produto.getQuantidade()).append(" ")
                    .append("│").append("Valor Un.: R$ ").append(produto.getValorUnitario())
                    .append("\n");
        }

        return stringLista.toString();
    }

    private String listaVazia() {
        return """
                ┌──────────────────────────────────────────────────┐
                │A lista está vazia.
                └──────────────────────────────────────────────────┘""";
    }

    private String montarLista(String lista) {
        return """
                ┌──────────────────────────────────────────────────┐
                │Produtos em estoque
                └──────────────────────────────────────────────────┘
                %s
                └──────────────────────────────────────────────────┘""".formatted(lista);
    }
}