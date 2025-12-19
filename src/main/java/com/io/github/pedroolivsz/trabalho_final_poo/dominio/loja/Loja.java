package com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja;

/**
 *
 * @author pedroolivsz
 */
public class Loja {
    
    private String nome;
    private String localizacao;
    private String CNPJ;
    private String senha;

    public Loja() {
    }

    public Loja(String nome, String localizacao, String CNPJ, String senha) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.CNPJ = CNPJ;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
