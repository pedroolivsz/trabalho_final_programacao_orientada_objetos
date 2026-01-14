package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.CompraRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.BuyValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.OutfitterValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.TransacaoView;

import java.math.BigDecimal;

public class CompraService {
    /**private final CompraRepository compraRepository;
    private final TransacaoView transacaoView;
    private final ProdutoService produtoService;
    private final LojaService lojaService;

    public CompraService(CompraRepository compraRepository, TransacaoView transacaoView, ProdutoService produtoService, LojaService lojaService) {
        this.transacaoView = transacaoView;
        this.compraRepository = compraRepository;
        this.produtoService = produtoService;
        this.lojaService = lojaService;
    }

    public void realizarCompra(Carrinho carrinho, TipoPagamento tipoPagamento, Fornecedor fornecedor, Loja comprador) {
        OutfitterValidator.validarFornecedor(fornecedor);
        ShopValidator.validarExistenciaDeLoja(comprador);
        BuyValidator.validarTipoPagamento(tipoPagamento);

        BigDecimal total = carrinho.calcularTotal();

        Compra compra = new Compra(fornecedor, carrinho.getProdutos(), tipoPagamento);
        Venda venda = new Venda(carrinho.getProdutos(), tipoPagamento, total);

        compra.setIdLoja(comprador.getId());
        venda.setIdLoja(fornecedor.getLoja().getId());

        comprador.debitar(total);
        fornecedor.getLoja().creditar(total);

        removerProdutosDoVendedor(fornecedor.getLoja().getId(), carrinho);
        adicionarProdutosDoComprador(comprador.getId(), fornecedor.getLoja().getId(), carrinho);

        compraRepository.salvarCompra(compra);
        transacaoView.realizarVenda(carrinho, tipoPagamento, fornecedor.getLoja());
    }

    public void removerProdutosDoVendedor(long idVendedor, Carrinho carrinho) {
        for(Produto produtoVendido : carrinho.getProdutos()) {
            produtoService.subtrairProdutosVendidosDoEstoque(idVendedor, produtoVendido.getNome(), produtoVendido.getQuantidade());
        }
    }

    public void adicionarProdutosDoComprador(long idComprador, long idVendedor, Carrinho carrinho) {
        for(Produto produtoVendido : carrinho.getProdutos()) {
            produtoService.adicionarEstoque(idComprador, idVendedor, produtoVendido.getNome(), produtoVendido.getQuantidade());
        }
    }**/

}
