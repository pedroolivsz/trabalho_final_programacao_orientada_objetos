package com.io.github.pedroolivsz.trabalho_final_poo.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String gerarHash(String senha) {
        return BCrypt.withDefaults().hashToString(10, senha.toCharArray());
    }

    public static boolean verificarSenha(String senhaDigitada, String hashSalvo) {
        BCrypt.Result result = BCrypt.verifyer().verify(senhaDigitada.toCharArray(),
                                                        hashSalvo);
        return result.verified;
    }
}
