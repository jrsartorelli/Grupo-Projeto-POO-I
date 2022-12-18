package ada.projeto_final.mapas;

import ada.projeto_final.Pokemon;
import ada.projeto_final.Utilidades;

import java.util.*;

public class MapaPokemons {
    private static final Integer[] VALOR_ATAQUE_BASE = new Integer[]{300, 350, 400};
    private static final Integer[] RANGE_RANDOMICO = new Integer[]{300, 350, 400};

    private static final Map<String, String[]> mapaAtaquesNivel1 = new HashMap<>() {{
        put("Charmander", new String[]{"Fire Ball", "Flame Thrower", "Fire Blast"});
        put("Bulbasaur", new String[]{"Take Down", "Petal Dance", "Solar Beam"});
        put("Squirtle", new String[]{"Bubble Beam", "Water Gun", "Aqua Tail"});
        put("Caterpie", new String[]{"String Shot", "Tackle", "Silk Cannon"});
        put("Weedle", new String[]{"Poison Sting", "Poison Cannon", "Web Toss"});
        put("Pidgey", new String[]{"Peck", "Gust", "Quick Attack"});
        put("Nidoran", new String[]{"Double Kick", "Nidoran Tackle", "Nid Bite"});
        put("Zubat", new String[]{"Vulp Fire Spin", "Vulp Flame Thrower", "Ember"});
        put("Oddish", new String[]{"Sludge Bomb", "Stun Spore", "Sweet Scent"});
        put("Poliwag", new String[]{"Defense Curl", "Water Pulse", "Leer"});
        put("Abra", new String[]{"Teleport", "Safeguad", "Psychic"});
        put("Machop", new String[]{"Karate Chop", "Cross Chop", "Dynamic Punch"});
        put("Bellsprout", new String[]{"Razor Leaf", "Absorb", "Growth"});
        put("Geodude", new String[]{"Rollout", "Mega Punch", "Hidden Power"});
        put("Gastly", new String[]{"Astonish", "Night Shades", "Confuse Ray"});
        put("Eevee", new String[]{"Double Team", "Trump Card", "Swift"});
        put("Dratini", new String[]{"Tail Swipe", "Wrap", "Dart Aqua Tail"});
    }};

    private static final Map<String, String[]> mapaAtaquesNivel2 = new HashMap<>() {{
        put("Charmeleon", new String[]{"Fire Spin", "Dragon Claw", "Headbutt"});
        put("Ivysaur", new String[]{"Vine Whip", "Ivysaur Razor Leaf", "Ivysaur Tackle"});
        put("Wartortle", new String[]{"Hydro Pump", "Rapid Spin", "Bite"});
        put("Metapod", new String[]{"Metapod Tackle", "Harden", "Metapod String Shot"});
        put("Kakuna", new String[]{"Kakuna String Shot", "Kakuna Tackle", "Kakuna Harden"});
        put("Pidgeotto", new String[]{"Aerial Ace", "Wing Attack", "Double-Edge"});
        put("Nidorina", new String[]{"Nid Scratch", "Nid Poison Sting", "Nidorina Tackle"});
        put("Golbat", new String[]{"Sand Attack", "Steel Wing", "X-Scissor"});
        put("Gloom", new String[]{"Acid", "Gloom Stun Spore", "Sleep Powder"});
        put("Poliwhirl", new String[]{"Mud Bomb", "Scald", "Pol Bubble Beam"});
        put("Kadabra", new String[]{"Kad Hypnosis", "Kad Confusion", "Kad Psychic"});
        put("Machoke", new String[]{"Focus Energy", "Scary Face", "Brick Break"});
        put("Weepinbell", new String[]{"Weep Razor Leaf", "Weep Poison Powder", "Weep Vine Whip"});
        put("Graveler", new String[]{"Grav Stone Edge", "Grav Rock Blast", "Grav Rock Slide"});
        put("Haunter", new String[]{"Hau Shadow Ball", "Hau Hypnoses", "Hau Confuse Ray"});
        put("Vaporeon", new String[]{"Acid Armor", "Vaporeon Hydro Pump", "Aurora Beam"});
        put("Dragonair", new String[]{"Drag Rain Dance", "Drag Hyper Beam", "Drag Dragon Rage"});
    }};

    private static final Map<String, String[]> mapaAtaquesNivel3 = new HashMap<>() {{
        put("Charizard", new String[]{"Slash", "Charizard Steel Wing", "Seismic Toss"});
        put("Venusaur", new String[]{"Venusaur Sweet Scent", "Venusaur Sleep Powder", "Frenzy Plant"});
        put("Blastoise", new String[]{"Withdraw", "Rain Dance", "Skull Bash"});
        put("Butterfree", new String[]{"Whirlwind", "Butterfree Gust", "Butterfree Stun Spore"});
        put("Beedrill", new String[]{"Pin Missile", "Slugde Bomb", "Twineedle"});
        put("Pidgeot", new String[]{"Pid Feather Dance", "Pid Wing Attack", "Pid Whirlwind"});
        put("Nidoking", new String[]{"Rock Slide", "Horn Attack", "Earth Power"});
        put("Crobat", new String[]{"Supersonic", "Air Slash", "Sonic Boom"});
        put("Bellossom", new String[]{"Magical Leaf", "Bellossom Petal Dance", "Bellossom Stun Spore"});
        put("Poliwrath", new String[]{"Poliwrath Hydro Pump", "Double Slap", "Mega Kick"});
        put("Alakazam", new String[]{"Psybeam", "Zap Cannon", "Miracle Eye"});
        put("Machamp", new String[]{"Vital Throw", "Hammer Arm", "Bullet Punch"});
        put("Victreebel", new String[]{"Swallow", "Sword Dance", "Vict Vine Whip"});
        put("Golem", new String[]{"Rock Blast", "Sandstorm", "Magnitude"});
        put("Gengar", new String[]{"Dream Eater", "Dark Pulse", "Atonish"});
        put("Jolteon", new String[]{"Thunderbolt", "Shadow Ball", "Thunder Wave"});
        put("Dragonite", new String[]{"Dragon Rush", "Dragon Rage", "Dragonite Dragon Claw"});
    }};

    private static final HashMap<String, String> evolucoes =new HashMap<>()
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
            put("Gloom", "Bellossom");

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

            put("Eevee", "Vaporeon");
            put("Vaporeon", "Jolteon");

            put("Dratini", "Dragonair");
            put("Dragonair", "Dragonite");
        }
    };

    //Mapa com o valor para cada tipo de ataque
    private static final Map<String, Integer> mapaValorAtaque = new HashMap<>();

    private static final Map<String, Boolean> mapaPokemonsJaUtilizados = new HashMap<>();

    //Insere de forma randômica os valores para todos os ataques listados
    public static void inicializarValoresAtaque(){
        int valorAtaqueGerado;
        for (String[] ataques : mapaAtaquesNivel1.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE[0] + Utilidades.random.nextInt(0, RANGE_RANDOMICO[0]));
                if(!mapaValorAtaque.containsKey(ataque)){
                    mapaValorAtaque.put(ataque, valorAtaqueGerado);
                } else {
                    System.err.println("Já contem o valor chave para o Ataque: " + ataque);
                }
            }
        }
        for (String[] ataques : mapaAtaquesNivel2.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE[1] + Utilidades.random.nextInt(0, RANGE_RANDOMICO[1]));
                if(!mapaValorAtaque.containsKey(ataque)){
                    mapaValorAtaque.put(ataque, valorAtaqueGerado);
                } else {
                    System.err.println("Já contem o valor chave para o Ataque: " + ataque);
                }
            }
        }
        for (String[] ataques : mapaAtaquesNivel3.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE[2] + Utilidades.random.nextInt(0, RANGE_RANDOMICO[2]));
                if(!mapaValorAtaque.containsKey(ataque)){
                    mapaValorAtaque.put(ataque, valorAtaqueGerado);
                } else {
                    System.err.println("Já contem o valor chave para o Ataque: " + ataque);
                }
            }
        }
    }

    // Este método armazena informação sobre quais Pokémons já foram atribuídos
    // Desta forma podemos garantir que na atribuição randômica dos Pokémons, tanto para o Jogador, quanto para os NPC´s
    // Não haverá Pokémons repetidos. Devemos garantir que mapaAtaquesNivel1 possua pelo menos 12 Pokémons
    public static void inicializarValoresPokemonsJaUtilizados(){
        // Obtém os nomes de todos os Pokémons de nível 1
        Set<String> listaPokemons = mapaAtaquesNivel1.keySet();

        // Insere todos os Pokémons de nível 1 no Mapa mapaPokemonsUtilizados
        for (String nomePokemon : listaPokemons) {
            mapaPokemonsJaUtilizados.put(nomePokemon, false);
        }
    }

    // mapaValorAtaque contém todos os ataques de todos os Pokémons
    // key -> texto que define o ataque, valeu -> valor dos ataques
    public static Integer buscarValorAtaque(String ataque) {
        return mapaValorAtaque.get(ataque);
    }

    public static String[] buscarAtaques(String nomePokemon){
        if(mapaAtaquesNivel1.containsKey(nomePokemon)){
            return mapaAtaquesNivel1.get(nomePokemon);
        } else if (mapaAtaquesNivel2.containsKey(nomePokemon)) {
            return mapaAtaquesNivel2.get(nomePokemon);
        }
        return mapaAtaquesNivel3.get(nomePokemon);
    }

    public static Pokemon[] buscarPokemonsRandomicos(){
        int numeroPokemonsEncontrados = 0;
        int posicaoMapa;
        Pokemon[] pokemons = new Pokemon[3];
        List<String> chaves = new ArrayList<>(mapaPokemonsJaUtilizados.keySet());

        while (numeroPokemonsEncontrados < 3){
            posicaoMapa = Utilidades.random.nextInt(chaves.size());
            String chaveRandomica = chaves.get(posicaoMapa);
            if (!mapaPokemonsJaUtilizados.get(chaveRandomica)){
                pokemons[numeroPokemonsEncontrados] = new Pokemon(chaveRandomica);
                mapaPokemonsJaUtilizados.put(chaveRandomica, true);
                numeroPokemonsEncontrados++;
            }
        }
        return pokemons;
    }

    public static String getEvolucao(String nome) {
        return evolucoes.get(nome);
    }

    public static void limparMapas(){
        mapaPokemonsJaUtilizados.clear();
        mapaValorAtaque.clear();
    }
}