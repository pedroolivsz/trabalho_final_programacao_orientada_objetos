package com.io.github.pedroolivsz.trabalho_final_poo.loja.application;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.*;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;

import java.util.ArrayList;
import java.util.List;

public class LojaApp {
    public static void main(String[] args) {
        List<Loja> lojas = new ArrayList<>();
        List<Transacao> transacoes = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Fornecedor> fornecedores = new ArrayList<>();

        CodigoDeBarrasUtil codigoDeBarrasUtil = new CodigoDeBarrasUtil();

        ProdutoRepository produtoRepository = new ProdutoRepository(produtos);
        ProdutoService produtoService = new ProdutoService(produtoRepository, codigoDeBarrasUtil);
        ProdutoController produtoController = new ProdutoController(produtoService);
        ProdutoView produtoView = new ProdutoView(produtoController);

        LojaRepository lojaRepository = new LojaRepository(lojas);
        LojaService lojaService = new LojaService(lojaRepository);
        lojaService.salvarLoja("Madman Company", "12345678912345", "54321", "Rj", "Leblon", "Faz me rir", "Rua dos banceiros", "696", Categoria.ELETRONICOS, "(88) 90198-8255", "jp@gmail.com", "dede");
        lojaService.salvarLoja("Del Nobre", "98765432112345", "12345", "Sp", "Do quebra", "vai quem quer", "Monte dos urubus", "969", Categoria.ELETRONICOS, "(88) 98198-8255", "dede@gmail.com", "jp");
        LojaController lojaController = new LojaController(lojaService);

        TransacaoRepository transacaoRepository = new TransacaoRepository(transacoes);
        TransacaoService transacaoService = new TransacaoService(transacaoRepository, produtoService);
        TransacaoController transacaoController = new TransacaoController(transacaoService);
        TransacaoView transacaoView = new TransacaoView(transacaoController, produtoView);

        FornecedorRepository fornecedorRepository = new FornecedorRepository(fornecedores);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepository, lojaService);
        FornecedorController fornecedorController = new FornecedorController(fornecedorService);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController, transacaoView);

        LojaView lojaView = new LojaView(lojaController, transacaoController, produtoView, transacaoView, fornecedorView);

        lojaView.exibirMenuPrincipal();
    }
}
