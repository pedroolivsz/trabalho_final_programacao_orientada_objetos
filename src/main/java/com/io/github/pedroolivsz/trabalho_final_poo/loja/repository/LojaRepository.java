package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;

import java.util.List;

public class LojaRepository {
    private final List<Loja> lojas;

    public LojaRepository(List<Loja> lojas) {
        this.lojas = lojas;
    }

    public void salvarLoja(Loja loja) {
        lojas.add(loja);
    }

    public List<Loja> listarLojas() {
        return lojas;
    }

    public Loja procurarPorCNPJ(String CNPJ) {
        Loja lojaProcurada = null;

        for(Loja loja : lojas) {
            if(loja.getCNPJ().equals(CNPJ)) {
                lojaProcurada = loja;
                break;
            }
        }

        return lojaProcurada;
    }
}
