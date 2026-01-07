package com.io.github.pedroolivsz.trabalho_final_poo.regex;

import java.util.regex.Pattern;

public class ValidadorTelefone {
    private static final String REGEX_TELEFONE = "^(\\(\\d{2}\\)|\\d{2})\\s?-?\\d{4,5}-?\\d{4}$";

    private static final Pattern pattern = Pattern.compile(REGEX_TELEFONE);

    public static boolean validarTelefone(String telefone) {
        if(telefone == null) return false;
        return pattern.matcher(telefone).matches();
    }
}
