package com.io.github.pedroolivsz.trabalho_final_poo.service.loja;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.VendaValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.VendaValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.view.loja.ProdutoView;

public class VendaService {
	private final VendaRepository vendaRepository;
    private final ProdutoView produtoView;

	public VendaService(VendaRepository vendaRepository, ProdutoView produtoView) {
		this.vendaRepository = vendaRepository;
        this.produtoView = produtoView;
	}

	public VendaValidation salvarVenda(List<Produto> produtos, TipoPagamento tipoPagamento) {
		VendaValidation validation = VendaValidator.validar(produtos, tipoPagamento);

        if(validation != VendaValidation.SUCESSO) {
            return validation;
        }
		
		LocalDate dataDaVenda = LocalDate.now();
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		for(Produto produto : produtos) {
			BigDecimal quantidade = new BigDecimal(produto.getQuantidade());
			BigDecimal valor = produto.getValorUnitario().multiply(quantidade);
			valorTotal.add(valor);
		}
		
		Venda venda = new Venda(produtos, tipoPagamento, dataDaVenda, valorTotal);
		vendaRepository.salvarVenda(venda);
		
		return VendaValidation.SUCESSO;
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
