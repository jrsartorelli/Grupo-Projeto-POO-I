package ada.projeto_final.mapas;

import ada.projeto_final.JogadorNPC;

import java.util.*;

public class MapaNPCs {

    private static final Random random = new Random();

    private static final Map<String, String[]> mapaNPCs = new HashMap<>() {{
        put("Team Rocket", new String[]
                {"Usam roupas brancas com um chamativo R na frente das blusas, em tons de roxo, mas nas quartas feira é rosa.", "Fogo"});
        put("Team Aqua", new String[]
                {"Se vestem como piratas, com uma camiseta listrada e um pano azul na cabeça, onde se encontra o emblema da Equipe Aqua.", "Água"});
        put("Team Galactic", new String[]
                {"Os membros vestem roupas similares às de um astronauta e, em função disso, algumas pessoas que não conhecem a organização ou seus integrantes os chamam de \"homens espaciais\".", "Elétrico"});
        put("Team Magma", new String[]
                {"Os membros vestem roupas vermelhas e pretas e usam o emblema da equipe Magma em suas blusas.", "Terra"});
        put("Team Plasma", new String[]
                {"Os membros vestem roupas azuis e brancas e usam o emblema da equipe Plasma em suas blusas.", "Gelo"});
        put("Team Miragem", new String[]
                {"Os membros vestem roupas em tons de roxo e usam o emblema da equipe Miragem em suas blusas.", "Psíquico"});
        put("Team Shadow", new String[]
                {"Os membros vestem roupas negras e usam o emblema da equipe Shadow em suas blusas.", "Sombrio"});
        put("Team Nature", new String[]
                {"Os membros vestem roupas verdes e usam o emblema da equipe Nature em suas blusas.", "Planta"});
        put("Team Starlight", new String[]
                {"Os membros vestem roupas brilhantes e estreladas e usam o emblema da equipe Starlight em suas blusas.", "Fada"});
        put("Team Dragonfire", new String[]
                {"Os membros vestem roupas vermelhas e negras e usam o emblema da equipe Dragonfire em suas blusas.", "Dragão"});
        put("Team Vortex", new String[]
                {"Os membros vestem roupas em tons de cinza e usam o emblema da equipe Vortex em suas blusas.", "Fantasma"});
        put("Team Dreamcatcher", new String[]
                {"Os membros vestem roupas em tons de azul e usam o emblema da equipe Dreamcatcher em suas blusas.", "Sono"});
        put("Team Elemental", new String[]
                {"Os membros vestem roupas em tons de verde e azul e usam o emblema da equipe Elemental em suas blusas.", "Voador"});
    }};

    private static final Map<String, Boolean> mapaNPCsJaUtilizados = new HashMap<>();

    public static void inicializarValoresNPCsJaUtilizados(){
        // Obtém os nomes de todos os NPC´s
        Set<String> listaNPCs = mapaNPCs.keySet();

        // Insere todos os NPCs no Mapa mapaNPCsJaUtilizados
        for (String nomeNPC : listaNPCs) {
            mapaNPCsJaUtilizados.put(nomeNPC, false);
        }
    }

    public static String buscarDescricao(String nomeNPC){
        String[] valores = mapaNPCs.get(nomeNPC);
        return valores[0];
    }

    public static String buscarEspecialidade(String nomeNPC){
        String[] valores = mapaNPCs.get(nomeNPC);
        return valores[1];
    }

    public static JogadorNPC[] buscarNPCsRandomicos(){
        int numeroNPCsEncontrados = 0;
        int posicaoMapa;
        JogadorNPC[] jogadoresNPCs = new JogadorNPC[3];
        List<String> chaves = new ArrayList<>(mapaNPCs.keySet());

        while (numeroNPCsEncontrados < 3){
            posicaoMapa = random.nextInt(chaves.size());
            String chaveRandomica = chaves.get(posicaoMapa);
            if (!mapaNPCsJaUtilizados.get(chaveRandomica)){
                jogadoresNPCs[numeroNPCsEncontrados] = new JogadorNPC(chaveRandomica);
                mapaNPCsJaUtilizados.put(chaveRandomica, true);
                numeroNPCsEncontrados++;
            }
        }
        return jogadoresNPCs;
    }
}
