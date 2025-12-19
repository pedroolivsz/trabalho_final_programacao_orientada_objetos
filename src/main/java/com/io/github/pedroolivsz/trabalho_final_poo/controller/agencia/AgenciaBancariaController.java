package com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.service.agencia.AgenciaBancariaService;

import java.util.List;

public class AgenciaBancariaController {
    private final AgenciaBancariaService agenciaBancariaService;

    public AgenciaBancariaController(AgenciaBancariaService agenciaBancariaService) {
        this.agenciaBancariaService = agenciaBancariaService;
    }

    public void criarConta(String nome, String CPF, String endereco, String email, String senha) {
        agenciaBancariaService.criarConta(nome, CPF, endereco, email, senha);
    }

    public List<Conta> listarContas() {
        return agenciaBancariaService.listarContas();
    }

    public Conta logarConta(String email, String senha) {
        return agenciaBancariaService.logarConta(email, senha);
    }

    public Conta procurarPorNumeroDaConta(String numeroConta) {
        return agenciaBancariaService.procurarPorNumeroDaConta(numeroConta);
    }
}
