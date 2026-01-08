package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Carrinho;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.math.BigDecimal;

public class VendaView {
	private final VendaController vendaController;
    private final ProdutoView produtoView;

	public VendaView(VendaController vendaController, ProdutoView produtoView) {
		this.vendaController = vendaController;
        this.produtoView = produtoView;
	}

    private void adicionarProdutoACesta(Carrinho carrinho) {
        try {
            String nome = InputUtil.lerString("Nome do produto: ", "Adicionar produto");
            int quantidade = InputUtil.lerInteiro("Insira a quantidade: ", "Adicionar produto");

            vendaController.adicionarProduto(carrinho, nome, quantidade);
        } catch (SaleValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao adicionar produto ao carrinho");
        }
    }

    public void finalizarVenda(Carrinho carrinho, Loja loja) {
        try {
            if(carrinho.getProdutos().isEmpty()) {
                MessageUtil.error("Não é possível finalizar uma venda sem produtos", "Erro");
                return;
            }

            int opcao;

            String menuDePagamento = montarMenuDePagamento(montarCestaFinalizada(produtoView.formatarListaSimplesProdutos(carrinho.getProdutos()), carrinho.calcularTotal()));

            opcao = InputUtil.lerInteiro(menuDePagamento, "Finalizar Venda");

            TipoPagamento formaDePagamento = TipoPagamento.fromOpcao(opcao);

            vendaController.realizarVenda(carrinho, formaDePagamento, loja);
        } catch (SaleValidationException | ShopValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao finalizar a venda");
        }
    }

    private void exibirCesta(Carrinho carrinho) {
        if (carrinho.getProdutos().isEmpty()) {
            MessageUtil.error("Carrinho vazio.", "Erro");
            return;
        }

        String carrinhoFormatado = montarJaneja("Carrinho de produtos", produtoView.formatarListaSimplesProdutos(carrinho.getProdutos()));

        MessageUtil.plain(carrinhoFormatado, "Carrinho");
    }

    public void exibirMenuDeVenda(Loja loja) {
        int opcao;
        Carrinho carrinho = new Carrinho();
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
