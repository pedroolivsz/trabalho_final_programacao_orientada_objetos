package com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.service.agencia.ComprovanteService;

import java.util.List;

public class ComprovanteController {

    private final ComprovanteService comprovanteService;

    public ComprovanteController(ComprovanteService comprovanteService) {
        this.comprovanteService = comprovanteService;
    }

    public Comprovante criarComprovante(Conta proprietario, String numeroContaDestinatario) {
        return comprovanteService.criarComprovante(proprietario, numeroContaDestinatario);
    }

    public List<Comprovante> listarComprovantes() {
        return comprovanteService.listarComprovantes();
    }
}
