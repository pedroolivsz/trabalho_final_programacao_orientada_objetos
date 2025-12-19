package com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia;

import java.math.BigDecimal;

public class Conta {
    private String numeroDaConta;
    private Pessoa proprietario;
    private BigDecimal saldo;

    public Conta() {
    }

    public Conta(String numeroDaConta, Pessoa proprietario, String numeroConta) {
    	this.numeroDaConta = numeroConta;
        this.proprietario = proprietario;
        this.saldo = BigDecimal.ZERO;
    }

    public String getNumeroDaConta() { return numeroDaConta; }

    public Pessoa getProprietario() { return proprietario; }

    public String getNomeProprietario() { return proprietario.getNome(); }

    public String getCpfProprietario() { return proprietario.getCPF(); }

    public String getEnderecoProprietario() { return proprietario.getEndereco(); }

    public String getEmailProprietario() { return proprietario.getEmail(); }

    public String getSenhaProprietario() { return proprietario.getSenha(); }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
