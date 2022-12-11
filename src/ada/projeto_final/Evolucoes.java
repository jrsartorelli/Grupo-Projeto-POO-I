package ada.projeto_final;

import java.util.HashMap;

public class Evolucoes {
    private HashMap<String, String> evolucoes =new HashMap<>()
    {
        {
            put("Charmander","Charmeleon");
            put("Charmeleon","Charizard");
            put("Bulbasaur","Ivysaur");
            put("Ivysaur","Venusaur");
            put("Squirtle","Wartortle");
            put("Wartortle","Blastoise");
        }
    };

    public String getEvolucao(String nome) {
        return this.evolucoes.get(nome);
    }
}
