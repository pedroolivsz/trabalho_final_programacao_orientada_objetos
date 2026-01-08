package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Carrinho;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;

import java.math.BigDecimal;
import java.util.List;

public class VendaValidator {
    public static void validarTipoPagamento(TipoPagamento tipoPagamento) {
        if(tipoPagamento == null) throw new SaleValidationException("Tipo de pagamento inválido");
    }

    public static void validarCarrinho(Carrinho carrinho) {
        if(carrinho == null) throw new SaleValidationException("Erro ao carregar o carrinho de compras");
        if(carrinho.getProdutos().isEmpty()) throw new SaleValidationException("Lista vazia");
        if(carrinho.calcularTotal() == null || carrinho.calcularTotal().compareTo(BigDecimal.ZERO) <= 0) throw new SaleValidationException("Valor total de compras inválido");
    }
}
