package com.io.github.pedroolivsz.trabalho_final_poo.view.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.ContaController;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.AccountException;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.math.BigDecimal;

public class ContaView {
    private final ContaController contaController;
    private final ComprovanteView comprovanteView;

    public ContaView(ContaController contaController, ComprovanteView comprovanteView) {
        this.contaController = contaController;
        this.comprovanteView = comprovanteView;
    }

    public void depositar() {
        try {
            BigDecimal valorDepositado = InputUtil.lerBigDecimal("Insira o valor: ", "Depositar");

            contaController.depositar(valorDepositado);

            String comprovante = comprovanteView.comprovanteDeDeposito(contaController.getContaProprietaria(), valorDepositado);
            MessageUtil.plain(comprovante, "Sucesso");
        } catch (AccountException e) {
            MessageUtil.error(e.getMessage(), "Erro na transação");
        }
    }

    public void sacar() {
        try {
            BigDecimal valorSacado = InputUtil.lerBigDecimal("Insira o valor: ", "Sacar");

            contaController.sacar(valorSacado);

            String comprovante = comprovanteView.comprovanteDeSaque(contaController.getContaProprietaria(), valorSacado);
            MessageUtil.plain(comprovante, "Sucesso");
        } catch (AccountException e) {
            MessageUtil.error(e.getMessage(), "Erro na transação");
        }
    }

    public void transferir() {
        try {
            String numeroConta = InputUtil.lerString("Insira o número da conta: ", "Transferência");
            BigDecimal valorTransferido = InputUtil.lerBigDecimal("Insira o valor: ", "Transferência");

            contaController.transferir(numeroConta, valorTransferido);

            String comprovante = comprovanteView.comprovanteDeTransferencia(contaController.getContaProprietaria(), numeroConta, valorTransferido);
            MessageUtil.plain(comprovante, "Sucesso");
        } catch (AccountException e) {
            MessageUtil.error(e.getMessage(), "Erro na transação");
        }
    }
}
