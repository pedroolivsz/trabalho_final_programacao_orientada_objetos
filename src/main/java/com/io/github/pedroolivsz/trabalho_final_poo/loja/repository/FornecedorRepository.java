package com.io.github.pedroolivsz.trabalho_final_poo.loja.repository;

import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorRepository {
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public FornecedorRepository(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void salvar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public List<Fornecedor> listar() {
        return fornecedores;
    }

    public Fornecedor procurarPorCnpj(String cnpj) {
        return fornecedores.stream()
                .filter(fornecedor -> fornecedor.getLoja().getCnpj().equals(cnpj))
                .findFirst()
                .orElse(null);
    }
}
