package ada.exercise.heranca.faculdade;

import java.util.Date;
import java.util.List;

public class Artigo {
    private String titulo;
    private String descrição;
    private List<String> palavrasChave;
    private String data;

    public Artigo(String titulo, String descrição, List<String> palavrasChave, String data) {
        this.titulo = titulo;
        this.descrição = descrição;
        this.palavrasChave = palavrasChave;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
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
                ", descrição='" + descrição + '\'' +
                ", palavrasChave=" + palavrasChave +
                ", data=" + data +
                '}';
    }
}
