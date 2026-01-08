package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import java.math.BigDecimal;

public class Loja {
    private String nome;
    private String cnpj;
    private Endereco endereco;
    private Categoria categoria;
    private Contatos contatos;
    private StatusLoja status;
    private String senha;
    private BigDecimal caixa;

    private int operacoesComCaixaZerado;

    public Loja() {
    }

    public Loja(String nome, String cnpj, Endereco endereco, Categoria categoria, Contatos contatos, StatusLoja status,
                String senha, BigDecimal caixa) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.categoria = categoria;
        this.contatos = contatos;
        this.status = status;
        this.senha = senha;
        this.caixa = caixa;
    }

    public void creditar(BigDecimal valor) {
        this.caixa = this.caixa.add(valor);
        verificarBloqueio();
    }

    public void debitar(BigDecimal valor) {
        this.caixa = this.caixa.subtract(valor);
        verificarBloqueio();
    }

    private void verificarBloqueio() {
        if(caixa.compareTo(BigDecimal.ZERO) == 0) {
            operacoesComCaixaZerado++;
        } else if(caixa.compareTo(BigDecimal.ZERO) != 0) {
            operacoesComCaixaZerado = 0;
        }

        if(operacoesComCaixaZerado >= 3) {
            this.status = StatusLoja.BLOQUEADA;
        }
    }

    public boolean estaBloqueada() {
        return this.status == StatusLoja.BLOQUEADA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cNPJ) {
        cnpj = cNPJ;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return endereco.getCep();
    }

    public void setCep(String cep) {
        this.endereco.setCep(cep);
    }

    public String getEstado() {
        return endereco.getEstado();
    }

    public void setEstado(String estado) {
        this.endereco.setEstado(estado);
    }

    public String getCidade() {
        return endereco.getCidade();
    }

    public void setCidade(String cidade) {
        this.endereco.setCidade(cidade);
    }

    public String getBairro() {
        return endereco.getBairro();
    }

    public void setBairro(String bairro) {
        this.endereco.setBairro(bairro);
    }

    public String getRua() {
        return endereco.getRua();
    }

    public void setRua(String rua) {
        this.endereco.setRua(rua);
    }

    public String getNumero() {
        return endereco.getNumero();
    }

    public void setNumero(String numero) {
        this.endereco.setCep(numero);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setContatos(Contatos contatos) {
        this.contatos = contatos;
    }

    public String getTelefone() {
        return contatos.getTelefone();
    }

    public void setTelefone(String telefone) {
        this.contatos.setTelefone(telefone);
    }

    public String getEmail() {
        return contatos.getEmail();
    }

    public void setEmail(String email) {
        this.contatos.setEmail(email);
    }

    public StatusLoja getStatus() {
        return status;
    }

    public void setStatus(StatusLoja status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getCaixa() {
        return caixa;
    }

    public void setCaixa(BigDecimal caixa) {
        this.caixa = caixa;
    }
}