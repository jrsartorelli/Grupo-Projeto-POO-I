package ada.exercise.heranca.faculdade;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String tiltuloArtigo = "Mitos e Verdades";
        String descricaoArtigo = "Artigo sobre a grande quantidade de informações encontradas na Internet";
        String dataPublicacao = "19-12-2022";
        List<String> palavrasChave = new ArrayList<>(){{
            add("Mito");
            add("Verdade");
            add("Internet");
        }};
        Artigo artigo = new Artigo(tiltuloArtigo, descricaoArtigo, palavrasChave, dataPublicacao);

        Agenda agenda = new Agenda("Curso POO", "Curso sobre Programação Orientada a Obetos - JAVA");

        String nomeTurma = "Turma 945 - Santander Coders";
        List<String> horariosAulas = new ArrayList<>(){{
            add("19h00");
        }};
        String localTurma = "Google Meet";
        String dataInicial = "01-11-2022";
        String dataFinal = "16-12-2022";
        Turma turma = new Turma(nomeTurma, 25, horariosAulas, localTurma, dataInicial, dataFinal);

        Professor professor1 = new Professor("Tião");
        Professor professor2 = new Professor("Pedro");
        professor1.setAgenda(agenda);

        professor1.cadastrarArtigo(artigo);  // Item I - Cadastro de Artigos para um professor X
        professor1.cadastrarTurma(turma);    // Item II - Cadastro de Turmas para um professor X

        Diretor diretor = new Diretor("Barnabé");

        diretor.orientarProfessor(professor1);
        diretor.orientarProfessor(professor2);
        diretor.exibirProfessoresOrientados();  // Item III - Exibição de professores que foram orientados pelo Diretor

        Secretario secretario = new Secretario("Tadeu");
        secretario.listarAgenda(professor1);     // tem IV - Exibição detalhada da agenda de um funcionário X
    }
}