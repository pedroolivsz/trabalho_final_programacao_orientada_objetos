package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.TransacaoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.ShopValidator;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.VendaValidator;

import java.math.BigDecimal;

public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ProdutoService produtoService;

    public TransacaoService(TransacaoRepository transacaoRepository, ProdutoService produtoService) {
        this.transacaoRepository = transacaoRepository;
        this.produtoService = produtoService;
    }

    public void realizarVenda(Carrinho carrinho, TipoPagamento tipoPagamento, Loja loja) {
        ShopValidator.validarLojaAtiva(loja);
        VendaValidator.validarCarrinho(carrinho);
        VendaValidator.validarTipoPagamento(tipoPagamento);

        removerProdutosVendidos(loja.getId(), carrinho);

        loja.creditar(carrinho.calcularTotal());

        Transacao venda = new Transacao(loja.getId(), carrinho.getProdutos(), tipoPagamento, TipoTransacao.VENDA, carrinho.calcularTotal());

        transacaoRepository.salvar(venda);
    }

    public void adicionarProdutoAoCarrinho(long idLoja, Carrinho carrinho, String nome, int quantidade) {
        Produto item = produtoService.procurarPorNome(idLoja, nome);
        carrinho.adicionarProduto(item, quantidade);
    }

    private void removerProdutosVendidos(long idLoja, Carrinho carrinho) {
        for(ItemTransacao item : carrinho.getProdutos()) {
            produtoService.subtrairProdutosVendidosDoEstoque(idLoja, item.getProduto().getNome(), item.getQuantidade());
        }
    }

    public BigDecimal calcularValorTotalDeVendas() {
        return transacaoRepository.listar().stream()
                .filter(transacao -> transacao.getTipoTransacao() == TipoTransacao.VENDA)
                .map(Transacao::calcularTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
