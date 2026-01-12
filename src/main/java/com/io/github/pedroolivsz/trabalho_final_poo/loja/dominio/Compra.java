package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Compra {
    private long idLoja;
    private final Fornecedor fornecedor;
    private final List<Produto> produtos;
    private LocalDate dataDaCompra;
    private BigDecimal valorDaCompra;
    private TipoPagamento tipoPagamento;
    private StatusCompra status;

    public Compra(Fornecedor fornecedor, List<Produto> produtos, TipoPagamento tipoPagamento) {
        this.fornecedor = fornecedor;
        this.produtos = produtos;
        this.tipoPagamento = tipoPagamento;
        this.dataDaCompra = LocalDate.now();
        this.status = StatusCompra.PENDENTE;
        calcularTotal();
    }

    public void calcularTotal() {
        this.valorDaCompra = BigDecimal.ZERO;
        for(Produto produto : produtos) {
            this.valorDaCompra = this.valorDaCompra.add(produto.getValorUnitario()
                    .multiply(BigDecimal.valueOf(produto.getQuantidade())));
        }
    }

    public void setIdLoja(long idLoja) {
        this.idLoja = idLoja;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorDaCompra() {
        return valorDaCompra;
    }

    public void finalizar() {
        this.status = StatusCompra.FINALIZADA;
    }
}
