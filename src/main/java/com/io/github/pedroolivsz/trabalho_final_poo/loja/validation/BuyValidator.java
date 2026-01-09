package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.BuyValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Compra;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;

import java.math.BigDecimal;

public class BuyValidator {
    public static void validarTipoPagamento(TipoPagamento tipoPagamento) {
        if(tipoPagamento == null) throw new BuyValidationException("Tipo de pagamento inválido");
    }
}
