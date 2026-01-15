package com.io.github.pedroolivsz.trabalho_final_poo.integrar.application;

import com.io.github.pedroolivsz.trabalho_final_poo.integrar.view.ProjetosView;

public class ProjetosApp {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        ProjetosView projetosView = config.configurarProjetos();

        projetosView.menuPricipal();
    }
}
