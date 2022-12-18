package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private final String nome;
    private final String descricao;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    private final Pokemon[] arrayPokemon;
    private Integer indicePokemonEscolhido;
    private final String especialidade;

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
        Utilidades.imprimirComPausa("\n" + getNome() + " escolheu o Pokémon " +
                getPokemonEscolhido().getNome() + " com Vida = " + getPokemonEscolhido().getVida() + " para iniciar no campo de batalha!\n");
        Utilidades.imprimirComPausa(getNome() + ": \"" + getFrasesDeEfeito().get("inicio") + "\"\n");
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setFrasesDeEfeito(Map<String, String> frasesDeEfeito) {
        this.frasesDeEfeito = frasesDeEfeito;
    }

    public boolean aptoJogar(){
        for(Pokemon poke: this.arrayPokemon) {
            if (poke.estaVivo()) {
                return true;
            }
        }
        return false;
    }
}
