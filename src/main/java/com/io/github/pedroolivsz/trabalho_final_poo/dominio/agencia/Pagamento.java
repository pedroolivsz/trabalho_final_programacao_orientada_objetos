/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia;

/**
 *
 * @author pedroolivsz
 */
public abstract class Pagamento {
    
    protected double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    public Pagamento() {
    }

    public abstract String exibirTipo();

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public abstract void confimacaoDePagamento();

}
