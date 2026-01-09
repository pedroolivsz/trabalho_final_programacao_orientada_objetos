package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import java.math.BigDecimal;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.VendaService;

public class VendaController {
	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		this.vendaService = vendaService;
	}
	
	public void realizarVenda(Carrinho carrinho, TipoPagamento tipoPagamento, Loja loja) {
		vendaService.realizarVenda(carrinho, tipoPagamento, loja);
	}

    public BigDecimal calcularValorTotalDeVendas() {
        return vendaService.calcularValorTotalDeVendas();
    }

    public void adicionarProduto(long idLoja, Carrinho carrinho, String nome, int quantidade) {
        vendaService.adicionarProdutoAoCarrinho(idLoja, carrinho, nome, quantidade);
    }
}
