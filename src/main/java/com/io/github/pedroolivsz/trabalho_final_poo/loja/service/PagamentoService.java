package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.CartaoCredito;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.CartaoDebito;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Pix;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.util.InputUtil;

import java.math.BigDecimal;

public class PagamentoService {
	/**public Venda venda;

	public PagamentoService(Venda venda) {
		this.venda = venda;
	}

    public void processarTransacaoComum(Loja loja, BigDecimal valor) {
        loja.creditar(valor);
    }

    public void processarTransacaoEntreLojas(Loja comprador, Loja fornecedor, BigDecimal valor) {
        comprador.debitar(valor);
        fornecedor.creditar(valor);
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
    }**/
}
