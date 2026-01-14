package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import java.math.BigDecimal;

public class ItemTransacao {
    private Produto produto;
    private int quantidade;
    private BigDecimal valorUnitario;

    public ItemTransacao(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getValorUnitario();
    }

    public BigDecimal getSubTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
