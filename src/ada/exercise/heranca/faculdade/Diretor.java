package ada.exercise.heranca.faculdade;

import java.util.ArrayList;
import java.util.List;

public class Diretor extends Funcionario{
    private List<Professor> professoresOrientados;

    public Diretor(String nome) {
        super(nome);
        this.professoresOrientados = new ArrayList<>();
    }

    public List<Professor> getProfessoresOrientados() {
        return professoresOrientados;
    }

    public void setProfessoresOrientados(List<Professor> professoresOrientados) {
        this.professoresOrientados = professoresOrientados;
    }

    public void orientarProfessor(Professor professor){
        professoresOrientados.add(professor);
    }

    public void exibirProfessoresOrientados(){
        System.out.println(professoresOrientados);
    }

    @Override
    public String toString() {
        return "Diretor{" +
                "professoresOrientados=" + professoresOrientados +
                "} " + super.toString();
    }
}



