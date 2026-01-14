package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.TransacaoService;

import java.math.BigDecimal;

public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public void realizarVenda(Carrinho carrinho, TipoPagamento tipoPagamento, Loja loja) {
        transacaoService.realizarVenda(carrinho, tipoPagamento, loja);
    }

    public void realizarCompra(Carrinho carrinho, TipoPagamento tipoPagamento, Loja fornecedor, Loja comprador) {
        transacaoService.realizarCompra(carrinho, tipoPagamento, fornecedor, comprador);
    }

    public BigDecimal calcularValorTotalDeVendas() {
        return transacaoService.calcularValorTotalDeVendas();
    }

    public void adicionarProduto(long idLoja, Carrinho carrinho, String nome, int quantidade) {
        transacaoService.adicionarProdutoAoCarrinho(idLoja, carrinho, nome, quantidade);
    }
}
