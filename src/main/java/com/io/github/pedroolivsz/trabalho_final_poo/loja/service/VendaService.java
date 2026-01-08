package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidator;

public class VendaService {
	private final VendaRepository vendaRepository;
    private final LojaService lojaService;
    private final ProdutoService produtoService;

	public VendaService(VendaRepository vendaRepository, LojaService lojaService, ProdutoService produtoService) {
		this.vendaRepository = vendaRepository;
        this.lojaService = lojaService;
        this.produtoService = produtoService;
	}

	public void realizarVenda(Carrinho carrinho, TipoPagamento tipoPagamento, Loja loja) {
        VendaValidator.validarCarrinho(carrinho);
		VendaValidator.validarTipoPagamento(tipoPagamento);

        BigDecimal total = carrinho.calcularTotal();
		LocalDate dataDaVenda = LocalDate.now();

        removerProdutosVendidos(carrinho);

        lojaService.adicionarDinheiroAoCaixa(loja, carrinho.calcularTotal());

		Venda venda = new Venda(carrinho.getProdutos(), tipoPagamento, dataDaVenda, total);

		vendaRepository.salvarVenda(venda);
	}

    public void adicionarProdutoAoCarrinho(Carrinho carrinho, String nome, int quantidade) {
        Produto item = produtoService.procurarPorNome(nome);
        carrinho.adicionarProduto(item, quantidade);
    }

    private void removerProdutosVendidos(Carrinho carrinho) {
        for(Produto produtoVendido : carrinho.getProdutos()) {
            produtoService.subtrairQuantidade(produtoVendido.getNome(), produtoVendido.getQuantidade());
        }
    }

    public BigDecimal calcularValorTotalDeVendas() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<Venda> vendas = vendaRepository.listarVendas();

        for(Venda venda : vendas) {
            valorTotal = valorTotal.add(venda.getValorTotal());
        }

        return valorTotal;
    }
}
