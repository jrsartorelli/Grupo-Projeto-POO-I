package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapaAtaques {
    private static Map<String, String[]> mapaAtaques = new HashMap<>() {{
        put("Charmander", new String[]{"Fire Ball", "Flame Thrower", "Fire Blast"});
        put("Bulbasaur", new String[]{"Take Down", "Petal Dance", "Solar Beam"});
        put("Squirtle", new String[]{"Rapid Spin", "Water Gun", "Aqua Tail"});
    }};
    private static Map<String, Integer> mapaValorAtaque = new HashMap<>();
    private static final Integer VALOR_ATAQUE_BASE = 300;
    private static Random random = new Random();

    public static void inicializarValoresAtaque(){
        for (String[] ataques : mapaAtaques.values()) {
            for (String ataque: ataques) {
                mapaValorAtaque.put(ataque, (VALOR_ATAQUE_BASE + random.nextInt(0, 300)));
            }
        }
    }

    public static Integer buscarValorAtaque(String ataque) {
        return mapaValorAtaque.get(ataque);
    }

    public static String[] buscarAtaques(String nomePokemon){
        return mapaAtaques.get(nomePokemon);
    }
}
