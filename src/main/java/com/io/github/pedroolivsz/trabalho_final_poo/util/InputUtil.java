package com.io.github.pedroolivsz.trabalho_final_poo.util;

import javax.swing.JOptionPane;
import java.math.BigDecimal;

public class InputUtil {

    public static String lerString(String msg, String titulo) {
        return JOptionPane.showInputDialog(null, MessageUtil.formatarTexto(msg), titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static int lerInteiro(String msg, String titulo) {
        while (true) {
            try {
                String entrada = JOptionPane.showInputDialog(null,
                        msg,
                        titulo,
                        JOptionPane.PLAIN_MESSAGE);
                if(entrada == null) return 0;
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                MessageUtil.error("Por favor, digite um número válido", "Erro");
            }
        }
    }

    public static BigDecimal lerBigDecimal(String msg, String titulo) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                    MessageUtil.formatarTexto(msg),
                    titulo,
                    JOptionPane.PLAIN_MESSAGE);
            if(entrada == null) return null;
            try {
                return new BigDecimal(entrada.replace(",", "."));
            } catch (NumberFormatException e) {
                MessageUtil.error("Valor inválido. Digite um número, ex: 25,50 ou 25.50", "Erro");
            }
        }
    }

    public static int lerConfirmacaoYesNo(String msg, String titulo) {
        return JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
