package com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia;

import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Comprovante;

public class ComprovanteRepository {
	
	private final List<Comprovante> comprovantes;

	public ComprovanteRepository(List<Comprovante> comprovantes) {
		this.comprovantes = comprovantes;
	}
	
	public void salvarComprovante(Comprovante comprovante) {
		comprovantes.add(comprovante);
	}
	
	public List<Comprovante> listarComprovantes() {
		return comprovantes;
	}

}
