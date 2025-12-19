package com.io.github.pedroolivsz.trabalho_final_poo.validation.loja;

import java.math.BigDecimal;

public class ProductValidator {
    public static ProductValidation validarProduto(String nome, String descricao, int quantidade, BigDecimal valorUnitario) {
        if (nome == null || nome.isBlank()) { return ProductValidation.NOME_INVALIDO; }
        if (descricao == null || descricao.isBlank()) { return ProductValidation.DESCRICAO_INVALIDA; }
        if (quantidade < 0) { return ProductValidation.QUANTIDADE_INVALIDA; }
        if (valorUnitario.compareTo(BigDecimal.ZERO) <= 0 || valorUnitario == null) {return ProductValidation.PRECO_INVALIDO; }

        return ProductValidation.SUCESSO;
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
