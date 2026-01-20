package com.io.github.pedroolivsz.trabalho_final_poo.util;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Categoria;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.util.List;

public class InputUtil {

    public static String lerString(String msg, String titulo) {
        return JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
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
                    msg,
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

    public static Categoria lerCategoria(String titulo) {
        while (true) {
            try {
                String entrada = JOptionPane.showInputDialog(null,
                        "1. Vestuário\n" +
                                "2. Eletrônicos\n" +
                                "3. Alimentos\n" +
                                "Escolha: ",
                        titulo,
                        JOptionPane.PLAIN_MESSAGE);
                if (entrada == null) entrada = "0";
                int entradaNum = Integer.parseInt(entrada);

                switch (entradaNum) {
                    case 1 -> {
                        return Categoria.VESTAURIO;
                    }
                    case 2 -> {
                        return Categoria.ELETRONICOS;
                    }
                    case 3 -> {
                        return Categoria.ALIMENTOS;
                    }
                    default -> MessageUtil.error("Por favor, digite uma opção válida", "Erro");
                }
            } catch (NumberFormatException e) {
                MessageUtil.error("Por favor, digite uma opção válida", "Erro");
            }
        }
    }

    public static <T> T selecionarObjeto(List<T> list, String msg, String titulo) {
        if(list.isEmpty()) return null;

        T objeto = (T) JOptionPane.showInputDialog(null,
                msg,
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                list.toArray(),
                list.get(0));

        if(objeto == null) throw new IllegalArgumentException("Operação cancelada");

        return objeto;
    }

    public static <E extends  Enum<E>> E selecionarEnum(Class<E> enumClass, String msg, String titulo) {
        E[] valores = enumClass.getEnumConstants();

        E selecionado = (E) JOptionPane.showInputDialog(null,
                msg,
                titulo,
                JOptionPane.PLAIN_MESSAGE,
                null,
                valores,
                valores[0]);

        if(selecionado == null) throw new IllegalArgumentException("Operação cancelada");

        return selecionado;
    }
    public static int lerConfirmacao(String msg, String titulo) {
        return JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
