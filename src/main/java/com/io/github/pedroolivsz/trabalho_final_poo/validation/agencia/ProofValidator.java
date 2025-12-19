package com.io.github.pedroolivsz.trabalho_final_poo.validation.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProofValidationException;

public class ProofValidator {
    public static void validarDadosComprovante(Conta proprietario, String numeroContaDestinatario) {
        if(proprietario == null) throw new ProofValidationException("Conta do proprietário é inválida.");
        if(numeroContaDestinatario == null || numeroContaDestinatario.isBlank()) throw new ProofValidationException("Conta do destinatário é inválida.");
    }
}
