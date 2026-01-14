package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.ItemTransacao;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoView {
    private final ProdutoController produtoController;

    public ProdutoView(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    public void cadastrarProduto(long idLoja) {
        try {
            String nome = InputUtil.lerString("Nome: ", "Cadastro de produtos");
            String descricao = InputUtil.lerString("Descrição: ", "Cadastro de produtos");
            int quantidade = InputUtil.lerInteiro("Quantidade: ", "Cadastro de produtos");
            BigDecimal valorUnitario = InputUtil.lerBigDecimal("Valor unitário: ", "Cadastro de produtos");

            produtoController.cadastrarProduto(idLoja, nome, descricao, quantidade, valorUnitario);
        } catch (ProductValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao cadastrar produto");
        }
    }

    public void subtrairQuantidadeProduto(long idLoja) {
        String nome = InputUtil.lerString("Nome: ", "Informações do produto");
        int quantidade = InputUtil.lerInteiro("Quantidade: ", "Informações do produto");
        subtrairQuantidadeProduto(idLoja, nome, quantidade);
    }

    public void subtrairQuantidadeProduto(long idLoja, String nome, int quantidade) {
        try {
            produtoController.subtrairQuantidadeProduto(idLoja, nome, quantidade);
        } catch (ProductValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro");
        }
    }

    public void listarProdutos(long idLoja) {
        MessageUtil.plain(formatarListaProdutos(idLoja), "Produtos");
    }

    public Produto procurarProdutoPorNome(long idLoja, String nome) {
        return produtoController.procurarProdutoPorNome(idLoja, nome);
    }

    public String formatarListaProdutos(long idLoja) {
        return formatarListaCompletaProdutos(produtoController.listarProdutos(idLoja));
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

    public String formatarListaSimplesItensTransacao(List<ItemTransacao> produtos) {

        if (produtos.isEmpty()) {
            MessageUtil.error(listaVazia(), "Erro");
            return "Lista vazia";
        }

        StringBuilder stringLista = new StringBuilder();

        for (ItemTransacao item : produtos) {
            stringLista.append("│").append("Nome: ").append(item.getProduto().getNome()).append(" ")
                    .append("│").append("Qtd: ").append(item.getQuantidade()).append(" ")
                    .append("│").append("Valor Un.: R$ ").append(item.getValorUnitario())
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