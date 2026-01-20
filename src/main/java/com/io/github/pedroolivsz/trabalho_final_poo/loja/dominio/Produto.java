package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;

import java.math.BigDecimal;

public class Produto {
    private long idLoja;
    private String codigoDeBarras;
    private String nome;
    private String descricao;
    private int quantidade;
    private BigDecimal valorUnitario;

    public Produto() {
    }

    public Produto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public void baixarEstoque(int quantidade) {
        if(quantidade > this.quantidade) throw new ProductValidationException("Estoque insuficiente");
        this.quantidade -= quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(long idLoja) {
        this.idLoja = idLoja;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código de barras: " + codigoDeBarras +
                "| Nome: " + nome;
    }
}
