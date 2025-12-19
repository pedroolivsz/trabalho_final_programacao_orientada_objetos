package com.io.github.pedroolivsz.trabalho_final_poo.validation.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.AccountException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.PersonValidationException;

import java.math.BigDecimal;

public class AccountValidator {
    public static void validarConta(String nome, String CPF, String endereco, String email, String senha) {
        if(nome == null || nome.isBlank()) throw new PersonValidationException("Nome inválido.");
        if(CPF == null || CPF.isBlank()) throw new PersonValidationException("Cpf inválido.");
        if(endereco == null || endereco.isBlank()) throw new PersonValidationException("Endereço inválido.");
        if(email == null || email.isBlank()) throw new PersonValidationException("Email inválido.");
        if(senha == null || senha.isBlank()) throw new PersonValidationException("Senha inválida.");
    }

    public static void validarLogin(String email, String senha) {
        if(email == null || email.isBlank()) throw new PersonValidationException("Email inválido.");
        if(senha == null || senha.isBlank()) throw new PersonValidationException("Senha inválida.");
    }

    public static void validarNumeroConta(String numeroConta) {
        if(numeroConta == null || numeroConta.isBlank()) throw new AccountException("Número da conta inválido.");
    }

    public static void validarContaExistente(Conta conta) {
        if(conta == null) throw new AccountException("Senha ou email incorretos.");
    }

    public static void validarDeposito(BigDecimal valor) {
        if(valor == null) throw new AccountException("Valor inválido.");
        if(valor.compareTo(BigDecimal.ZERO) <= 0) throw new AccountException("O valor não pode ser negativo ou zero.");
    }

    public static void validarSaque(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0) throw new AccountException("O valor não pode ser negativo ou zero");
        if(valor == null) throw new AccountException("Valor inválido.");
    }

    public static void validarTransferencia(String numeroConta, BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0) throw new AccountException("O valor não pode ser negativo ou zero");
        if(valor == null) throw new AccountException("Valor inválido.");
        if(numeroConta == null || numeroConta.isBlank()) throw new AccountException("Número da conta inválido.");
    }
}
