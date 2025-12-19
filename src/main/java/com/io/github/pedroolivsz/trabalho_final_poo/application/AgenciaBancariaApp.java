package com.io.github.pedroolivsz.trabalho_final_poo.application;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.AgenciaBancariaController;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.ComprovanteController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia.AgenciaBancariaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia.ComprovanteRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.service.agencia.AgenciaBancariaService;
import com.io.github.pedroolivsz.trabalho_final_poo.service.agencia.ComprovanteService;
import com.io.github.pedroolivsz.trabalho_final_poo.view.agencia.AgenciaBancariaView;
import com.io.github.pedroolivsz.trabalho_final_poo.view.agencia.ComprovanteView;

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
