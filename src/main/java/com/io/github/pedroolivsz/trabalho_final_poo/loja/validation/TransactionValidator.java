package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.TransactionValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Carrinho;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;

public class TransactionValidator {
    public static void validarItens(Loja loja, Carrinho carrinho, TipoPagamento tipoPagamento) {
        if(loja == null) throw new TransactionValidationException("Loja inválida");
        if(carrinho == null || carrinho.getProdutos() == null || carrinho.getProdutos().isEmpty()) {
            throw new TransactionValidationException("A transação deve possuir itens");
        }
        if(tipoPagamento == null) throw new TransactionValidationException("Tipo de pagamento inválido");
    }
}
