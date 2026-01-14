package com.io.github.pedroolivsz.trabalho_final_poo.loja.controller;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Fornecedor;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.service.FornecedorService;

public class FornecedorController {
    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    public Fornecedor criarFornecedor(String cnpj) {
        return fornecedorService.criarFornecedor(cnpj);
    }

    public void desativarFornecedor(String cnpj) {
        fornecedorService.desativarFornecedor(cnpj);
    }

    public void reativarFornecedor(String cnpj) {
        fornecedorService.reativarFornecedor(cnpj);
    }

    public boolean verificarSeEFornecedor(String cnpj) {
        return fornecedorService.verificarSeEFornecedor(cnpj);
    }

    public boolean verificarSeEstaDesativado(String cnpj) {
        return fornecedorService.verificarSeEstaDesativado(cnpj);
    }

    public Fornecedor procurarPorCnpj(String cnpj) {
        return fornecedorService.procurarPorCnpj(cnpj);
    }
}
