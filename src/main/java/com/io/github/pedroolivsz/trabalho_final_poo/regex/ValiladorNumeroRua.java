package com.io.github.pedroolivsz.trabalho_final_poo.regex;

import java.util.regex.Pattern;

public class ValiladorNumeroRua {
    private static final String REGEX_NUMERO_RUA = "(?i)^(\\d{1,5}|s\\/n)$";

    private static final Pattern pattern = Pattern.compile(REGEX_NUMERO_RUA);

    public static boolean validarNumeroDaRua(String numero) {
        if(numero == null) return false;
        return pattern.matcher(numero).matches();
    }
}
