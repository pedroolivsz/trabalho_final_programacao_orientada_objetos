package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.OutfitterValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Fornecedor;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;

public class OutfitterValidator {
    public static void validarSeApto(Loja loja) {
        if(loja == null) throw new OutfitterValidationException("Loja não encontrada");
        if(loja.estaBloqueada()) throw new OutfitterValidationException("Loja bloqueada");
    }

    public static void validarFornecedor(Fornecedor fornecedor) {
        if(fornecedor == null) throw new OutfitterValidationException("Fornecedor inválido");
        if(!fornecedor.estaAtivo()) throw new OutfitterValidationException("Fornecedor inativo");
    }
}
