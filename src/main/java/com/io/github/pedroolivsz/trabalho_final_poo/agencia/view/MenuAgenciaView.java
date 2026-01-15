package com.io.github.pedroolivsz.trabalho_final_poo.agencia.view;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PadronizarDadosUtil;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MenuAgenciaView {
    public static String montarMenuDaConta(Conta proprietario) {
        return """
            ┌───────────────────────────────────┐
            │           MENU DA CONTA
            │───────────────────────────────────│
            │ Nº da conta: %-20s
            │ Nome       : %-20s
            │ Saldo      : %-20s
            │───────────────────────────────────│
            │ 1. Depositar
            │ 2. Sacar
            │ 3. Transferir
            │ 0. Sair
            └───────────────────────────────────┘
            """.formatted(
                proprietario.getNumeroDaConta(),
                proprietario.getNomeProprietario(),
                PadronizarDadosUtil.normalizarSaldo(proprietario.getSaldo()));
    }

    public static String montarMenuInicial() {
        return """
            ┌───────────────────────────────────┐
            │          MENU DA INICIAL
            │───────────────────────────────────│
            │ 1. Criar conta
            │ 2. Acessar conta
            │ 0. Sair da agência
            └───────────────────────────────────┘
            """;
    }
}
