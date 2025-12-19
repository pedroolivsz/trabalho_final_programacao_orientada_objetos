package com.io.github.pedroolivsz.trabalho_final_poo.view.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.loja.ShopValidation;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

public class LojaView {
    private final LojaController lojaController;
    private final VendaController vendaController;
    private final ProdutoView produtoView;
    private final VendaView vendaView;

    public LojaView(LojaController lojaController, VendaController vendaController, ProdutoView produtoView, VendaView vendaView) {
        this.lojaController = lojaController;
        this.vendaController = vendaController;
        this.produtoView = produtoView;
        this.vendaView = vendaView;
    }

    private static final String TITULO_SISTEMA = "Sistema de Loja";
    private static final String TITULO_CADASTRO = "Cadastro de Loja";

    public void relatorioGeral(Loja loja) {
        StringBuilder textoRelatorio = new StringBuilder();
        textoRelatorio.append("Nome da loja: ").append(loja.getNome()).append("\n");
        textoRelatorio.append("CNPJ: ").append(loja.getCNPJ()).append("\n");
        textoRelatorio.append("Localização: ").append(loja.getLocalizacao()).append("\n");

        textoRelatorio.append("---\n");

        textoRelatorio.append(produtoView.formatarListaProdutos());

        textoRelatorio.append("\n---\n");

        textoRelatorio.append("Valor total de vendas: R$").append(vendaController.calcularValorTotalDeVendas());

        MessageUtil.plain(textoRelatorio.toString(), "Relatório geral");
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

    public void adicionarLoja() {
        String nome = InputUtil.lerString("Nome: ", TITULO_CADASTRO);
        String localizacao = InputUtil.lerString("Localização: ", TITULO_CADASTRO);
        String cnpj = InputUtil.lerString("CNPJ: ", TITULO_CADASTRO);
        String senha = InputUtil.lerString("Senha: ", TITULO_CADASTRO);

        lojaController.salvarLoja(nome, localizacao, cnpj, senha);
        MessageUtil.plain("Loja adicionada com sucesso!", "Finalizado");
    }

    public void exibirMenuLoja() {
        Loja loja = realizarLogin();

        if(loja == null) {
            MessageUtil.error("CNPJ ou senha inválidos", "Erro de login");
            return;
        }

        int opcao;

        do {
            opcao = InputUtil.lerInteiro(montarMenuLoja(loja.getNome(), loja.getCNPJ()), TITULO_SISTEMA);
            processarOpcaoMenuLoja(opcao, loja);
        } while (opcao!=0);
    }

    private Loja realizarLogin() {
        String cnpj = InputUtil.lerString("CNPJ: ", "Login");
        String senha = InputUtil.lerString("Senha: ", "Login");

        ShopValidation validation = lojaController.login(cnpj, senha);

        if(validation != ShopValidation.SUCESSO) {
            MessageUtil.error(validation.getMessage(), "Erro");
            return null;
        }

        return lojaController.procurarLoja(cnpj);
    }

    private void processarOpcaoMenuLoja(int opcao, Loja loja) {
        switch (opcao) {
            case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
            case 1 -> produtoView.criarProduto();
            case 2 -> vendaView.exibirMenuDeVenda();
            case 3 -> produtoView.listarProdutos();
            case 4 -> relatorioGeral(loja);
            default -> MessageUtil.error("Opção inválida", "Erro");
        }
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

    public String montarMenuLoja(String nome, String CNPJ) {
        return """
                ┌────────────────────────────────────────────────────────┐
                │%s - %s
                │────────────────────────────────────────────────────────│
                │ 1. Cadastrar produto
                │ 2. Vender produtos
                │ 3. Listar produtos
                │ 4. Relatório de vendas
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""".formatted(nome, CNPJ);
    }
}
