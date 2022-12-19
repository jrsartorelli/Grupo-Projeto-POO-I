package ada.exercise.heranca.faculdade;

import java.util.List;

public class Turma {
    private String nome;
    private Integer quantidadeAlunos;
    private List<String> horariosAulas;
    private String local;
    private String dataInicio;
    private String dataFinal;

    public Turma(String nome, Integer quantidadeAlunos, List<String> horarioAulas, String local, String dataInicio, String dataFinal) {
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
        this.horariosAulas = horarioAulas;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(Integer quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public List<String> getHorariosAulas() {
        return horariosAulas;
    }

    public void setHorariosAulas(List<String> horariosAulas) {
        this.horariosAulas = horariosAulas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "nome='" + nome + '\'' +
                ", quantidadeAlunos=" + quantidadeAlunos +
                ", horarioAulas=" + horariosAulas +
                ", local='" + local + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                '}';
    }
}
