package ada.exercise.heranca.faculdade;

public class Coordenador extends Funcionario{

    public Coordenador(String nome) {
        super(nome);
    }

    public void alocarProfessor(Funcionario professor){
        professor.setAgenda(new Agenda("Curso POO", "Curso sobre Programação Orientada a Obetos - JAVA"));
    }

    @Override
    public String toString() {
        return "Coordenador{} " + super.toString();
    }
}
