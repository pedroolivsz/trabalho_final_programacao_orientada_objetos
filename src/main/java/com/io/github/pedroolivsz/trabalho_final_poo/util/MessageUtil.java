package com.io.github.pedroolivsz.trabalho_final_poo.util;

import javax.swing.JOptionPane;

public class MessageUtil {

    public static void error(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, formatarTexto(texto), titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void plain(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, formatarTexto(texto), titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public static String formatarTexto(String texto) {
        return """
                <html><div style="font-family: Arial; font-size: 12px;"><pre>
                %s
                </pre></div></html>
                """.formatted(texto);
    }
}
