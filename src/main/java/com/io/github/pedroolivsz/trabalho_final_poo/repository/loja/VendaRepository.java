package com.io.github.pedroolivsz.trabalho_final_poo.repository.loja;

import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Venda;

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
