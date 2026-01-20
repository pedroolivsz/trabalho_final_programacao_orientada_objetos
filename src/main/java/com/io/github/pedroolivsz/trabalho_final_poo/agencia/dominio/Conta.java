package com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio;

import java.math.BigDecimal;

public class Conta {
    private String numeroDaConta;
    private Pessoa proprietario;
    private BigDecimal saldo;
    private StatusConta status;

    public Conta() {
    }

    public Conta(Pessoa proprietario, String numeroConta) {
    	this.numeroDaConta = numeroConta;
        this.proprietario = proprietario;
        this.saldo = BigDecimal.ZERO;
        this.status = StatusConta.ATIVA;
    }

    public void creditar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void debitar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
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
