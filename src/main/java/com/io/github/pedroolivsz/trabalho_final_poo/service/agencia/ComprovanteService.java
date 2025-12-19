package com.io.github.pedroolivsz.trabalho_final_poo.service.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia.ComprovanteRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.agencia.ProofValidator;

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
