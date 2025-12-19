package com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Pessoa;

import java.util.List;

public class AgenciaBancariaRepository {
    private final List<Conta> contasBancarias;

    public AgenciaBancariaRepository(List<Conta> contasBancarias) {
        this.contasBancarias = contasBancarias;
    }

    public void criarConta(Conta conta) {
        contasBancarias.add(conta);
    }

    public Conta procurarContaPorNumeroDaConta(String numeroConta) {
        Conta destinatario = null;

        for(Conta conta : contasBancarias) {
            if(conta.getNumeroDaConta().equals(numeroConta)) {
                destinatario = conta;
            }
        }

        return destinatario;
    }

    public Conta procurarContaPorEmail(String email) {
        Conta contaProcurada = null;

        for(Conta conta : contasBancarias) {
            if(conta.getEmailProprietario().equals(email)) {
                contaProcurada = conta;
            }
        }

        return contaProcurada;
    }

    public Pessoa encontrarPessoa(String CPF) {
        Pessoa pessoaEncontrada = null;
        for(Conta conta : contasBancarias) {
            if(conta.getCpfProprietario().equals(CPF)) {
                pessoaEncontrada = conta.getProprietario();
                break;
            }
        }
        return pessoaEncontrada;
    }

    public List<Conta> listarContas() {
        return contasBancarias;
    }
}
