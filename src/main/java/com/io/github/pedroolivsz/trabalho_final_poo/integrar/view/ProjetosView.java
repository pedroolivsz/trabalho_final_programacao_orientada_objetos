package com.io.github.pedroolivsz.trabalho_final_poo.integrar.view;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.view.AgenciaBancariaView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.LojaView;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.util.MessageUtil;

public class ProjetosView {
    private final LojaView lojaView;
    private final AgenciaBancariaView agenciaBancariaView;

    public ProjetosView(LojaView lojaView, AgenciaBancariaView agenciaBancariaView) {
        this.lojaView = lojaView;
        this.agenciaBancariaView = agenciaBancariaView;
    }

    public void menuPricipal() {
        int opcao;

        do {
            opcao = InputUtil.lerInteiro(montarMenuPrincipal(), "Escolha");
            switch (opcao) {
                case 0 -> MessageUtil.plain("Encerrando sistema...", "Saindo");
                case 1 -> agenciaBancariaView.menuInicial();
                case 2 -> lojaView.exibirMenuGeral();
                default -> MessageUtil.error("Opção inválida", "Tente novamente");
            }
        } while (opcao != 0);
    }

    private String montarMenuPrincipal() {
        return """
                ┌────────────────────────────────────────────────────────┐
                │                     MENU INICIAL
                │────────────────────────────────────────────────────────│
                │ 1. Agência Bancaria
                │ 2. Loja (Sistema de varejo)
                │ 0. Sair
                └────────────────────────────────────────────────────────┘""";
    }
}
