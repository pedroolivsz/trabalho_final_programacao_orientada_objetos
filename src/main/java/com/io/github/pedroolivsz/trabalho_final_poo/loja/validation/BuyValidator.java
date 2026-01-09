package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.BuyValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Compra;

import java.math.BigDecimal;

public class BuyValidator {
    public static void validarCompra(Compra compra) {
        if(compra.getFornecedor() == null) throw new BuyValidationException("Fornecedor obrigatório");
        if(!compra.getFornecedor().estaAtivo()) throw new BuyValidationException("Fornecedor bloqueado");
        if(compra.getValorDaCompra().compareTo(BigDecimal.ZERO) <= 0) throw new BuyValidationException("Valor inválido");
    }
}
