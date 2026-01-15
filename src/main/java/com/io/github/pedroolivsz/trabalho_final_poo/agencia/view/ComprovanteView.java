package com.io.github.pedroolivsz.trabalho_final_poo.agencia.view;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller.ComprovanteController;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PadronizarDadosUtil;

import java.math.BigDecimal;

public class ComprovanteView {

    private final ComprovanteController comprovanteController;

    public ComprovanteView(ComprovanteController comprovanteController) {
        this.comprovanteController = comprovanteController;
    }

    public String comprovanteDeTransferencia(Conta proprietario, String numeroContaDestinatario, BigDecimal valor) {

        Comprovante comprovante = comprovanteController.criarComprovante(proprietario, numeroContaDestinatario);

        return montarComprovanteComDestinatario(comprovante, proprietario, valor, numeroContaDestinatario);
    }

    public String comprovanteDeDeposito(Conta proprietario, BigDecimal valor) {

        Comprovante comprovante = comprovanteController.criarComprovante(proprietario, proprietario.getNumeroDaConta());

        return montarComprovanteSimple(comprovante, proprietario, valor);
    }

    public String comprovanteDeSaque(Conta proprietario, BigDecimal valor) {

        Comprovante comprovante = comprovanteController.criarComprovante(proprietario, proprietario.getNumeroDaConta());

        return montarComprovanteSimple(comprovante, proprietario, valor);
    }

    private String montarComprovanteSimple(Comprovante comprovante, Conta proprietario, BigDecimal valor) {
            return """
            ┌──────────────────────────────────────┐
            │         Comprovante de saque
            │──────────────────────────────────────│
            │ Data da transação: %-17s
            │ Conta            : Nº %-14s
            │ Nome             : %-17s
            │──────────────────────────────────────│
            │ Valor: %-29s
            └──────────────────────────────────────┘
            """.formatted(comprovante.getDataDeLancamento(),
                    proprietario.getNumeroDaConta(),
                    proprietario.getNomeProprietario(),
                    PadronizarDadosUtil.normalizarSaldo(valor));
    }

    private String montarComprovanteComDestinatario(Comprovante comprovante, Conta proprietario,
                                                    BigDecimal valor, String numeroContaDestinatario) {
        return """
            ┌──────────────────────────────────────┐
            │     Comprovante de transferência
            │──────────────────────────────────────│
            │ Data da transação: %-17s
            │ Conta            : Nº %-14s
            │ Nome             : %-17s
            │──────────────────────────────────────│
            │ Valor: %-29s
            │ Conta destinatária: Nº %-13s
            └──────────────────────────────────────┘
                """.formatted(comprovante.getDataDeLancamento(),
                proprietario.getNumeroDaConta(),
                proprietario.getNomeProprietario(),
                PadronizarDadosUtil.normalizarSaldo(valor),
                numeroContaDestinatario);
    }
}
