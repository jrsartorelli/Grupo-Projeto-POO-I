package ada.exercise.heranca.faculdade;

public class Secretario extends Funcionario{

    public Secretario(String nome) {
        super(nome);
    }

    public void listarAgenda(Funcionario funcionario){
        System.out.println(funcionario.getAgenda());
    }

    @Override
    public String toString() {
        return "Secretario{} " + super.toString();
    }
}
