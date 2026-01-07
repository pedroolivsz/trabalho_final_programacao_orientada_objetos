package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendaView {
	private final VendaController vendaController;
    private final ProdutoView produtoView;

	public VendaView(VendaController vendaController, ProdutoView produtoView) {
		this.vendaController = vendaController;
        this.produtoView = produtoView;
	}

    public void exibirResultado(VendaValidation vendaValidation) {
        if(vendaValidation == VendaValidation.SUCESSO) {
            MessageUtil.plain("Venda realizada com sucesso!", "Sucesso");
        } else {
            MessageUtil.error(vendaValidation.getMessage(), "Erro");
        }
    }

    private void adicionarProdutoACesta(List<Produto> cesta) {
        String nome = InputUtil.lerString("Nome do produto: ", "Adicionar produto");
        int quantidade = InputUtil.lerInteiro("Insira a quantidade: ", "Adicionar produto");

        boolean sucesso = vendaController.adicionarProduto(cesta, nome, quantidade);

        if(!sucesso) {
            MessageUtil.error("Produto não encontrado ou quantidade inválida", "Erro");
        }
    }

    public void finalizarVenda(List<Produto> carrinho, Loja loja) {
        try {
            if(carrinho.isEmpty()) {
                MessageUtil.error("Não é possível finalizar uma venda sem produtos", "Erro");
                return;
            }

            BigDecimal valorTotalCesta = vendaController.calcularValorTotal(carrinho);
            int opcao;

            String menuDePagamento = montarMenuDePagamento(montarCestaFinalizada(produtoView.formatarListaSimplesProdutos(carrinho), valorTotalCesta));

            opcao = InputUtil.lerInteiro(menuDePagamento, "Finalizar Venda");

            TipoPagamento formaDePagamento = TipoPagamento.fromOpcao(opcao);

            vendaController.salvarVenda(carrinho, formaDePagamento, valorTotalCesta, loja);
            vendaController.removerProdutosVendidos(carrinho);
        } catch (SaleValidationException | ShopValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao finalizar a venda");
        }
    }

    private void exibirCesta(List<Produto> carrinho) {
        if (carrinho.isEmpty()) {
            MessageUtil.error("Carrinho vazia.", "Erro");
            return;
        }

        String carrinhoFormatado = montarJaneja("Carrinho de produtos", produtoView.formatarListaSimplesProdutos(carrinho));

        MessageUtil.plain(carrinhoFormatado, "Carrinho");
    }

    public void exibirMenuDeVenda(Loja loja) {
        int opcao;
        List<Produto> carrinho = new ArrayList<>();
        do {
            opcao = InputUtil.lerInteiro(montarMenuDeVenda(), "Sistema de loja");
            switch (opcao) {
                case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
                case 1 -> adicionarProdutoACesta(carrinho);
                case 2 -> exibirCesta(carrinho);
                case 3 -> finalizarVenda(carrinho, loja);
                default -> MessageUtil.error("Opção inválida", "Erro");
            }
        } while(opcao!=0);
    }

    private String montarMenuDeVenda() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                    Menu de venda
                │────────────────────────────────────────────────────────│
                │ 1. Escolher produto
                │ 2. Cesta de produtos
                │ 3. Finalizar compra
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    private String montarJaneja(String titulo, String corpo) {
        return """
                ┌────────────────────────────────────────────────────────┐
                │%s
                └────────────────────────────────────────────────────────┘
                %s
                └────────────────────────────────────────────────────────┘""".formatted(titulo, corpo);
    }

    private String montarCestaFinalizada(String cesta, BigDecimal valorTotal) {
        return """
                ┌────────────────────────────────────────────────────────┐
                │Carrinho de produtos
                └────────────────────────────────────────────────────────┘
                %s
                │────────────────────────────────────────────────────────│
                │Valor total: R$ %s
                └────────────────────────────────────────────────────────┘""".formatted(cesta, valorTotal);
    }

    private String montarMenuDePagamento(String texto) {
        return """
                %s
                ┌────────────────────────────────────────────────────────┐
                │                   Menu de pagamento
                │────────────────────────────────────────────────────────│
                │ 1. Pix
                │ 2. Cartão de Débito
                │ 3. Cartão de Crédito
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""".formatted(texto);
    }
}
