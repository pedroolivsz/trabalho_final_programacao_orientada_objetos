package com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.ComprovanteService;

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
