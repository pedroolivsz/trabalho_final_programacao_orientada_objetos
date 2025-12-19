package com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia;

import java.time.LocalDate;

public class Comprovante {

    private static int gerarID = 1;
    private final int ID;
    private LocalDate dataDeLancamento;
    private Conta proprietario;
    private String numeroContaDestinatario;
    private String observacao;

    public Comprovante() {
        this.ID = gerarID;
        gerarID++;
    }

    public Comprovante(LocalDate dataDeLancamento,
                       Conta proprietario,
                       String numeroContaDestinatario) {
        this.ID = gerarID;
        gerarID++;
        this.dataDeLancamento = dataDeLancamento;
        this.proprietario = proprietario;
        this.numeroContaDestinatario = numeroContaDestinatario;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Conta getProprietario() {
        return proprietario;
    }

    public void setProprietario(Conta proprietario) {
        this.proprietario = proprietario;
    }

    public String getNumeroContaDestinatario() {
        return numeroContaDestinatario;
    }

    public void setNumeroContaDestinatario(String numeroContaDestinatario) {
        this.numeroContaDestinatario = numeroContaDestinatario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
