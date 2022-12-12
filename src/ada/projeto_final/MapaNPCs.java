package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;

public class MapaNPCs {

    private static final Map<String, String[]> mapaNPCs = new HashMap<>() {{
        put("Team Rocket", new String[]
                {"Usam roupas brancas com um chamativo R na frente das blusas, em tons de roxo, mas nas quartas feira é rosa", "Fogo"});
        put("Team Aqua", new String[]
                {"Se vestem como piratas, com uma camiseta listrada e um pano azul na cabeça, onde se encontra o emblema da Equipe Aqua.", "Água"});
        put("Team Galactic", new String[]
                {"Os membros vestem roupas similares às de um astronauta e, em função disso, algumas pessoas que não conhecem a organização ou seus integrantes os chamam de \"homens espaciais\".", "Elétrico"});
    }};

    public static String buscarDescricao(String nomeNPC){
        String[] valores = mapaNPCs.get(nomeNPC);
        return valores[0];
    }

    public static String buscarEspecialidade(String nomeNPC){
        String[] valores = mapaNPCs.get(nomeNPC);
        return valores[1];
    }
}
