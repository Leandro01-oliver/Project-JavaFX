package br.sapiens.models;

public class Matricula {


    private String id;
    private Disciplina disciplina;
    private Aluno aluno;
    private Periodo periodo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Matricula(String id, Disciplina disciplina, Aluno aluno, Periodo periodo) {
        this.id = id;
        this.disciplina = disciplina;
        this.aluno = aluno;
        this.periodo = periodo;
    }
}
