package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private String nome;
    private String descricao;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    private Pokemon[] arrayPokemon;
    private Integer indicePokemonEscolhido;
    private String especialidade;

    public JogadorNPC(String nome) {
        this.nome = nome;
        this.descricao = MapaNPCs.buscarDescricao(nome);
        this.especialidade = MapaNPCs.buscarEspecialidade(nome);
        this.arrayPokemon = MapaPokemons.buscarPokemonsRandomicos();
        this.indicePokemonEscolhido = null;
    }

    public String getNome() {
        return nome;
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

    public Pokemon getPokemonEscolhido(){
        if (indicePokemonEscolhido != null){
            return arrayPokemon[indicePokemonEscolhido];
        } else{
            System.err.println("Erro! O Pokémon do NPC não foi escolhido");
        }
        return null;
    }

    public void setIndicePokemonEscolhido(Integer indicePokemonEscolhido) {
        this.indicePokemonEscolhido = indicePokemonEscolhido;
    }

    public void escolherPokemonNPCRandomico(){
        int indice = Utilidades.random.nextInt(2);
        while(getArrayPokemon()[indice].getVida()<1){
            indice++;
            indice%=3;
        }
        setIndicePokemonEscolhido(indice);
    }

    public String buscarAtaqueNPCRandomico(){
        return getPokemonEscolhido().buscarAtaqueRandomico();
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
        for(Pokemon poke: Arrays.asList(this.arrayPokemon)) {
            if (poke.estaVivo()) {
                return true;
            }
        }
        return false;
    }
}
