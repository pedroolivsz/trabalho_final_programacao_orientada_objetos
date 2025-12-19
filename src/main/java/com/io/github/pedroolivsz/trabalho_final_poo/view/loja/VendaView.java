package com.io.github.pedroolivsz.trabalho_final_poo.view.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.VendaValidation;
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

    public BigDecimal calcularValorTotal(List<Produto> cesta) {
        return vendaController.calcularValorTotal(cesta);
    }

    private void adicionarProdutoACesta(List<Produto> cesta) {
        String nome = InputUtil.lerString("Nome do produto: ", "Adicionar produto");
        int quantidade = InputUtil.lerInteiro("Insira a quantidade: ", "Adicionar produto");

        boolean sucesso = vendaController.adicionarProduto(cesta, nome, quantidade);

        if(!sucesso) {
            MessageUtil.error("Produto não encontrado ou quantidade inválida", "Erro");
        }
    }

    public void finalizarVenda(List<Produto> cesta) {
        if(cesta.isEmpty()) {
            MessageUtil.error("Não é possível finalizar uma venda sem produtos", "Erro");
            return;
        }

        BigDecimal valorTotalCesta = calcularValorTotal(cesta);
        int opcao;

        String menu = montarCestaFinalizada(produtoView.formatarListaSimplesProdutos(cesta), valorTotalCesta) +
                "\n" +
                montarMenuDePagamento();

        opcao = InputUtil.lerInteiro(menu, "Finalizar Venda");

        TipoPagamento formaDePagamento = TipoPagamento.fromOpcao(opcao);

        if(formaDePagamento == null) {
            MessageUtil.error("Opção inválida", "Erro");
            return;
        }

        vendaController.removerProdutosVendidos(cesta);
        vendaController.salvarVenda(cesta, formaDePagamento);
    }

    private void exibirCesta(List<Produto> cesta) {
        if (cesta.isEmpty()) {
            MessageUtil.error("Cesta vazia.", "Erro");
            return;
        }

        String cestaFormatada = montarJaneja("Cesta de produtos", produtoView.formatarListaCompletaProdutos(cesta));

        MessageUtil.plain(cestaFormatada, "Cesta");
    }

    public void exibirMenuDeVenda() {
        int opcao;
        List<Produto> cesta = new ArrayList<>();
        do {
            opcao = InputUtil.lerInteiro(montarMenuDeVenda(), "Sistema de loja");
            switch (opcao) {
                case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
                case 1 -> adicionarProdutoACesta(cesta);
                case 2 -> exibirCesta(cesta);
                case 3 -> finalizarVenda(cesta);
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
                │Cesta de produtos
                └────────────────────────────────────────────────────────┘
                %s
                │────────────────────────────────────────────────────────│
                │Valor total: R$ %s
                └────────────────────────────────────────────────────────┘""".formatted(cesta, valorTotal);
    }

    private String montarMenuDePagamento() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                   Menu de pagamento
                │────────────────────────────────────────────────────────│
                │ 1. Pix
                │ 2. Cartão de Débito
                │ 3. Cartão de Crédito
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }
}
