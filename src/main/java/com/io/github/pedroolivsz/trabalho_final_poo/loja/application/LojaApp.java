package com.io.github.pedroolivsz.trabalho_final_poo.loja.application;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.FornecedorController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.*;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.FornecedorRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.FornecedorService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.FornecedorView;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.LojaService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.ProdutoService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.VendaService;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.LojaView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.ProdutoView;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.view.VendaView;

import java.util.ArrayList;
import java.util.List;

public class LojaApp {
    public static void main(String[] args) {
        List<Loja> lojas = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
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


        FornecedorRepository fornecedorRepository = new FornecedorRepository(fornecedores);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepository, lojaService);
        FornecedorController fornecedorController = new FornecedorController(fornecedorService);
        FornecedorView fornecedorView = new FornecedorView(fornecedorController);

        VendaRepository vendaRepository = new VendaRepository(vendas);
        VendaService vendaService = new VendaService(vendaRepository, produtoService);
        VendaController vendaController = new VendaController(vendaService);
        VendaView vendaView = new VendaView(vendaController, produtoView);

        LojaView lojaView = new LojaView(lojaController, vendaController, produtoView, vendaView, fornecedorView);

        lojaView.exibirMenuPrincipal();
    }
}
