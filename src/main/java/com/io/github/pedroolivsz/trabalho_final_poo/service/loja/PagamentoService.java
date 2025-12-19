package com.io.github.pedroolivsz.trabalho_final_poo.service.loja;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.CartaoCredito;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.CartaoDebito;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Pix;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;

public class PagamentoService {
	public Venda venda;

	public PagamentoService(Venda venda) {
		this.venda = venda;
	}
	
	public void pagarComPix(double valorASerPago) {
        Pix pix = new Pix();
        pix.setValor(valorASerPago);
        String chave = InputUtil.lerString("Insira a chave pix: ", "Dados do cliente");
        pix.setChave(chave);
        pix.confimacaoDePagamento();
    }

	public void pagarComCartaoDebito(double valorASerPago) {
        CartaoDebito cartaoDebito = new CartaoDebito();
        cartaoDebito.setValor(valorASerPago);
        String numCartaoDeb = InputUtil.lerString("Insira o número do cartão: ", "Informações de pagamento");
        cartaoDebito.setNumeroCartao(numCartaoDeb);
        cartaoDebito.confimacaoDePagamento();
    }
	
	public void pagarComCartaoCredito(double valorASerPago) {
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setValor(valorASerPago);
        String numCartaoCred = InputUtil.lerString("Insira o número do cartão: ", "Dados do cliente");
        cartaoCredito.setNumeroCartao(numCartaoCred);
        cartaoCredito.confimacaoDePagamento();
    }
}
