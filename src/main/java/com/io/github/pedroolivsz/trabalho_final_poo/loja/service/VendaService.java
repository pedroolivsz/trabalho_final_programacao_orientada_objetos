package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.ProdutoView;

public class VendaService {
	private final VendaRepository vendaRepository;
    private final LojaService lojaService;
    private final ProdutoView produtoView;

	public VendaService(VendaRepository vendaRepository, LojaService lojaService, ProdutoView produtoView) {
		this.vendaRepository = vendaRepository;
        this.lojaService = lojaService;
        this.produtoView = produtoView;
	}

	public void salvarVenda(List<Produto> produtos, TipoPagamento tipoPagamento, BigDecimal valorDaVenda, Loja loja) {
		VendaValidator.validarDados(produtos, tipoPagamento, valorDaVenda);
		
		LocalDate dataDaVenda = LocalDate.now();
		
		Venda venda = new Venda(produtos, tipoPagamento, dataDaVenda, valorDaVenda);

        lojaService.adicionarDinheiroAoCaixa(loja, valorDaVenda);

		vendaRepository.salvarVenda(venda);
	}

    public boolean adicionarProduto(List<Produto> produtos, String nome, int quantidade) {
        Produto produtoOriginal = produtoView.procurarProdutoPorNome(nome);

        if(quantidade <= 0) return false;
        if(produtoOriginal == null) return false;

        Produto produtoCesta = new Produto(produtoOriginal.getNome(), produtoOriginal.getDescricao(),
                quantidade, produtoOriginal.getValorUnitario());
        produtoCesta.setCodigoDeBarras(produtoOriginal.getCodigoDeBarras());
        produtos.add(produtoCesta);

        return true;
    }

    public void removerProdutosVendidos(List<Produto> cesta) {
        for(Produto produtoVendido : cesta) {
            produtoView.subtrairQuantidadeProduto(produtoVendido.getNome(), produtoVendido.getQuantidade());
        }
    }

    public BigDecimal calcularValorTotalDaCesta(List<Produto> cesta) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal valorDoProduto;

        for(Produto produto : cesta) {
            valorDoProduto = produto.getValorUnitario().multiply(BigDecimal.valueOf(produto.getQuantidade()));
            valorTotal = valorTotal.add(valorDoProduto);
        }

        return valorTotal;
    }

    public BigDecimal calcularValorTotalDeVendas() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<Venda> vendas = vendaRepository.listarVendas();

        for(Venda venda : vendas) {
            valorTotal = valorTotal.add(venda.getValorTotal());
        }

        return valorTotal;
    }
	
	public List<Venda> listarVendas() {
		return vendaRepository.listarVendas();
	}

}
