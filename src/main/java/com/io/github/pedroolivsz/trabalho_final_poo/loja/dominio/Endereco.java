package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public class Endereco {
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco() {
    }

    public Endereco(String cep, String estado, String cidade, String bairro, String rua, String numero) {
        super();
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return  cep + ", " +
                estado + ", " +
                cidade + ", " +
                bairro + ", " +
                rua + ", " +
                "Nº " + numero;
    }
}