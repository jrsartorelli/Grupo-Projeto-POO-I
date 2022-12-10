package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;

public class MapaAtaques {
    private static Map<String, String[]> mapaAtaques = new HashMap<>() {{
        put("Charmander", new String[]{"FireBall", "FlameThrower", "FireBlast"});
        put("Bulbasaur", new String[]{"Take Down", "Petal Dance", "Solar Beam"});
        put("Squirtle", new String[]{"Rapid Spin", "Water Gun", "Aqua Tail"});
    }};

    public static String[] buscarAtaques(String nomePokemon){
        return mapaAtaques.get(nomePokemon);
    }
}
