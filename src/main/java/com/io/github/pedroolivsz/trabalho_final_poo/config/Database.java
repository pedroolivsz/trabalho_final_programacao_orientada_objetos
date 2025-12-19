package com.io.github.pedroolivsz.trabalho_final_poo.config;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static final HikariDataSource dataSource = new HikariDataSource();
    static {
        dataSource.setJdbcUrl(Config.get("agenciadb.url"));
        dataSource.setMaximumPoolSize(10);
    }
    public Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
