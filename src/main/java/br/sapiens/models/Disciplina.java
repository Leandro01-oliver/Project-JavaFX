package br.sapiens.models;

public class Disciplina {

    private Integer id;

    private String descricao;
    private int creditos;
    private Curso curso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Disciplina(Integer id, String descricao, int creditos, Curso curso) {
        this.id = id;
        this.descricao = descricao;
        this.creditos = creditos;
        this.curso = curso;
    }

}
