package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Compra {
    private String id;
    private Fornecedor fornecedor;
    private List<Produto> produtos;
    private LocalDate dataDaCompra;
    private BigDecimal valorDaCompra;
    private StatusCompra status;

    public Compra(Fornecedor fornecedor, List<Produto> produtos) {
        this.fornecedor = fornecedor;
        this.produtos = produtos;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorDaCompra() {
        return valorDaCompra;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public void finalizar() {
        this.status = StatusCompra.FINALIZADA;
    }
}
