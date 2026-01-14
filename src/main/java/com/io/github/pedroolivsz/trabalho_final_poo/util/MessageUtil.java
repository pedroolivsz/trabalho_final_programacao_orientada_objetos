package com.io.github.pedroolivsz.trabalho_final_poo.util;

import javax.swing.JOptionPane;

public class MessageUtil {

    public static void error(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void plain(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
    }
}
