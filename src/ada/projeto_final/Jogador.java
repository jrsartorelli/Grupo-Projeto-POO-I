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
        int aux = 1;
        for (Pokemon pokemon : arrayPokemon) {
            if (pokemon.estaVivo()) {
                textoPokemons.append(aux + " - " + pokemon);
                aux++;
            }
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
        Pokemon[] arrayMapaPokemons = new Pokemon[getArrayPokemon().length];
        int contadorOpcoes = 0;
        String mensagem = "\nEstes são seus Pokémons:\n";

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemons[i] = getArrayPokemon()[i];
                mensagem += ++contadorOpcoes + " - " + getArrayPokemon()[i] + "\n";
            } else {
                arrayMapaPokemons[i] = null;
            }
        }

        if (contadorOpcoes == 1){
            int i = 0;
            for (Pokemon pokemon : arrayMapaPokemons) {
                if (pokemon != null) {
                    indicePokemonEscolhido = i;
                    break;
                }
                i++;
            }
            return;
        } else {
            String mensagem2 = "(";
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2 += i + " ou ";
                } else if(i == contadorOpcoes){
                    mensagem2 += i + "): ";
                } else{
                    mensagem2 += i + ", ";
                }
            }
            mensagem += "\nEscolha com qual Pokémon você quer entrar na Batalha " + mensagem2;
        }

        while (true) {
            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem);
            int i = 0;
            if ((contadorOpcoes == 2) && (opcaoPokemonJogador == 1 || opcaoPokemonJogador == 2)) {
                int aux = 1;
                for (Pokemon pokemon : arrayMapaPokemons) {
                    if (pokemon != null) {
                        if (opcaoPokemonJogador == 1) {
                            indicePokemonEscolhido = i;
                            break;
                        } else if (opcaoPokemonJogador == 2) {
                            if (aux == 2) {
                                indicePokemonEscolhido = i;
                                break;
                            }
                        }
                        aux++;
                    }
                    i++;
                }
                return;
            } else if (contadorOpcoes == 3 && (opcaoPokemonJogador == 1 || opcaoPokemonJogador == 2 || opcaoPokemonJogador == 3)) {
                indicePokemonEscolhido = (opcaoPokemonJogador - 1);
                return;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

}
