package com.io.github.pedroolivsz.trabalho_final_poo.view.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.ComprovanteController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;

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
            │         Comprovante de saque         │
            │──────────────────────────────────────│
            │ Data da transação: %-17s │
            │ Conta            : Nº %-14s │
            │ Nome             : %-17s │
            │──────────────────────────────────────│
            │ Valor: %-29s │
            └──────────────────────────────────────┘
            """.formatted(comprovante.getDataDeLancamento(),
                    proprietario.getNumeroDaConta(),
                    proprietario.getNomeProprietario(),
                    MenuAgenciaView.formatarSaldo(valor));
    }

    private String montarComprovanteComDestinatario(Comprovante comprovante, Conta proprietario,
                                                    BigDecimal valor, String numeroContaDestinatario) {
        return """
            ┌──────────────────────────────────────┐
            │     Comprovante de transferência     │
            │──────────────────────────────────────│
            │ Data da transação: %-17s │
            │ Conta            : Nº %-14s │
            │ Nome             : %-17s │
            │──────────────────────────────────────│
            │ Valor: %-29s │
            │ Conta destinatária: Nº %-13s │
            └──────────────────────────────────────┘
                """.formatted(comprovante.getDataDeLancamento(),
                proprietario.getNumeroDaConta(),
                proprietario.getNomeProprietario(),
                MenuAgenciaView.formatarSaldo(valor),
                numeroContaDestinatario);
    }
}
