package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private String nome;
    private Integer vida = 1000;
    private String descricao;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    // private List<Pokemon> listaPokemons = new ArrayList<>();
    private Pokemon pokemon;
    private String especialidade;

    public JogadorNPC(String nome, String descricao, Map<String, String> frasesDeEfeito, Pokemon pokemon, String especialidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.frasesDeEfeito = frasesDeEfeito;
        this.pokemon = pokemon;
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

    public Pokemon getPokemon() {
        return pokemon;
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

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }


}
