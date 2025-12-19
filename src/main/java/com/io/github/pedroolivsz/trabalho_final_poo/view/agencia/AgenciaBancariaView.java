package com.io.github.pedroolivsz.trabalho_final_poo.view.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.AgenciaBancariaController;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.agencia.ContaController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.AccountException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.PersonValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

public class AgenciaBancariaView {
    private final AgenciaBancariaController agenciaBancariaController;
    private final ComprovanteView comprovanteView;

    public AgenciaBancariaView(AgenciaBancariaController agenciaBancariaController, ComprovanteView comprovanteView) {
        this.agenciaBancariaController = agenciaBancariaController;
        this.comprovanteView = comprovanteView;
    }

    public void criarConta() {
        try {
            String nome = InputUtil.lerString("Nome: ", "Cadastro pessoal");
            String CPF = InputUtil.lerString("CPF: ", "Cadastro pessoal");
            String endereco = InputUtil.lerString("Endereço: ", "Cadastro pessoal");
            String email = InputUtil.lerString("Email: ", "Cadastro pessoal");
            String senha = InputUtil.lerString("Senha: ", "Cadastro pessoal");

            agenciaBancariaController.criarConta(nome, CPF, endereco, email, senha);
        } catch (PersonValidationException | AccountException e) {
            MessageUtil.error(e.getMessage(), "Erro ao criar conta");
        }
    }

    public Conta logarConta() {
        try {
            String email = InputUtil.lerString("Email: ", "Login");
            String senha = InputUtil.lerString("Senha: ", "Login");

            return agenciaBancariaController.logarConta(email, senha);
        } catch (PersonValidationException | AccountException e) {
            MessageUtil.error(e.getMessage(), "Erro ao logar na conta.");
            return null;
        }
    }

    public void menuInicial() {
        int opcao;
        do {
            opcao = InputUtil.lerInteiro(MenuAgenciaView.montarMenuInicial(), "Agência Bancária");

            switch (opcao) {
                case 0 -> MessageUtil.plain("Encerrando operações...", "Saindo da conta");
                case 1 -> criarConta();
                case 2 -> menuConta();
                default -> MessageUtil.error("Opção inválida", "Erro");
            }
        } while (opcao != 0);
    }

    public void menuConta() {
        Conta proprietario = logarConta();

        if(proprietario == null) {
            return;
        }

        ContaController contaController = new ContaController(proprietario, agenciaBancariaController);
        ContaView contaView = new ContaView(contaController, comprovanteView);
        int opcao;

        do {
            opcao = InputUtil.lerInteiro(MenuAgenciaView.montarMenuDaConta(proprietario), "Agencia Bancaria");

            switch (opcao) {
                case 0 -> MessageUtil.plain("Saindo...", "Saindo da conta");
                case 1 -> contaView.depositar();
                case 2 -> contaView.sacar();
                case 3 -> contaView.transferir();
                default -> MessageUtil.error("Opção inválida", "Erro");
            }
        } while (opcao!=0);
    }
}
