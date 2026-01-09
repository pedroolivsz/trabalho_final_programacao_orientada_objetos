package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.CompraRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.BuyValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.OutfitterValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;

import java.math.BigDecimal;

public class CompraService {
    private final CompraRepository compraRepository;
    private final ProdutoService produtoService;
    private final LojaService lojaService;

    public CompraService(CompraRepository compraRepository, ProdutoService produtoService, LojaService lojaService) {
        this.compraRepository = compraRepository;
        this.produtoService = produtoService;
        this.lojaService = lojaService;
    }

    public void realizarCompra(Carrinho carrinho, TipoPagamento tipoPagamento, Fornecedor fornecedor, Loja comprador) {
        OutfitterValidator.validarFornecedor(fornecedor);
        ShopValidator.validarExistenciaDeLoja(comprador);
        BuyValidator.validarTipoPagamento(tipoPagamento);

        BigDecimal total = carrinho.calcularTotal();


    }

    public void removerProdutosDoVendedor(long idVendedor, Carrinho carrinho) {
        for(Produto produtoVendido : carrinho.getProdutos()) {
            produtoService.subtrairQuantidade(idVendedor, produtoVendido.getNome(), produtoVendido.getQuantidade());
        }
    }

}
