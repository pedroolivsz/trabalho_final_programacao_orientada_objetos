package com.io.github.pedroolivsz.trabalho_final_poo.service.agencia;

import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Conta;
import com.io.github.pedroolivsz.trabalho_final_poo.dominio.agencia.Pessoa;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.AccountException;
import com.io.github.pedroolivsz.trabalho_final_poo.exceptions.PersonValidationException;
import com.io.github.pedroolivsz.trabalho_final_poo.repository.agencia.AgenciaBancariaRepository;
import com.io.github.pedroolivsz.trabalho_final_poo.util.PasswordUtil;
import com.io.github.pedroolivsz.trabalho_final_poo.validation.agencia.AccountValidator;

import java.util.List;

public class AgenciaBancariaService {
    private static final String CODIGO_BANCO = "0001";
    private static int sequencialConta = 1;
    private final AgenciaBancariaRepository agenciaBancariaRepository;

    public AgenciaBancariaService(AgenciaBancariaRepository agenciaBancariaRepository) {
        this.agenciaBancariaRepository = agenciaBancariaRepository;
    }

    public void criarConta(String nome, String CPF, String endereco, String email, String senha) throws PersonValidationException {
        AccountValidator.validarConta(nome, CPF, endereco, email, senha);

        Conta contaDuplicadaEmail = procurarContaPorEmail(email);
        Pessoa pessoaDuplicadaCpf = procurarPessoaPorCpf(CPF);

        if(contaDuplicadaEmail != null) throw new AccountException("O email já está cadastrado em uma conta.");
        if(pessoaDuplicadaCpf != null) throw new PersonValidationException("Você já possui uma conta cadastrada.");

        String hash = PasswordUtil.gerarHash(senha);
        Pessoa proprietario = new Pessoa(nome, CPF, endereco, email, hash);

        String numeroConta = gerarNumeroConta();

        Conta conta = new Conta(numeroConta, proprietario, numeroConta);

        agenciaBancariaRepository.criarConta(conta);
    }

    public List<Conta> listarContas() {
        return List.copyOf(agenciaBancariaRepository.listarContas());
    }

    public Conta logarConta(String email, String senha) {
        AccountValidator.validarLogin(email, senha);

        Conta contaLogada = procurarContaPorEmail(email);

        AccountValidator.validarContaExistente(contaLogada);

        validarSenha(contaLogada, senha);

        return contaLogada;
    }

    public void validarSenha(Conta contaProcurada, String senha) {
        if(!PasswordUtil.verificarSenha(senha, contaProcurada.getSenhaProprietario())) throw new AccountException("Senha ou email inválidos");
    }

    public Conta procurarPorNumeroDaConta(String numeroConta) {
        return agenciaBancariaRepository.procurarContaPorNumeroDaConta(numeroConta);
    }

    private Conta procurarContaPorEmail(String email) {
        return agenciaBancariaRepository.procurarContaPorEmail(email);
    }
    
    private Pessoa procurarPessoaPorCpf(String cpf) {
    	return agenciaBancariaRepository.encontrarPessoa(cpf);
    }

    public String gerarNumeroConta() {
        String numeroSequencial = String.format("%06d", sequencialConta);
        String numContaSemDigitoVerificador = CODIGO_BANCO + numeroSequencial;

        int digitoVerificador = calcularDigitoVerificador(numContaSemDigitoVerificador);

        sequencialConta++;

        return CODIGO_BANCO + "-" + numeroSequencial + "-" + digitoVerificador;
    }

    private int calcularDigitoVerificador(String numero) {
        int soma = 0;

        for(char character : numero.toCharArray()) {
            soma += Character.getNumericValue(character);
        }

        return soma % 10;
    }
}
