package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Transacao;

import java.util.List;

public class TransacaoRepository {
    private final List<Transacao> transacoes;

    public TransacaoRepository(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> listar() {
        return transacoes;
    }
}
