package br.sapiens.models;

import java.util.Date;
import java.util.List;

public class Aluno {

    private Integer id;
    private String nome;
    private Date dataNascimento;
    private Curso curso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno(Integer id, String nome, Date dataNascimento, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.curso = curso;
    }
}
