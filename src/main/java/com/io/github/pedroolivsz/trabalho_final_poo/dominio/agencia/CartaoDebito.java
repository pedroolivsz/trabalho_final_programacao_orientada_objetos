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
public class CartaoDebito extends Pagamento {
    
    private String numeroCartao;

    public CartaoDebito() {
    }

    @Override
    public String exibirTipo() {
        return "Cartão de Débito";
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public void confimacaoDePagamento() {
        JOptionPane.showMessageDialog(null, "O pagamento de R$" + valor + " foi concluido com sucesso para o cartão de débito de Nº: " + numeroCartao
                , "Processamento de pagamento", JOptionPane.PLAIN_MESSAGE);
    }
    
    
}
