package com.io.github.pedroolivsz.trabalho_final_poo.loja.service;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.OutfitterValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Fornecedor;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.repository.FornecedorRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.validation.OutfitterValidator;

public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final LojaService lojaService;

    public FornecedorService(FornecedorRepository fornecedorRepository, LojaService lojaService) {
        this.fornecedorRepository = fornecedorRepository;
        this.lojaService = lojaService;
    }

    public Fornecedor criarFornecedor(String cnpjLoja) {
        Loja loja = lojaService.procurarPorCNPJ(cnpjLoja);
        OutfitterValidator.validarSeApto(loja);
        if(fornecedorRepository.procurarPorCnpj(loja.getCnpj()) != null) throw new OutfitterValidationException("Loja já é fornecedora");

        Fornecedor fornecedor = new Fornecedor(loja);
        fornecedorRepository.salvar(fornecedor);

        return fornecedor;
    }

    public void desativarFornecedor(String cnpj) {
        Fornecedor fornecedor = fornecedorRepository.procurarPorCnpj(cnpj);

        if(fornecedor == null) throw new OutfitterValidationException("Você ainda não é um fornecedor");

        fornecedor.desativar();
    }

    public void reativarFornecedor(String cnpj) {
        Fornecedor fornecedor = fornecedorRepository.procurarPorCnpj(cnpj);

        if(!verificarSeEstaDesativado(cnpj)) throw new OutfitterValidationException("Você não está com o cadastro desativado");

        fornecedor.reativar();
    }

    public boolean verificarSeEFornecedor(String cnpjLoja) {
        if(cnpjLoja == null) throw new OutfitterValidationException("Erro ao verificar loja");
        return fornecedorRepository.procurarPorCnpj(cnpjLoja) != null;
    }

    public boolean verificarSeEstaDesativado(String cnpjLoja) {
        if(cnpjLoja == null) throw new OutfitterValidationException("Erro ao verificar loja");
        Fornecedor fornecedor = fornecedorRepository.procurarPorCnpj(cnpjLoja);
        if(fornecedor == null) throw new OutfitterValidationException("Operação cancelada");
        return !fornecedor.estaAtivo();
    }

    public Fornecedor procurarPorCnpj(String cnpj) {
        return fornecedorRepository.procurarPorCnpj(cnpj);
    }
}
