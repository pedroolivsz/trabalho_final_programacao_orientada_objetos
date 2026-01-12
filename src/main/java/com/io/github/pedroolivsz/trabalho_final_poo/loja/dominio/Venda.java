package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Venda {

    private long idLoja;
	private List<Produto> listaDeCompras;
	private TipoPagamento tipoDePagamento;
	private LocalDate dataDaVenda;
    private BigDecimal valorTotal;

    public Venda() {
	}

	public Venda(List<Produto> listaDeCompras, TipoPagamento tipoDePagamento,
			BigDecimal valorTotal) {
		super();
		this.listaDeCompras = listaDeCompras;
		this.tipoDePagamento = tipoDePagamento;
		this.dataDaVenda = LocalDate.now();
		this.valorTotal = valorTotal;
	}

    public void setIdLoja(long idLoja) {
        this.idLoja = idLoja;
    }

    public List<Produto> getListaDeCompras() {
		return listaDeCompras;
	}

	public void setListaDeCompras(List<Produto> listaDeCompras) {
		this.listaDeCompras = listaDeCompras;
	}

	public TipoPagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoPagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public LocalDate getDataDaVenda() {
		return dataDaVenda;
	}

	public void setDataDaVenda(LocalDate dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
