package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Pessoa;

public class Cliente extends Pessoa {
    private Conta contaBancaia;

    public Cliente(String nome, String CPF, String endereco, String email, String senha, Conta contaBancaia) {
        super(nome, CPF, endereco, email, senha);
        this.contaBancaia = contaBancaia;
    }

    public Conta getContaBancaia() {
        return contaBancaia;
    }

    public void setContaBancaia(Conta contaBancaia) {
        this.contaBancaia = contaBancaia;
    }
}
