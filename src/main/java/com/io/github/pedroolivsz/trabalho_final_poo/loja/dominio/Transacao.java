package com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.TransactionValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Transacao {
    private final UUID id;
    private long idLoja;
    private List<ItemTransacao> itens;
    private TipoPagamento tipoPagamento;
    private TipoTransacao tipoTransacao;
    private LocalDate dataDaTransacao;
    private BigDecimal valorTotal;
    private StatusTransacao status;

    public Transacao(long idLoja,
                     List<ItemTransacao> itens,
                     TipoPagamento tipoPagamento,
                     TipoTransacao tipoTransacao,
                     BigDecimal valorTotal) {
        this.id = UUID.randomUUID();
        this.idLoja = idLoja;
        this.itens = itens;
        this.tipoPagamento = tipoPagamento;
        this.tipoTransacao = tipoTransacao;
        this.dataDaTransacao = LocalDate.now();
        this.valorTotal = valorTotal;
        this.status = StatusTransacao.PENDENTE;
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemTransacao::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void finalizar() {
        if(status != StatusTransacao.PENDENTE) throw new TransactionValidationException("Transação não pode ser finalizada");
        this.status = StatusTransacao.FINALIZADA;
    }

    public void cancelar() {
        if(status != StatusTransacao.PENDENTE) throw new TransactionValidationException("Transação não pode ser finalizada");
        this.status = StatusTransacao.CANCELADA;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public List<ItemTransacao> getItens() {
        return itens;
    }

    public long getIdLoja() {
        return idLoja;
    }
}
