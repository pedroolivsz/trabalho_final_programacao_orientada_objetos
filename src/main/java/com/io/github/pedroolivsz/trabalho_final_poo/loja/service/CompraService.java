package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.BuyValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Compra;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.BuyValidator;

public class CompraService {
    private final ProdutoService produtoService;
    private final LojaService lojaService;

    public CompraService(ProdutoService produtoService, LojaService lojaService) {
        this.produtoService = produtoService;
        this.lojaService = lojaService;
    }

    public void realizarCompra(Compra compra) {
        BuyValidator.validarCompra(compra);

        Loja fornecedor = compra.getFornecedor().getLoja();
        Loja comprador = lojaService.getLojaLogada();

        comprador.debitar(compra.getValorDaCompra());
        fornecedor.creditar(compra.getValorDaCompra());

        compra.getProdutos().forEach(produtoService::adicionarProodutoAoEstoque);
        compra.finalizar();
    }

}
