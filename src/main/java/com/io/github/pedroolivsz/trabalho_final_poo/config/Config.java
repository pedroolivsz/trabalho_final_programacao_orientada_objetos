package com.io.github.pedroolivsz.trabalho_final_poo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try(InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if(inputStream == null) {
                throw new RuntimeException("O arquivo 'application.properties' não foi encontrado");
            }

            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo de configuração", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
