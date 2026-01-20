package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.OutfitterValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.FornecedorController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Fornecedor;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.util.List;

public class FornecedorView {
    private final FornecedorController fornecedorController;
    private final TransacaoView transacaoView;

    public FornecedorView(FornecedorController fornecedorController, TransacaoView transacaoView) {
        this.fornecedorController = fornecedorController;
        this.transacaoView = transacaoView;
    }

    public void menuDoFornecedor(Loja loja) {
        int opcao;
        try {
            do {
                if(!fornecedorController.verificarSeEFornecedor(loja.getCnpj())) {
                    opcao = InputUtil.lerInteiro(montarMenuDoFornecedorSemCadastro(), "Menu do fornecedor");
                    switch (opcao) {
                        case 0 -> MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
                        case 1 -> cadastrarFornecedor(loja.getCnpj());
                        case 2 -> comprarDeFornecedor(loja);
                        default -> MessageUtil.error("Opção inválida", "Erro");
                    }
                }

                if(fornecedorController.verificarSeEstaDesativado(loja.getCnpj())) {
                    opcao = InputUtil.lerInteiro(montarMenuDoFornecedorDesativado(), "Menu do fornecedor");
                    switch (opcao) {
                        case 0 -> MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
                        case 1 -> reativarFornecedor(loja.getCnpj());
                        case 2 -> comprarDeFornecedor(loja);
                        default -> MessageUtil.error("Opção inválida", "Erro");
                    }
                }

                else {
                    opcao = InputUtil.lerInteiro(montarMenuDoFornecedorAtivo(), "Menu do fornecedor");
                    switch (opcao) {
                        case 0 -> MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
                        case 1 -> desativarCadastroDeFornecedor(loja.getCnpj());
                        case 2 -> comprarDeFornecedor(loja);
                        default -> MessageUtil.error("Opção inválida", "Erro");
                    }
                }
            } while (opcao != 0);
        } catch (OutfitterValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro");
        }
    }

    public void cadastrarFornecedor(String cnpj) {
        try {
            int confirmacao = InputUtil.lerConfirmacao("Deseja se tornar um fornecedor?",
                                                        "Cadastro de fornecedores");
            if(confirmacao == 0) {
                fornecedorController.criarFornecedor(cnpj);
                MessageUtil.plain("Parabéns, você agora é um fornecedor", "Cadastro de fornecedores");
            }

            else MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
        } catch (OutfitterValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao se cadastrar como fornecedor");
        }
    }

    public void comprarDeFornecedor(Loja loja) {
        try {
            List<Fornecedor> fornecedores = fornecedorController.listar();

            if(fornecedores.isEmpty()) {
                MessageUtil.error("Nenhum fornecedor disponivel. Tente novamente mais tarde", "Erro");
                return;
            }

            Fornecedor fornecedor = InputUtil.selecionarObjeto(fornecedores, "Escolha o fornecedor: ", "Shopping");

            transacaoView.exibirMenuDeComprasEntreLojas(loja, fornecedor.getLoja());
        } catch (IllegalArgumentException exception) {
            MessageUtil.error(exception.getMessage(), "Voltando");
        }

    }

    public void desativarCadastroDeFornecedor(String cnpj) {
        try {
            int confirmacao = InputUtil.lerConfirmacao("Deseja desativar seu cadastro de fornecedor?",
                    "Desativação do cadastro de fornecedor");
            if(confirmacao == 0) {
                fornecedorController.desativarFornecedor(cnpj);
                MessageUtil.plain("Seu cadastro foi desativado", "Cadastro de fornecedores");
            }

            else MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
        } catch (OutfitterValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro desativar cadastro como fornecedor");
        }
    }

    public void reativarFornecedor(String cnpj) {
        try {
            int confirmacao = InputUtil.lerConfirmacao("Deseja reativar seu cadastro de fornecedor?",
                    "Reativação do cadastro de fornecedor");
            if(confirmacao == 0) {
                fornecedorController.reativarFornecedor(cnpj);
                MessageUtil.plain("Seu cadastro foi reativado", "Cadastro de fornecedores");
            }

            else MessageUtil.plain("Retornando a página anterior...", "Redirecionamento");
        } catch (OutfitterValidationException exception) {
            MessageUtil.error(exception.getMessage(), "Erro ao reativar cadastro como fornecedor");
        }
    }

    public String montarMenuDoFornecedorSemCadastro() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                 Menu do fornecedor
                │────────────────────────────────────────────────────────│
                │ 1. Cadastrar-se como fornecedor
                │ 2. Comprar produtos
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    public String montarMenuDoFornecedorDesativado() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                 Menu do fornecedor
                │────────────────────────────────────────────────────────│
                │ 1. Reativar cadastro
                │ 2. Comprar produtos
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }

    public String montarMenuDoFornecedorAtivo() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                 Menu do fornecedor
                │────────────────────────────────────────────────────────│
                │ 1. Desativar cadastro
                │ 2. Comprar produtos
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }
}
