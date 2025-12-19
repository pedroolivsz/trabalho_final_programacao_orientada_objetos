package com.io.github.pedroolivsz.trabalho_final_poo.service.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.AgenciaBancariaController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.AccountException;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.agencia.AccountValidator;

import java.math.BigDecimal;

public class ContaService {
    private final Conta contaProprietaria;
    private final AgenciaBancariaController agenciaBancariaController;

    public ContaService(Conta contaProprietaria, AgenciaBancariaController agenciaBancariaController) {
        this.contaProprietaria = contaProprietaria;
        this.agenciaBancariaController = agenciaBancariaController;
    }

    public void depositar(BigDecimal valor) {
        AccountValidator.validarDeposito(valor);
        contaProprietaria.setSaldo(contaProprietaria.getSaldo().add(valor));
    }

    public void sacar(BigDecimal valor) {
        AccountValidator.validarSaque(valor);
        if(valor.compareTo(contaProprietaria.getSaldo()) > 0) throw new AccountException("Saldo insuficiente para realizar a transação.");

        contaProprietaria.setSaldo(contaProprietaria.getSaldo().subtract(valor));
    }

    public void transferir(String numeroConta, BigDecimal valor) {
        AccountValidator.validarTransferencia(numeroConta, valor);

        Conta destinatario = agenciaBancariaController.procurarPorNumeroDaConta(numeroConta);

        if(destinatario == null) {
            throw new AccountException("A conta com número: " + numeroConta + " não foi encontrada");
        }

        contaProprietaria.setSaldo(contaProprietaria.getSaldo().subtract(valor));
        destinatario.setSaldo(destinatario.getSaldo().add(valor));
    }

    public Conta getContaProprietaria() {
        return contaProprietaria;
    }
}
