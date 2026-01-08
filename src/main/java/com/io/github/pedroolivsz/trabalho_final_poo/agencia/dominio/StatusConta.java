package com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio;

public enum StatusConta {
    ATIVA(1),
    BLOQUEADA(2),
    PENDENTE(3);

    private final int idStatus;

    StatusConta(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }
}
