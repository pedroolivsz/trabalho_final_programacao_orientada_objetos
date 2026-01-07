package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;

import java.math.BigDecimal;
import java.util.List;

public class VendaValidator {
    public static void validarDados(List<Produto> produtos, TipoPagamento tipoPagamento, BigDecimal valorDaVenda) {
        if(produtos.isEmpty()) throw new SaleValidationException("Lista vazia");
        if(tipoPagamento == null) throw new SaleValidationException("Tipo de pagamento inválido");
        if(valorDaVenda == null || valorDaVenda.compareTo(BigDecimal.ZERO) <= 0) throw new SaleValidationException("Tipo de pagamento inválido");
    }
}
