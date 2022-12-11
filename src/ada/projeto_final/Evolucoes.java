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

            put("Caterpie", "Metapod");
            put("Metapod", "Butterfree");

            put("Weedle", "Kakuna");
            put("Kakuna", "Beedrill");

            put("Pidgey", "Pidgeotto");
            put("Pidgeotto", "Pidgeot");

            put("Nidoran", "Nidorina");
            put("Nidorina", "Nidoking");

            put("Zubat", "Golbat");
            put("Golbat", "Bellossom");

            put("Oddish", "Gloom");
            put("Gloom", "Leaf Stone");

            put("Poliwag", "Poliwhirl");
            put("Poliwhirl", "Poliwrath");

            put("Abra", "Kadabra");
            put("Kadabra", "Alakazam");

            put("Machop", "Machoke");
            put("Machoke", "Machamp");

            put("Bellsprout", "Weepinbell");
            put("Weepinbell", "Victreebel");

            put("Geodude", "Graveler");
            put("Graveler", "Golem");

            put("Gastly", "Haunter");
            put("Haunter", "Gengar");

            put("Eevee", "Water Stone");
            put("Water Stone", "Vaporeon");

            put("Dratini", "Dragonair");
            put("Dragonair", "Dragonite");
        }
    };

    public String getEvolucao(String nome) {
        return this.evolucoes.get(nome);
    }
}
