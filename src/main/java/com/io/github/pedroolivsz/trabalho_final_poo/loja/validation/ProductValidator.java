package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;

import java.math.BigDecimal;

public class ProductValidator {
    public static void validarDadosDoProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        if (nome == null || nome.isBlank()) throw new ProductValidationException("Nome inválido");
        if (descricao == null || descricao.isBlank()) throw new ProductValidationException("Descrição inválida");
        if (quantidade < 0) throw new ProductValidationException("Quantidade inválida");
        if (valorUnitario.compareTo(BigDecimal.ZERO) <= 0 || valorUnitario == null) throw new ProductValidationException("Valor unitário inválido");
    }

    public static ProductValidation validarQuantidade(int quantidade) {
        if (quantidade <= 0) { return ProductValidation.QUANTIDADE_INVALIDA; }

        return ProductValidation.SUCESSO;
    }

    public static ProductValidation validarNome(String nome) {
        if (nome == null || nome.isBlank()) { return ProductValidation.NOME_INVALIDO; }

        return ProductValidation.SUCESSO;
    }
}
