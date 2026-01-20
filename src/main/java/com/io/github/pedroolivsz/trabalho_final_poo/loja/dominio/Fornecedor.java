package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

public class Fornecedor {
    private Loja loja;
    private StatusLoja status;

    public Fornecedor(Loja loja) {
        this.loja = loja;
        this.status = StatusLoja.ATIVA;
    }

    public Loja getLoja() {
        return this.loja;
    }

    public boolean estaAtivo() {
        return status == StatusLoja.ATIVA && !loja.estaBloqueada();
    }

    public void desativar() {
        this.status = StatusLoja.BLOQUEADA;
    }

    public void reativar() {
        this.status = StatusLoja.ATIVA;
    }

    @Override
    public String toString() {
        return loja.getNome();
    }
}
