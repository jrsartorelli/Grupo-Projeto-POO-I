package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapaAtaques {
    private static final Integer NIVEL_1 = 1;
    private static final Integer NIVEL_2 = 2;
    private static final Integer NIVEL_3 = 3;
    private static final Integer VALOR_ATAQUE_BASE_NIVEL1 = 300;
    private static final Integer VALOR_ATAQUE_BASE_NIVEL2 = 400;
    private static final Integer VALOR_ATAQUE_BASE_NIVEL3 = 500;
    private static final Random random = new Random();

    private static final Map<String, String[]> mapaAtaquesNivel1 = new HashMap<>() {{
        put("Charmander", new String[]{"Fire Ball", "Flame Thrower", "Fire Blast"});
        put("Bulbasaur", new String[]{"Take Down", "Petal Dance", "Solar Beam"});
        put("Squirtle", new String[]{"Bubble Beam", "Water Gun", "Aqua Tail"});
    }};

    private static final Map<String, String[]> mapaAtaquesNivel2 = new HashMap<>() {{
        put("Charmeleon", new String[]{"Fire Spin", "Dragon Claw", "Headbutt"});
        put("Ivysaur", new String[]{"Vine Whip", "Razor Leaf", "Tackle"});
        put("Wartortle", new String[]{"Hydro Pump", "Rapid Spin", "Bite"});
    }};

    private static final Map<String, String[]> mapaAtaquesNivel3 = new HashMap<>() {{
        put("Charizard", new String[]{"Slash", "Steel Wing", "Seismic Toss"});
        put("Venusaur", new String[]{"Sweet Scent", "Sleep Powder", "Frenzy Plant"});
        put("Blastoise", new String[]{"Withdraw", "Rain Dance", "Skull Bash"});
    }};

    //Mapa associando o Nível do Pokémon aos seus respetivos ataques
    private static final Map<Integer, Map> mapaPokemons = new HashMap<>() {{
        put(NIVEL_1, mapaAtaquesNivel1);
        put(NIVEL_2, mapaAtaquesNivel2);
        put(NIVEL_3, mapaAtaquesNivel3);
    }};

    //Mapa com o valor para cada tipo de ataque
    private static final Map<String, Integer> mapaValorAtaque = new HashMap<>();

    //Insere de forma randômica os valores para todos os ataques listados
    public static void inicializarValoresAtaque(){
        int valorAtaqueGerado;
        for (String[] ataques : mapaAtaquesNivel1.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE_NIVEL1 + random.nextInt(0, 300));
                mapaValorAtaque.put(ataque, valorAtaqueGerado);
            }
        }
        for (String[] ataques : mapaAtaquesNivel2.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE_NIVEL2 + random.nextInt(0, 300));
                mapaValorAtaque.put(ataque, valorAtaqueGerado);
            }
        }
        for (String[] ataques : mapaAtaquesNivel3.values()) {
            for (String ataque : ataques) {
                valorAtaqueGerado = (VALOR_ATAQUE_BASE_NIVEL3 + random.nextInt(0, 300));
                mapaValorAtaque.put(ataque, valorAtaqueGerado);
            }
        }
    }

    public static Integer buscarValorAtaque(String ataque) {
        return mapaValorAtaque.get(ataque);
    }

    public static String[] buscarAtaques(String nomePokemon){
        return mapaAtaquesNivel1.get(nomePokemon);
    }
}
