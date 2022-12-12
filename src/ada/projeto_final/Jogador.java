package ada.projeto_final;

public class Jogador {
    private final String nome;
    private Integer vida;
    private Integer level;
    private Pokemon[] arrayPokemon;
    private Integer numRevives;
    private Integer pedrasEvolução;

    public Jogador(String nome) {
        this.nome = nome;
        this.vida = 1000;
        this.level = 1;
        this.arrayPokemon = criarTimePokemons();
        this.numRevives = 1;
        this.pedrasEvolução = 0;
    }

    private Pokemon[] criarTimePokemons(){
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

    public Integer getLevel() {
        return level;
    }

    public Integer getNumRevives() {
        return numRevives;
    }

    public void setVida(Integer vida) { this.vida = vida; }

    public void setLevel(Integer level) { this.level = level; }

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
    }

    public Pokemon getPokemon(int indexArrayPokemon){
        return arrayPokemon[indexArrayPokemon];
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
        if(this.numRevives>0 || this.vida>0){
            return true;
        }
        return false;
    }

}
