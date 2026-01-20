package com.io.github.pedroolivsz.trabalho_final_poo.integrar.application;

import com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller.AgenciaBancariaController;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.controller.ComprovanteController;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Comprovante;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.dominio.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.repository.AgenciaBancariaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.repository.ComprovanteRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.AgenciaBancariaService;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.service.ComprovanteService;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.view.AgenciaBancariaView;
import com.io.github.pedroolivsz.trabalho_final_poo.agencia.view.ComprovanteView;
import com.io.github.pedroolivsz.trabalho_final_poo.integrar.view.ProjetosView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.FornecedorController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.TransacaoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.FornecedorRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.TransacaoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.FornecedorService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.LojaService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.ProdutoService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.TransacaoService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.FornecedorView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.LojaView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.ProdutoView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.TransacaoView;

import java.util.ArrayList;
import java.util.List;

public class ProjetosApp {
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        List<Comprovante> comprovantes = new ArrayList<>();

        ComprovanteRepository comprovanteRepository = new ComprovanteRepository(comprovantes);
        ComprovanteService comprovanteService = new ComprovanteService(comprovanteRepository);
        ComprovanteController comprovanteController = new ComprovanteController(comprovanteService);
        ComprovanteView comprovanteView = new ComprovanteView(comprovanteController);

        AgenciaBancariaRepository agenciaBancariaRepository = new AgenciaBancariaRepository(contas);
        AgenciaBancariaService agenciaBancariaService = new AgenciaBancariaService(agenciaBancariaRepository);
        /**agenciaBancariaService.criarConta("Skullka", "123.456.789-10", "Crato", "jp", "dede");
        agenciaBancariaService.criarConta("Wandinha", "109.876.543-21", "Crato", "dede", "jp");**/
        AgenciaBancariaController agenciaBancariaController = new AgenciaBancariaController(agenciaBancariaService);

        AgenciaBancariaView agenciaBancariaView = new AgenciaBancariaView(agenciaBancariaController, comprovanteView);

        List<Loja> lojas = new ArrayList<>();
        List<Transacao> transacoes = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Fornecedor> fornecedores = new ArrayList<>();

        CodigoDeBarras codigoDeBarras = new CodigoDeBarras();

        ProdutoRepository produtoRepository = new ProdutoRepository(produtos);
        ProdutoService produtoService = new ProdutoService(produtoRepository, codigoDeBarras);
        ProdutoController produtoController = new ProdutoController(produtoService);
        ProdutoView produtoView = new ProdutoView(produtoController);

        LojaRepository lojaRepository = new LojaRepository(lojas);
        LojaService lojaService = new LojaService(lojaRepository, agenciaBancariaService);
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

        ProjetosView projetosView = new ProjetosView(lojaView, agenciaBancariaView);

        projetosView.menuPricipal();
    }
}
