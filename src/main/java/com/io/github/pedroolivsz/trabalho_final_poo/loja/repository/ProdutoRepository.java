package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;

import java.util.List;

public class ProdutoRepository {
    private final List<Produto> produtos;

    public ProdutoRepository(List<Produto> produtos) {
        this.produtos = produtos;

        for(Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void editarProduto(Produto produtoEditado) {
        for(Produto produto : produtos) {
            if(produto.getCodigoDeBarras().equals(produtoEditado.getCodigoDeBarras())) {
                produto.setQuantidade(produtoEditado.getQuantidade());
                return;
            }
        }
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto procurarProdutoPorNome(String nome) {
        Produto procurarProduto = null;

        for(Produto produto : produtos) {
            if(produto.getNome().equals(nome)) {
                procurarProduto = produto;
                break;
            }
        }

        return procurarProduto;
    }
}
