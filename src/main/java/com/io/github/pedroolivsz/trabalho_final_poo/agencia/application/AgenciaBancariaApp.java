package com.io.github.pedroolivsz.trabalho_final_poo.agencia.application;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller.AgenciaBancariaController;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller.ComprovanteController;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.repository.AgenciaBancariaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.repository.ComprovanteRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.AgenciaBancariaService;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.ComprovanteService;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.view.AgenciaBancariaView;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.view.ComprovanteView;

import java.util.ArrayList;
import java.util.List;

public class AgenciaBancariaApp {
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        List<Comprovante> comprovantes = new ArrayList<>();

        ComprovanteRepository comprovanteRepository = new ComprovanteRepository(comprovantes);
        ComprovanteService comprovanteService = new ComprovanteService(comprovanteRepository);
        ComprovanteController comprovanteController = new ComprovanteController(comprovanteService);
        ComprovanteView comprovanteView = new ComprovanteView(comprovanteController);

        AgenciaBancariaRepository agenciaBancariaRepository = new AgenciaBancariaRepository(contas);
        AgenciaBancariaService agenciaBancariaService = new AgenciaBancariaService(agenciaBancariaRepository);
        agenciaBancariaService.criarConta("João Pedro", "123.456.789-10", "Ipu-CE", "jp", "dede");
        agenciaBancariaService.criarConta("Debora Medeiros", "109.876.543-21", "Pires Ferreira-CE", "dede", "jp");
        AgenciaBancariaController agenciaBancariaController = new AgenciaBancariaController(agenciaBancariaService);
        AgenciaBancariaView agenciaBancariaView = new AgenciaBancariaView(agenciaBancariaController, comprovanteView);

        agenciaBancariaView.menuInicial();
    }
}
