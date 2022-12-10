package ada.projeto_final;

// import java.util.ArrayList;
// import java.util.List;

public class Jogador {
    private String nome;
    private Integer vida = 1000;
    private Integer nivel = 1;
    private Pokemon[] arrayPokemon;
    private Integer numRevives = 1;

//    public Jogador(String nome) {
//        this.nome = nome;
//    }

    public Jogador(String nome) {
        this.nome = nome;
        this.arrayPokemon = criarTimePokemon();
    }

    private Pokemon[] criarTimePokemon(){
        Pokemon[] array = new Pokemon[]
                {   new Pokemon("Charmander"),
                    new Pokemon("Squirtle"),
                    new Pokemon("Bulbasaur")
                };
        return array;
    }

    public String getNome() {
        return nome;
    }

    public Integer getVida() {
        return vida;
    }

    public Integer getNivel() {
        return nivel;
    }

//    public List<Pokemon> getListaPokemons() {
//        return listaPokemons;
//    }

//    public Pokemon getPokemon() {
//        return pokemon;
//    }

    public Integer getNumRevives() {
        return numRevives;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

//    public void setListaPokemons(List<Pokemon> listaPokemons) {
//        this.listaPokemons = listaPokemons;
//    }


    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
    }

//    public void setPokemon(Pokemon pokemon) {
//        this.pokemon = pokemon;
//    }

    public void setNumRevives(Integer numRevives) {
        this.numRevives = numRevives;
    }
}
