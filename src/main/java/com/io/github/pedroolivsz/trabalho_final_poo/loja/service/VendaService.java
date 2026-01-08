package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidator;

public class VendaService {
	private final VendaRepository vendaRepository;
    private final ProdutoService produtoService;

	public VendaService(VendaRepository vendaRepository, ProdutoService produtoService) {
		this.vendaRepository = vendaRepository;
        this.produtoService = produtoService;
	}

	public void realizarVenda(Carrinho carrinho, TipoPagamento tipoPagamento, Loja loja) {
        ShopValidator.validarLojaAtiva(loja);
        VendaValidator.validarCarrinho(carrinho);
		VendaValidator.validarTipoPagamento(tipoPagamento);

        BigDecimal total = carrinho.calcularTotal();
		LocalDate dataDaVenda = LocalDate.now();

        removerProdutosVendidos(carrinho);

        loja.creditar(carrinho.calcularTotal());

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
