package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConecaoSington {

    private static Connection conn = null;

    ConecaoSington() throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test";
        if(conn == null){
            conn = DriverManager.getConnection(jdbcURL);
            System.out.println("Uma conexao esta sendo estabelecida");
        }

    }

    public Connection getConn() {
        return conn;
    }
}
