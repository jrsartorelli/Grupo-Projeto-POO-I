package ada.projeto_final;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private String nome;
    private Integer vida = 1000;
    private String descricao;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    private Pokemon[] arrayPokemon;
    private String especialidade;

    public JogadorNPC(String nome) {
        this.nome = nome;
        this.descricao = MapaNPCs.buscarDescricao(nome);
        this.especialidade = MapaNPCs.buscarEspecialidade(nome);
        this.arrayPokemon = MapaPokemons.buscarPokemonsRandomicos();
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

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
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

    public void setArrayPokemon(Pokemon[] arrayPokemon) {
        this.arrayPokemon = arrayPokemon;
    }

    public boolean aptoJogar(){
        for(Pokemon poke: Arrays.asList(this.arrayPokemon)){
            if(poke.estaVivo()){
                return true;
            }
        }
        if(this.vida>0){
            return true;
        }
        return false;
    }


}
