/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia;

import javax.swing.JOptionPane;

/**
 *
 * @author pedroolivsz
 */
public class Pix extends Pagamento {
    
    private String chave;

    public Pix() {
    }

    @Override
    public String exibirTipo() {
        return "Pix";
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    @Override
    public void confimacaoDePagamento() {
        JOptionPane.showMessageDialog(null, "O pagamento de R$" + valor + " foi concluido com sucesso para a chave Pix: " + chave, "Processamento de pagamento", JOptionPane.PLAIN_MESSAGE);
    }
    
}
