package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.TransacaoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Categoria;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PadronizarDadosUtil;

import java.math.BigDecimal;

public class LojaView {
    private final LojaController lojaController;
    private final TransacaoController transacaoController;
    private final ProdutoView produtoView;
    private final TransacaoView transacaoView;
    private final FornecedorView fornecedorView;

    public LojaView(LojaController lojaController, TransacaoController transacaoController,
                    ProdutoView produtoView, TransacaoView transacaoView,
                    FornecedorView fornecedorView) {
        this.lojaController = lojaController;
        this.transacaoController = transacaoController;
        this.produtoView = produtoView;
        this.transacaoView = transacaoView;
        this.fornecedorView = fornecedorView;
    }

    private static final String TITULO_SISTEMA = "Sistema de Loja";
    private static final String TITULO_CADASTRO = "Cadastro de Loja";

    public void adicionarLoja() {
        try {
            String nome = InputUtil.lerString("Nome: ", TITULO_CADASTRO);
            String cnpj = InputUtil.lerString("CNPJ: ", TITULO_CADASTRO);
            String cep = InputUtil.lerString("CEP: ", TITULO_CADASTRO);
            String estado = InputUtil.lerString("Estado: ", TITULO_CADASTRO);
            String cidade = InputUtil.lerString("Cidade: ", TITULO_CADASTRO);
            String bairro = InputUtil.lerString("Bairro: ", TITULO_CADASTRO);
            String rua = InputUtil.lerString("Rua: ", TITULO_CADASTRO);
            String numero = InputUtil.lerString("Número: ", TITULO_CADASTRO);
            Categoria categoria = InputUtil.lerCategoria(TITULO_CADASTRO);
            String telefone = InputUtil.lerString("Telefone: ", TITULO_CADASTRO);
            String email = InputUtil.lerString("E-mail: ", TITULO_CADASTRO);
            String senha = InputUtil.lerString("Senha: ", TITULO_CADASTRO);

            lojaController.salvarLoja(nome, cnpj, cep, estado, cidade, bairro, rua, numero, categoria, telefone, email, senha);
            MessageUtil.plain("Loja adicionada com sucesso!", "Finalizado");
        } catch (ShopValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao cadastrar loja");
        }
    }

    private Loja realizarLogin() {
        try {
            String cnpj = InputUtil.lerString("CNPJ: ", "Login");
            String senha = InputUtil.lerString("Senha: ", "Login");


            return lojaController.login(cnpj, senha);
        } catch (ShopValidationException exception) {
            MessageUtil.error("CNPJ ou senha incorretos", "Erro de login");
        }

        return null;
    }

    public void relatorioGeral(Loja loja) {
        String relatorio = """
                ┌────────────────────────────────────────────────────────┐
                │ Nome: %s
                │ CNPJ: %s
                │ Endereço: %s
                │ Categoria: %s
                │ Telefone: %s
                │ E-mail: %s
                │ Status da Loja: %s
                │────────────────────────────────────────────────────────│
                │ Valor em caixa: %s
                └────────────────────────────────────────────────────────┘""".formatted(loja.getNome(),
                                                                                        loja.getCnpj(),
                                                                                        loja.getEndereco(),
                                                                                        loja.getCategoria(),
                                                                                        loja.getTelefone(),
                                                                                        loja.getEmail(),
                                                                                        loja.getStatus(),
                                                                                        PadronizarDadosUtil.normalizarSaldo(transacaoController.calcularValorTotalDeVendas()));

        MessageUtil.plain(relatorio, "Relatório geral");
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            opcao = InputUtil.lerInteiro(montarMenuPrincipal(), TITULO_SISTEMA);
            switch (opcao) {
                case 0 -> MessageUtil.plain("Encerrando sistema...", "Saindo");
                case 1 -> adicionarLoja();
                case 2 -> exibirMenuLoja();
                default -> MessageUtil.error("Opção inválida", "Erro");
            }
        } while(opcao != 0);
    }

    public void exibirMenuLoja() {
        Loja loja = realizarLogin();

        if(loja == null) {
            return;
        }

        int opcao;

        do {
            opcao = InputUtil.lerInteiro(montarMenuLoja(loja.getNome(), loja.getCaixa()), TITULO_SISTEMA);
            processarOpcaoMenuLoja(opcao, loja);
        } while (opcao!=0);
    }

    private void processarOpcaoMenuLoja(int opcao, Loja loja) {
        switch (opcao) {
            case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
            case 1 -> produtoView.cadastrarProduto(loja.getId());
            case 2 -> transacaoView.exibirMenuDeVenda(loja);
            case 3 -> produtoView.listarProdutos(loja.getId());
            case 4 -> relatorioGeral(loja);
            case 5 -> fornecedorView.menuDoFornecedor(loja);
            default -> MessageUtil.error("Opção inválida", "Erro");
        }
    }

    public String primeiroMenu() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                     MENU INICIAL
                │────────────────────────────────────────────────────────│
                │ 1. Entrar como cliente
                │ 2. Entrar como gerente
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    public String montarMenuPrincipal() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                     MENU INICIAL
                │────────────────────────────────────────────────────────│
                │ 1. Cadastrar nova loja
                │ 2. Acessar loja (login)
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    public String montarMenuLoja(String nome, BigDecimal saldo) {
        return """
                ┌────────────────────────────────────────────────────────┐
                │%s
                │Valor em caixa: %s
                │────────────────────────────────────────────────────────│
                │ 1. Cadastrar produto
                │ 2. Vender produtos
                │ 3. Listar produtos
                │ 4. Relatório de vendas
                │ 5. Fornecedores
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""".formatted(nome, PadronizarDadosUtil.normalizarSaldo(saldo));
    }
}
