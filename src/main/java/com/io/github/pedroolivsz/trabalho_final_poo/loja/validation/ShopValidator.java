package com.io.github.pedroolivsz.trabalho_final_poo.loja.validation;

import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.ShopValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.loja.dominio.Loja;

public class ShopValidator {
    public static void validarDadosDaLoja(String nome, String CNPJ, String senha) {
        if(nome == null || nome.isBlank()) throw new ShopValidationException("Nome inválido");
        if(CNPJ == null || CNPJ.isBlank()) throw new ShopValidationException("CNPJ inválido");
        if(senha == null || senha.isBlank()) throw new ShopValidationException("Senha inválida");
    }

    public static void validarDadosDeEndereco(String cep,
                                              String estado,
                                              String cidade,
                                              String bairro,
                                              String rua,
                                              String numero) {
        if(cep == null || cep.isBlank()) throw new ShopValidationException("CEP inválido");
        if(estado == null || estado.isBlank()) throw new ShopValidationException("Estado inválido");
        if(cidade == null || cidade.isBlank()) throw new ShopValidationException("Cidade inválida");
        if(bairro == null || bairro.isBlank()) throw new ShopValidationException("Bairro inválido");
        if(rua == null || rua.isBlank()) throw new ShopValidationException("Rua inválida");
        if(numero == null || numero.isBlank()) throw new ShopValidationException("Número inválido");
    }

    public static void validarDadosDeContato(String telefone, String email) {
        if(telefone == null || telefone.isBlank()) throw new ShopValidationException("Número de telefone inválido");
        if(email == null || email.isBlank()) throw new ShopValidationException("Endereço de e-mail inválido");
    }

    public static void validarExistenciaDeLoja(Loja loja) {
        if(loja == null) {
            throw new ShopValidationException("CNPJ inválido");
        }
    }

    public static void validarLojaAtiva(Loja loja) {
        if(loja.estaBloqueada()) {
            throw new ShopValidationException("Loja bloqueada por atingir o limite de operações com o caixa zerado");
        }
    }
}
