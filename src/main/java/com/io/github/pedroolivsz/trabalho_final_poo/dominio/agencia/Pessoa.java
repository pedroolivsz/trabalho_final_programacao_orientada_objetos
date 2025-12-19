package com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia;

public class Pessoa {
    private static int ID = 0;
    private String nome;
    private String CPF;
    private String endereco;
    private String email;
    private String senha;

    public Pessoa() {
        ID++;
    }

    public Pessoa(String nome, String CPF, String endereco, String email, String senha) {
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        ID++;
    }

    public static int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
