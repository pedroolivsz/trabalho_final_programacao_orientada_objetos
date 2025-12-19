package com.io.github.pedroolivsz.trabalho_final_poo.repository.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Loja;

import java.util.List;
import java.util.Optional;

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
