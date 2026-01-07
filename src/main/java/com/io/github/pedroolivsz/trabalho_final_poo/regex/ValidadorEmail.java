package com.io.github.pedroolivsz.trabalho_final_poo.regex;

import java.util.regex.Pattern;

public class ValidadorEmail {
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern pattern = Pattern.compile(REGEX_EMAIL);

    public static boolean validarEmail(String email) {
        if(email == null) return false;
        return pattern.matcher(email).matches();
    }
}
