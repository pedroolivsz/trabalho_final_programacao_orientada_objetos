package com.io.github.pedroolivsz.trabalho_final_poo.view.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MenuAgenciaView {
    public static String montarMenuDaConta(Conta proprietario) {
        return """
            <html><div style="font-family: Arial; font-size: 12px;"><pre>
            ┌───────────────────────────────────┐
            │           MENU DA CONTA           │
            │───────────────────────────────────│
            │ Nº da conta: %-20s │              
            │ Nome       : %-20s │
            │ Saldo      : %-20s │
            │───────────────────────────────────│
            │ 1. Depositar                      │
            │ 2. Sacar                          │
            │ 3. Transferir                     │
            │ 0. Sair                           │
            └───────────────────────────────────┘
            </pre></div></html>
            """.formatted(
                proprietario.getNumeroDaConta(),
                proprietario.getNomeProprietario(),
                formatarSaldo(proprietario.getSaldo()));
    }

    public static String montarMenuInicial() {
        return """
            <html><div style="font-family: Arial; font-size: 12px;"><pre>
            ┌───────────────────────────────────┐
            │          MENU DA INICIAL          │
            │───────────────────────────────────│
            │ 1. Criar conta                    │
            │ 2. Acessar conta                  │
            │ 0. Sair da agência                │
            └───────────────────────────────────┘
            </pre></div></html>
            """;
    }

    public static String formatarSaldo(BigDecimal saldo) {
        return NumberFormat
                .getCurrencyInstance(new Locale("pt", "BR"))
                .format(saldo);
    }
}
