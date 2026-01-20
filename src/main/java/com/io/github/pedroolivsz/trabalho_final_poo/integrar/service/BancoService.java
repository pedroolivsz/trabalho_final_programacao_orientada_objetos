package com.io.github.pedroolivsz.trabalho_final_poo.integrar.service;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;

import java.math.BigDecimal;

public interface BancoService {
    void creditarCreditoLoja(Conta conta, BigDecimal valor);
    void debitarCreditoLoja(Conta conta, BigDecimal valor);
}
