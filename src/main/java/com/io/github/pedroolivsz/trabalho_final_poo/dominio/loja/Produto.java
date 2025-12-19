package com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja;

import java.math.BigDecimal;

public class Produto {
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
        return "Código de identificação: " + codigoDeBarras +
                "| Descrição: " + descricao +
                "| Preco: R$" + valorUnitario +
                "| Quantidade: " + quantidade;
    }
}
