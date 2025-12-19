package com.io.github.pedroolivsz.trabalho_final_poo.util.loja;

import java.time.Year;

public class CodigoDeBarrasUtil {
    private long id = 1l;
    public String gerarCodigoDeBarras() {
        String IDFormatado = String.format("%05d", id++);

        int ano = Year.now().getValue();
        String anoFormatado = String.valueOf(ano).substring(1);

        String base = IDFormatado + anoFormatado;
        int soma = 0;
        for(char c : base.toCharArray()) {
            soma += Character.getNumericValue(c);
        }

        int digitoVerificador = soma % 10;

        return IDFormatado + anoFormatado + digitoVerificador;
    }
}
