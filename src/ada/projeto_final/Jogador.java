package ada.projeto_final;

public class Jogador {
    private final String nome;
    private Integer vida;
    private Integer nivel;
    private Pokemon[] arrayPokemon;
    private Integer numRevives;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 1000;
        this.nivel = 1;
        this.arrayPokemon = criarTimePokemon();
        this.numRevives = 1;
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

    public Integer getNumRevives() {
        return numRevives;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
    }

    public void setNumRevives(Integer numRevives) {
        this.numRevives = numRevives;
    }
}
