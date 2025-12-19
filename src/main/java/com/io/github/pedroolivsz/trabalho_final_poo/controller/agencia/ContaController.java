package com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.service.agencia.ContaService;

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
