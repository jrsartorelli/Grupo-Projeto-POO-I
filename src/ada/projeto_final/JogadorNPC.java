package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private String nome;
    private Integer vida = 1000;
    private String descricao;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    // private List<Pokemon> listaPokemons = new ArrayList<>();
    private Pokemon[] listaPokemon;
    private String especialidade;

    public JogadorNPC(String nome, String descricao, String especialidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public Integer getVida() {
        return vida;
    }

    public String getDescricao() {
        return descricao;
    }

    public Map<String, String> getFrasesDeEfeito() {
        return frasesDeEfeito;
    }

    public Pokemon[] getListaPokemon() {
        return listaPokemon;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFrasesDeEfeito(Map<String, String> frasesDeEfeito) {
        this.frasesDeEfeito = frasesDeEfeito;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setListaPokemon(Pokemon[] listaPokemon) {
        this.listaPokemon = listaPokemon;
    }
}
