package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Categoria;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
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

            lojaController.login(cnpj, senha);
            return lojaController.procuraPorCnpj(cnpj);
        } catch (ShopValidationException exception) {
            MessageUtil.error("CNPJ ou senha incorretos", "Erro de login");
        }

        return null;
    }

    public void relatorioGeral(Loja loja) {
        /**StringBuilder textoRelatorio = new StringBuilder();
        textoRelatorio.append("Nome da loja: ").append(loja.getNome()).append("\n");
        textoRelatorio.append("CNPJ: ").append(loja.getCnpj()).append("\n");
        textoRelatorio.append("Localização: ").append(loja.getCidade()).append("\n");

        textoRelatorio.append("---\n");

        textoRelatorio.append(produtoView.formatarListaProdutos());

        textoRelatorio.append("\n---\n");

        textoRelatorio.append("Valor total de vendas: R$").append(vendaController.calcularValorTotalDeVendas());**/

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
                │ Valor em caixa: R$ %s
                └────────────────────────────────────────────────────────┘""".formatted(loja.getNome(),
                                                                                        loja.getCnpj(),
                                                                                        loja.getEndereco(),
                                                                                        loja.getCategoria(),
                                                                                        loja.getTelefone(),
                                                                                        loja.getEmail(),
                                                                                        loja.getStatus(),
                                                                                        vendaController.calcularValorTotalDeVendas());

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
            opcao = InputUtil.lerInteiro(montarMenuLoja(loja.getNome(), loja.getCnpj()), TITULO_SISTEMA);
            processarOpcaoMenuLoja(opcao, loja);
        } while (opcao!=0);
    }

    private void processarOpcaoMenuLoja(int opcao, Loja loja) {
        switch (opcao) {
            case 0 -> MessageUtil.plain("Saindo...", "Voltando a página anterior");
            case 1 -> produtoView.cadastrarProduto();
            case 2 -> vendaView.exibirMenuDeVenda(loja);
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
