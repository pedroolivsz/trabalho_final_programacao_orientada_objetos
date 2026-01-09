package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.OutfitterValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;

public class OutfitterValidator {
    public static void validarSeApto(Loja loja) {
        if(loja == null) throw new OutfitterValidationException("Loja não encontrada");
        if(loja.estaBloqueada()) throw new OutfitterValidationException("Loja bloqueada");
    }
}
