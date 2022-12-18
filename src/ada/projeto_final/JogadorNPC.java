package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

import java.util.HashMap;
import java.util.Map;

public class JogadorNPC {
    private final String NOME;
    private final String DESCRICAO;
    private Map<String, String> frasesDeEfeito = new HashMap<>();
    private final Pokemon[] ARRAY_POKEMON;
    private Integer indicePokemonEscolhido;
    private final String ESPECIALIDADE;

    public JogadorNPC(String nome) {
        this.NOME = nome;
        this.DESCRICAO = MapaNPCs.buscarDescricao(nome);
        this.ESPECIALIDADE = MapaNPCs.buscarEspecialidade(nome);
        this.ARRAY_POKEMON = MapaPokemons.buscarPokemonsRandomicos();
        this.indicePokemonEscolhido = null;
    }

    public String getNOME() {
        return NOME;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public Map<String, String> getFrasesDeEfeito() {
        return frasesDeEfeito;
    }

    public Pokemon[] getARRAY_POKEMON() {
        return ARRAY_POKEMON;
    }

    public Pokemon getPokemonEscolhido(){

        if (indicePokemonEscolhido != null){
            return ARRAY_POKEMON[indicePokemonEscolhido];
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
        while(getARRAY_POKEMON()[indice].getVida()<1){
            indice++;
            indice%=3;
        }
        setIndicePokemonEscolhido(indice);
        Utilidades.imprimirComPausa("\n" + getNOME() + " escolheu o Pokémon " +
                getPokemonEscolhido().getNome() + " com Vida = " + getPokemonEscolhido().getVida() + " para iniciar no campo de batalha!\n");
        Utilidades.imprimirComPausa(getNOME() + " diz: \"" + getFrasesDeEfeito().get("inicio") + "\"\n");
    }

    public String getESPECIALIDADE() {
        return ESPECIALIDADE;
    }

    public void setFrasesDeEfeito(Map<String, String> frasesDeEfeito) {
        this.frasesDeEfeito = frasesDeEfeito;
    }

    public boolean aptoJogar(){
        for(Pokemon poke: this.ARRAY_POKEMON) {
            if (poke.estaVivo()) {
                return true;
            }
        }
        return false;
    }
}
