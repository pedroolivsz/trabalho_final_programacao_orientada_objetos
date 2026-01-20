package com.io.github.pedroolivsz.trabalho_final_poo.agencia.service;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.integrar.service.BancoService;

import java.math.BigDecimal;

public class BancoServiceImp implements BancoService {
    private final ContaService contaService;

    public BancoServiceImp(ContaService contaService) {
        this.contaService = contaService;
    }

    @Override
    public void creditarCreditoLoja(Conta conta, BigDecimal valor) {
        contaService.creditar(conta, valor);
    }

    @Override
    public void debitarCreditoLoja(Conta conta, BigDecimal valor) {
        contaService.debitar(conta, valor);
    }
}
