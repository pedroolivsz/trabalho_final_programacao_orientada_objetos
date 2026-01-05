package com.io.github.pedroolivsz.trabalho_final_poo.loja.application;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.LojaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.ProdutoController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.controller.VendaController;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Produto;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Venda;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.LojaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.ProdutoRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.VendaRepository;
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
