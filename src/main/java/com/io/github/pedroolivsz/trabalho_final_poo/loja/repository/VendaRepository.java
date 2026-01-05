package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Venda;

public class VendaRepository {
	private final List<Venda> vendas;

	public VendaRepository(List<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public void salvarVenda(Venda venda) {
		vendas.add(venda);
	}
	
	public List<Venda> listarVendas() {
		return vendas;
	}

}
