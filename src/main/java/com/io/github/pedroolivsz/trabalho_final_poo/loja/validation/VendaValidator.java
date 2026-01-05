package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.TipoPagamento;

import java.util.List;

public class VendaValidator {
    public static VendaValidation validar(List<Produto> produtos, TipoPagamento tipoPagamento) {
        if(produtos.isEmpty()) { return VendaValidation.LISTA_VAZIA; }
        if(tipoPagamento == null) { return VendaValidation.TIPO_DE_PAGAMENTO_INVALIDO; }

        return VendaValidation.SUCESSO;
    }
}
