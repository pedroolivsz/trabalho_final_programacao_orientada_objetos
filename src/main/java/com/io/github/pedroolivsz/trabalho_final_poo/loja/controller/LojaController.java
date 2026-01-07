package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Categoria;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.LojaService;

public class LojaController {
	private final LojaService lojaService;

	public LojaController(LojaService lojaService) {
		this.lojaService = lojaService;
	}
	
	public void salvarLoja(String nome,
                           String cnpj,
                           String cep,
                           String estado,
                           String cidade,
                           String bairro,
                           String rua,
                           String numero,
                           Categoria categoria,
                           String telefone,
                           String email,
                           String senha) {
		lojaService.salvarLoja(nome, cnpj, cep, estado, cidade, bairro, rua, numero, categoria, telefone, email, senha);
	}
	
	public void login(String CNPJ, String senha) {
		lojaService.login(CNPJ, senha);
	}
	
	public List<Loja> listarLojas() {
		return lojaService.listarLojas();
	}

    public Loja procuraPorCnpj(String CNPJ) {
        return lojaService.procurarPorCNPJ(CNPJ);
    }
}
