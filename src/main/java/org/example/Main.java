package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConecaoSington s1 = new ConecaoSington();


        Connection conAluno = s1.getConn();
        ConecaoSington s2 = new ConecaoSington();
        Connection conMateria = s2.getConn();

        if(conAluno==conMateria)
            System.out.println("As con sao iguais");
        else
            System.out.println("Nao sao iguais");

        System.out.println("Conxao feita com sucesso!");

        String sql = "Create table alunos (matricula int primary key, nome varchar(200))";

        Statement statement = conAluno.createStatement();

        statement.execute(sql);

        System.out.println("Tabela criada comsucesso");

        sql = "Insert into alunos (matricula, nome) values (1, 'Windson')";

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Linha inserida com sucesso.");
        }
        conAluno.close();
    }
}