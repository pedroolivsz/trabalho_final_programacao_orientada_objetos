package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.TransacaoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.BuyValidator;
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

        loja.getContaBancaria().creditar(carrinho.calcularTotal());
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

    public void realizarCompra(Carrinho carrinho, TipoPagamento tipoPagamento, Loja fornecedor, Loja comprador) {
        ShopValidator.validarExistenciaDeLoja(comprador);
        ShopValidator.validarExistenciaDeLoja(fornecedor);
        BuyValidator.validarTipoPagamento(tipoPagamento);
        VendaValidator.validarCarrinho(carrinho);

        BigDecimal total = carrinho.calcularTotal();

        comprador.getContaBancaria().debitar(total);
        comprador.debitar(total);

        removerProdutosVendidos(fornecedor.getId(), carrinho);

        adicionarProdutosDoComprador(comprador.getId(), fornecedor.getId(), carrinho);

        Transacao compra = new Transacao(comprador.getId(),
                carrinho.getProdutos(),
                tipoPagamento,
                TipoTransacao.COMPRA,
                total);

        Transacao venda = new Transacao(fornecedor.getId(),
                carrinho.getProdutos(),
                tipoPagamento,
                TipoTransacao.VENDA,
                total);

        fornecedor.getContaBancaria().creditar(total);
        fornecedor.creditar(total);

        transacaoRepository.salvar(compra);
        transacaoRepository.salvar(venda);
    }


    private void adicionarProdutosDoComprador(long idComprador, long idFornecedor, Carrinho carrinho) {
        for(ItemTransacao item : carrinho.getProdutos()) {
            produtoService.adicionarEstoque(idComprador, idFornecedor, item.getProduto().getNome(), item.getQuantidade());
        }
    }
}
