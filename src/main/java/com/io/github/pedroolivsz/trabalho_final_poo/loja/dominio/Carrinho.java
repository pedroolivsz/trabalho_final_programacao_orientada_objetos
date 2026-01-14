package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private final List<ItemTransacao> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        if(produto == null) throw new SaleValidationException("O produto não foi encontrado");
        if(quantidade < 0) throw new SaleValidationException("Quantidade inválida");

        produtos.add(new ItemTransacao(produto, quantidade));
    }

    public BigDecimal calcularTotal() {
        return produtos.stream()
                .map(p -> p.getValorUnitario()
                        .multiply(BigDecimal.valueOf(p.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void esvaziarCarrinho() {
        produtos.clear();
    }

    public List<ItemTransacao> getProdutos() {
        return produtos;
    }
}
