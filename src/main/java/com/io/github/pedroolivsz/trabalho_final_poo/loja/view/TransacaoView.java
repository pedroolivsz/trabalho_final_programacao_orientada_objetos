package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ProductValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.SaleValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.TransacaoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Carrinho;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PadronizarDadosUtil;

import java.math.BigDecimal;

public class TransacaoView {
    private final TransacaoController transacaoController;
    private final ProdutoView produtoView;

    public TransacaoView(TransacaoController transacaoController, ProdutoView produtoView) {
        this.transacaoController = transacaoController;
        this.produtoView = produtoView;
    }

    private void adicionarProdutoAoCarrinho(long idLoja, Carrinho carrinho) {
        try {
            String nome = InputUtil.lerString("Nome do produto: ", "Adicionar produto");
            int quantidade = InputUtil.lerInteiro("Insira a quantidade: ", "Adicionar produto");

            transacaoController.adicionarProduto(idLoja, carrinho, nome, quantidade);

            MessageUtil.plain(nome + " adicionado ao carrinho", "Sucesso");
        } catch (SaleValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao adicionar produto ao carrinho");
        }
    }

    public void finalizarVenda(Carrinho carrinho, Loja loja) {
        finalzarTransacao(carrinho,
                "Finalizar venda",
                () -> transacaoController.realizarVenda(carrinho, TipoPagamento.PIX, loja));
    }

    public void finalizarCompra(Carrinho carrinho, Loja fornecedor, Loja comprador) {
        finalzarTransacao(carrinho,
                "Finalizar Compra",
                () -> transacaoController.realizarCompra(carrinho, TipoPagamento.PIX, fornecedor, comprador));
    }

    private void finalzarTransacao(Carrinho carrinho, String titulo, Runnable acaoDeFinalizacao) {
        try{
            if(carrinho.getProdutos().isEmpty()) {
                MessageUtil.error("Não é possível finalizar uma venda sem produtos", "Erro");
                return;
            }

            String menu = montarMenuDePagamento(
                    montarCarrinhoDeProdutos(produtoView.formatarListaSimplesItensTransacao(carrinho.getProdutos()),
                    carrinho.calcularTotal()));

            int opcao = InputUtil.lerInteiro(menu, titulo);

            TipoPagamento tipoPagamento = TipoPagamento.fromOpcao(opcao);

            acaoDeFinalizacao.run();
            MessageUtil.plain("Transação finalizada com sucesso", "Sucesso");
            carrinho.esvaziarCarrinho();
        } catch (SaleValidationException | ShopValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao finalizar a transação");
        }
    }

    private void exibirCarrinho(Carrinho carrinho) {
        if (carrinho.getProdutos().isEmpty()) {
            MessageUtil.error("Carrinho vazio.", "Erro");
            return;
        }

        String carrinhoFormatado = montarCarrinhoDeProdutos(produtoView.formatarListaSimplesItensTransacao(carrinho.getProdutos()),
                carrinho.calcularTotal());

        MessageUtil.plain(carrinhoFormatado, "Carrinho");
    }

    public void exibirMenuDeVenda(Loja loja) {
        try {
            int opcao;
            Carrinho carrinho = new Carrinho();
            do {
                opcao = InputUtil.lerInteiro(montarMenuDeVendas(), "Sistema de loja");
                switch (opcao) {
                    case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
                    case 1 -> adicionarProdutoAoCarrinho(loja.getId(), carrinho);
                    case 2 -> exibirCarrinho(carrinho);
                    case 3 -> finalizarVenda(carrinho, loja);
                    default -> MessageUtil.error("Opção inválida", "Erro");
                }
            } while(opcao!=0);
        } catch (ProductValidationException | SaleValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro");
        }
    }

    public void exibirMenuDeComprasEntreLojas(Loja loja, Loja fornecedor) {
        try {
            int opcao;
            Carrinho carrinho = new Carrinho();
            do {
                opcao = InputUtil.lerInteiro(montarMenuDeTransacoesEntreLojas(), "Sistema de loja");
                switch (opcao) {
                    case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
                    case 1 -> produtoView.listarProdutos(fornecedor.getId());
                    case 2 -> adicionarProdutoAoCarrinho(fornecedor.getId(), carrinho);
                    case 3 -> exibirCarrinho(carrinho);
                    case 4 -> finalizarCompra(carrinho, fornecedor, loja);
                    default -> MessageUtil.error("Opção inválida", "Erro");
                }
            } while(opcao!=0);
        } catch (ProductValidationException | SaleValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro");
        }
    }

    private String montarMenuDeVendas() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                    Menu de vendas
                │────────────────────────────────────────────────────────│
                │ 1. Escolher produto
                │ 2. Carrinho
                │ 3. Finalizar compra
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    private String montarMenuDeTransacoesEntreLojas() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                      Compras
                │────────────────────────────────────────────────────────│
                │ 1. Catalogo do fornecedor
                │ 2. Escolher produto
                │ 3. Carrinho
                │ 4. Finalizar compra
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

    private String montarCarrinhoDeProdutos(String cesta, BigDecimal valorTotal) {
        return """
                ┌────────────────────────────────────────────────────────┐
                │Carrinho de produtos
                └────────────────────────────────────────────────────────┘
                %s
                │────────────────────────────────────────────────────────│
                │Valor total: %s
                └────────────────────────────────────────────────────────┘""".formatted(cesta,
                PadronizarDadosUtil.normalizarSaldo(valorTotal));
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
