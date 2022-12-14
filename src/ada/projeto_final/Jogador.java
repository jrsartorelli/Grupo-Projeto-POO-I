package ada.projeto_final;

import ada.projeto_final.mapas.MapaPokemons;

import java.util.Scanner;

public class Jogador {
    private final String nome;
    private Integer level;
    private Pokemon[] arrayPokemon;
    private Integer indicePokemonEscolhido;
    private Integer numRevives;
    private Integer pedrasEvolução;

    public Jogador(String nome) {
        this.nome = nome;
        this.level = 1;
        this.arrayPokemon = MapaPokemons.buscarPokemonsRandomicos();
        this.indicePokemonEscolhido = null;
        this.numRevives = 1;
        this.pedrasEvolução = 0;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getNumRevives() {
        return numRevives;
    }

    public void setLevel(Integer level) { this.level = level; }

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
    }

    public Pokemon getPokemon(int indexArrayPokemon){
        return arrayPokemon[indexArrayPokemon];
    }

    public Pokemon getPokemonEscolhido(){
        if (indicePokemonEscolhido != null){
            return arrayPokemon[indicePokemonEscolhido];
        } else{
            System.err.println("Erro! O Pokémon não foi selecionado");
        }
        return null;
    }

    public String imprimirPokemons(){
        StringBuilder textoPokemons = new StringBuilder();
        for (int i = 0; i < arrayPokemon.length; i++){
            textoPokemons.append((i + 1) + " - " + arrayPokemon[i]);
        }
        return textoPokemons.toString();
    }

    public void setNumRevives(Integer numRevives) { this.numRevives = numRevives; }

   //a função retorna true caso seja possível reviver o pokemon
    public boolean usarRevive(Pokemon bono){
        if(!bono.estaVivo() && this.numRevives>0) {
            bono.revive();
            this.numRevives--;
            return true;
        }else{
            return false;
        }
    }

    public boolean aptoJogar(){
        for(Pokemon poke:this.arrayPokemon){
            if(poke.estaVivo()){
                return true;
            }
        }
        if(this.numRevives>0){
            return true;
        }
        return false;
    }

    public void escolherPokemon() {
        int opcaoPokemonJogador;
        while (true) {
            opcaoPokemonJogador = Utilidades.lerIntUsuario("\nEstes são seus Pokémons:\n" +
                    imprimirPokemons() + "\n" + nome +
                    ", escolha seu Pokémon para a Batalha (1, 2 ou 3): ");
            if (opcaoPokemonJogador == 1 || opcaoPokemonJogador == 2 || opcaoPokemonJogador == 3) {
                indicePokemonEscolhido = (opcaoPokemonJogador - 1);
                return;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

}
