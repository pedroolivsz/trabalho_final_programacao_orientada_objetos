package com.io.github.pedroolivsz.trabalho_final_poo.application;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.controller.loja.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.loja.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.loja.VendaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.loja.CodigoDeBarrasUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.service.loja.LojaService;
import com.io.github.pedroolivsz.trabalho_final_poo.service.loja.ProdutoService;
import com.io.github.pedroolivsz.trabalho_final_poo.service.loja.VendaService;
import com.io.github.pedroolivsz.trabalho_final_poo.view.loja.LojaView;
import com.io.github.pedroolivsz.trabalho_final_poo.view.loja.ProdutoView;
import com.io.github.pedroolivsz.trabalho_final_poo.view.loja.VendaView;

import java.util.ArrayList;
import java.util.List;

public class LojaApp {
    public static void main(String[] args) {
        List<Loja> lojas = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();

        CodigoDeBarrasUtil codigoDeBarrasUtil = new CodigoDeBarrasUtil();

        ProdutoRepository produtoRepository = new ProdutoRepository(produtos);
        ProdutoService produtoService = new ProdutoService(produtoRepository, codigoDeBarrasUtil);
        ProdutoController produtoController = new ProdutoController(produtoService);
        ProdutoView produtoView = new ProdutoView(produtoController);


        VendaRepository vendaRepository = new VendaRepository(vendas);
        VendaService vendaService = new VendaService(vendaRepository, produtoView);
        VendaController vendaController = new VendaController(vendaService);
        VendaView vendaView = new VendaView(vendaController, produtoView);

        LojaRepository lojaRepository = new LojaRepository(lojas);
        LojaService lojaService = new LojaService(lojaRepository);
        LojaController lojaController = new LojaController(lojaService);
        LojaView lojaView = new LojaView(lojaController, vendaController, produtoView, vendaView);

        lojaView.exibirMenuPrincipal();
    }
}
