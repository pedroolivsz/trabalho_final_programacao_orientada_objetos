package com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.ContaService;

import java.math.BigDecimal;

public class ContaController {

    private final ContaService contaService;

    public ContaController(Conta contaProprietaria, AgenciaBancariaController agenciaBancariaController) {
        this.contaService = new ContaService(contaProprietaria, agenciaBancariaController);
    }

    public void depositar(BigDecimal valor) {
        contaService.depositar(valor);
    }

    public void sacar(BigDecimal valor) {
        contaService.sacar(valor);
    }

    public void transferir(String numeroConta, BigDecimal valor) {
        contaService.transferir(numeroConta, valor);
    }

    public Conta getContaProprietaria() {
        return contaService.getContaProprietaria();
    }
}
