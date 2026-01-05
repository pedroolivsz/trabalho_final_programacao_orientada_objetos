package com.io.github.pedroolivsz.trabalho_final_poo.agencia.service;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.repository.ComprovanteRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.validation.ProofValidator;

import java.time.LocalDate;
import java.util.List;

public class ComprovanteService {
	
	private final ComprovanteRepository comprovanteRepository;
	
	public ComprovanteService(ComprovanteRepository comprovanteRepository) {
		this.comprovanteRepository = comprovanteRepository;
	}
	
	public Comprovante criarComprovante(Conta proprietario, String numeroContaDestinatario) {
        ProofValidator.validarDadosComprovante(proprietario, numeroContaDestinatario);

        LocalDate dataDeLancamento = LocalDate.now();

        Comprovante comprovante = new Comprovante(dataDeLancamento, proprietario, numeroContaDestinatario);
        comprovanteRepository.salvarComprovante(comprovante);

        return comprovante;
    }

    public List<Comprovante> listarComprovantes() {
        return comprovanteRepository.listarComprovantes();
    }

}
