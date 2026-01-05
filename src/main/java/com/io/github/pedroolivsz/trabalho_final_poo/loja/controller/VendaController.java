package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import java.math.BigDecimal;
import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.VendaService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidation;

public class VendaController {
	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		this.vendaService = vendaService;
	}
	
	public VendaValidation salvarVenda(List<Produto> produtos, TipoPagamento tipoPagamento) {
		return vendaService.salvarVenda(produtos, tipoPagamento);
	}

    public BigDecimal calcularValorTotal(List<Produto> cesta) {
        return vendaService.calcularValorTotalDaCesta(cesta);
    }

    public BigDecimal calcularValorTotalDeVendas() {
        return vendaService.calcularValorTotalDeVendas();
    }

    public boolean adicionarProduto(List<Produto> produtos, String nome, int quantidade) {
        return vendaService.adicionarProduto(produtos, nome, quantidade);
    }

    public void removerProdutosVendidos(List<Produto> cesta) {
        vendaService.removerProdutosVendidos(cesta);
    }
	
	public List<Venda> listarVendas() {
		return vendaService.listarVendas();
	}
}
