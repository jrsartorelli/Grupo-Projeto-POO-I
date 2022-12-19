package ada.exercise.heranca.faculdade;

import java.util.List;

public class Artigo {
    private String titulo;
    private String descricao;
    private List<String> palavrasChave;
    private String data;

    public Artigo(String titulo, String descricao, List<String> palavrasChave, String data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.palavrasChave = palavrasChave;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "titulo='" + titulo + '\'' +
                ", descrição='" + descricao + '\'' +
                ", palavrasChave=" + palavrasChave +
                ", data=" + data +
                '}';
    }
}
