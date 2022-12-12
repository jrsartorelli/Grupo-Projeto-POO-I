package ada.projeto_final;

import java.util.*;

public class MapaNPCs {

    private static final Random random = new Random();

    private static final Map<String, String[]> mapaNPCs = new HashMap<>() {{
        put("Team Rocket", new String[]
                {"Usam roupas brancas com um chamativo R na frente das blusas, em tons de roxo, mas nas quartas feira é rosa", "Fogo"});
        put("Team Aqua", new String[]
                {"Se vestem como piratas, com uma camiseta listrada e um pano azul na cabeça, onde se encontra o emblema da Equipe Aqua.", "Água"});
        put("Team Galactic", new String[]
                {"Os membros vestem roupas similares às de um astronauta e, em função disso, algumas pessoas que não conhecem a organização ou seus integrantes os chamam de \"homens espaciais\".", "Elétrico"});
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
        JogadorNPC[] jogadoresNPC = new JogadorNPC[3];
        List<String> chaves = new ArrayList<>(mapaNPCs.keySet());

        while (numeroNPCsEncontrados < 3){
            posicaoMapa = random.nextInt(chaves.size());
            String chaveRandomica = chaves.get(posicaoMapa);
            if (!mapaNPCsJaUtilizados.get(chaveRandomica)){
                jogadoresNPC[numeroNPCsEncontrados] = new JogadorNPC(chaveRandomica);
                mapaNPCsJaUtilizados.put(chaveRandomica, true);
                numeroNPCsEncontrados++;
            }
        }
        return jogadoresNPC;
    }
}
