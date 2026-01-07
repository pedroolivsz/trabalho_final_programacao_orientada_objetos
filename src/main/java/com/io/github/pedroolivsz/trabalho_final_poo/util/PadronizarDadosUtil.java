package com.io.github.pedroolivsz.trabalho_final_poo.util;

public class PadronizarDadosUtil {
    public static String normalizarTexto(String texto) {
        return texto == null ? null : texto.trim();
    }

    public static String normalizarTextoMaiusculo(String texto) {
        return texto == null ? null : texto.trim().toUpperCase();
    }

    public static String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    public static String normalzarTelefone(String telefone) {
        return telefone == null ? null : telefone.replaceAll("[^0-9]", "");
    }

    public static String normalzarCnpj(String cnpj) {
        return cnpj == null ? null : cnpj.replaceAll("[^0-9]", "");
    }

    public static String normalzarCep(String cep) {
        return cep == null ? null : cep.replaceAll("[^0-9]", "");
    }

    public static String normalizarNumeroDaRua(String numero) {
        return numero == null ? null : numero.trim();
    }
}
