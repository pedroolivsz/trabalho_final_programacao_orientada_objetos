package com.io.github.pedroolivsz.trabalho_final_poo.loja.view;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.AgenciaBancariaService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Cliente;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.LojaService;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

import java.util.List;

public class ClienteView {
    private final TransacaoView transacaoView;
    private final ProdutoView produtoView;
    private final LojaController lojaController;
    private final AgenciaBancariaService agenciaBancariaService;
    private Cliente clienteLogado;

    public ClienteView(TransacaoView transacaoView, ProdutoView produtoView, LojaController lojaController, AgenciaBancariaService agenciaBancariaService) {
        this.transacaoView = transacaoView;
        this.produtoView = produtoView;
        this.lojaController = lojaController;
        this.agenciaBancariaService = agenciaBancariaService;
    }

    private void logar() {
        String nome = InputUtil.lerString("Nome: ", "Dados de login");
        String numeroConta = InputUtil.lerString("Número da conta bancaria: ", "Dados de login");

        Conta conta = agenciaBancariaService.procurarPorNumeroDaConta(numeroConta);

        if(conta == null) {
            this.clienteLogado = null;
            return;
        }

        Cliente cliente = new Cliente(conta.getNomeProprietario(),
                conta.getCpfProprietario(),
                conta.getEnderecoProprietario(),
                conta.getEmailProprietario(),
                conta.getSenhaProprietario(),
                conta);

        this.clienteLogado = cliente;
    }

    public void menuCliente() {
        logar();

        if(this.clienteLogado == null) {
            MessageUtil.error("Conta não encontrada. É necessaria uma conta bancaria para continuar", "Erro ao logar");
            return;
        }

        List<Loja> lojas = lojaController.listar();

        if(lojas.isEmpty()) {
            MessageUtil.plain("Nenhuma loja disponivel", "Volte mais tarde");
            return;
        }

        Loja loja = InputUtil.selecionarObjeto(lojas, "Deseja entrar em qual loja?", "Shopping");

        transacaoView.exibirMenuDeVenda(loja, clienteLogado);
    }

    private String montarMenuCliente() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                    Menu do cliente
                │────────────────────────────────────────────────────────│
                │ 1. Listar produtos
                │ 2. Escolher produto
                │ 3. Carrinho
                │ 4. Finalizar compra
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }
}
