package ada.exercise.heranca.faculdade;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Funcionario{
    private List<Artigo> artigosPublicados;
    private List<Turma> turmasPeriodo;

    public Professor(String nome) {
        super(nome);
        this.artigosPublicados = new ArrayList<>();
        this.turmasPeriodo = new ArrayList<>();
    }

    public List<Artigo> getArtigosPublicados() {
        return artigosPublicados;
    }

    public void setArtigosPublicados(List<Artigo> artigosPublicados) {
        this.artigosPublicados = artigosPublicados;
    }

    public void cadastrarArtigo(Artigo artigo){
        this.artigosPublicados.add(artigo);
    }

    public List<Turma> getTurmasPeriodo() {
        return turmasPeriodo;
    }

    public void setTurmasPeriodo(List<Turma> turmasPeriodo) {
        this.turmasPeriodo = turmasPeriodo;
    }

    public void cadastrarTurma(Turma turma){
        this.turmasPeriodo.add(turma);
    }

    @Override
    public String toString() {
        return "Professor = " + super.getNome();
    }
}
