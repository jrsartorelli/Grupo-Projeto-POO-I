package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

public class MainPartida {

    public static void main(String[] args) {

        int opcaoReiniciarBatalha = 1;
        boolean primeiraExecucao = true;
        String nomeJogador = "";
        String proximoAtacante = "Jogador"; //Armazena de quem é a vez de atacar, Jogador sempre inicia atacando

        while (opcaoReiniciarBatalha == 1) {

            if (primeiraExecucao){
                nomeJogador = Utilidades.lerStringUsuario("Bem vindo ao jogo PokeRPG!\n" +
                        "Para iniciarmos digite o seu nome: ");
            }
            inicializarValoresMapas();
            Jogador jogador = new Jogador(nomeJogador);
            JogadorNPC[] jogadoresNPCs = MapaNPCs.buscarNPCsRandomicos();
            JogadorNPC jogadorEscolhidoNPC = null;
            int rodada = 1;

            while (jogador.aptoJogar()) {

                // Se todos os NPCs estão mortos, o Jogador venceu e o programa finaliza
                if (verificaZerarJogo(jogadoresNPCs, jogador)) {
                    break;
                }

                // Se for a primeira iteração — jogadorEscolhidoNPC será null
                if (jogadorEscolhidoNPC == null) {
                    jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
                }

                // Se jogadorEscolhidoNPC não posuir Pokémons com vida — será necessário selecionar um NPC que esteja apto e
                // será necessário que o Jogador escolha um novo Pokémon para jogar após selecionado outro NPC
                if (!jogadorEscolhidoNPC.aptoJogar()) {
                    jogadorEscolhidoNPC = trocarNPC(jogador, jogadorEscolhidoNPC, jogadoresNPCs);
                }

                // Se o Pokémon do NPC não estiver vivo — será escolhido um novo Pokémon Vivo da sua lista
                // Toda a vez que o NPC substituir o seu Pokémon o Jogador também poderá escolher outro Pokémon
                if (!jogadorEscolhidoNPC.getPokemonEscolhido().estaVivo()) {
                    jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
                    jogador.escolherPokemon();
                }

                // Se for a primeira iteração — Jogador ainda não terá escolhido o seu Pokémon para a Batalha
                if (!jogador.isPokemonEscolhido()) {
                    jogador.escolherPokemon();
                }
                // Enquanto Pokémon do Jogador não estiver vivo
                reviverOuTrocarPokemon(jogador, jogadorEscolhidoNPC);

                Utilidades.imprimirComPausa("Estamos iniciando a Rodada: " + rodada + "\n");
                rodada++;

                batalhaPokemons(jogador, jogadorEscolhidoNPC, proximoAtacante);

                verificaGameOver(jogador, jogadorEscolhidoNPC);

            }
            MapaPokemons.limparMapas();
            MapaNPCs.limparMapas();

            opcaoReiniciarBatalha = Utilidades.lerIntUsuario(jogador.getNOME() + ", deseja reiniciar a Batalha:\n1 - Sim\n2 - Não" +
                    "\nEscolha sua opção (1 ou 2): ");
            if (opcaoReiniciarBatalha != 1){
                Utilidades.imprimirComPausa("\nTudo bem " + jogador.getNOME() + ", nos vemos por aí... !!!\n");
            }
            primeiraExecucao = false;
        }
        Utilidades.fecharScanner();
    }

    private static void inicializarValoresMapas(){
        MapaPokemons.inicializarValoresAtaque();
        MapaPokemons.inicializarValoresPokemonsJaUtilizados();
        MapaNPCs.inicializarValoresNPCsJaUtilizados();
    }

    private static void reviverOuTrocarPokemon(Jogador jogador, JogadorNPC jogadorEscolhidoNPC) {
        while (!jogador.getPokemonEscolhido().estaVivo()){
            // Se tem Revive para usar — Pergunta ou aplica Revive e escolhe o Pokémon para o Jogador e NPC
            // Senão apenas escolhe o Pokémon para o Jogador e NPC
            if (jogador.getNumRevives() > 0){
                // Se todos os Pokémons estiverem mortos, não é perguntado se deseja utilizar o revive,
                // é exibida a lista para selecionar qual Pokémon ele deseja reviver
                if (!jogador.existePokemonVivo() || jogador.querReviverPokemon()) {
                    jogador.escolherPokemonParaReviver();
                }
            }
            jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
            jogador.escolherPokemon();
        }
    }

    public static void batalhaPokemons(Jogador jogador,JogadorNPC jogadorEscolhidoNPC,String proximoAtacante){
        while (jogador.getPokemonEscolhido().estaVivo() && jogadorEscolhidoNPC.getPokemonEscolhido().estaVivo()) {

            if (proximoAtacante.equals("Jogador")) {
                Utilidades.imprimirComPausa("\nÉ a sua vez, " + jogador.getNOME() + " ! \n");
                Utilidades.imprimirComPausa("O Pokémon do adversário é: " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + "\n");
                Utilidades.imprimirComPausa("E possui o seguinte HP: " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida() + "\n");

                String ataqueRodada = solicitaAtaque(jogador);
                int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueRodada);
                jogadorEscolhidoNPC.getPokemonEscolhido().aplicarDano(valorAtaque);
                Utilidades.imprimirComPausa("\n" + jogador.getPokemonEscolhido().getNome() + " atacando " +
                        jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueRodada +
                        " de Poder " + valorAtaque + "\n\n");
                Utilidades.imprimirComPausa("Vida do Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " de " +
                        jogadorEscolhidoNPC.getNOME() +
                        " = " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida() + "\n");

                if (jogadorEscolhidoNPC.getPokemonEscolhido().getVida() <= 0) {
                    Utilidades.imprimirComPausa("\nParabéns " + jogador.getNOME() + " !!!\nSeu Pokémon " + jogador.getPokemonEscolhido().getNome() +
                            " detonou o Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " do Time: " +
                            jogadorEscolhidoNPC.getNOME() + "\n\n");
                    Utilidades.imprimirComPausa(jogadorEscolhidoNPC.getNOME() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("perdePokemon") + "\"\n");
                }
            } else {
                Utilidades.imprimirComPausa("\nAgora é a vez de " + jogadorEscolhidoNPC.getNOME() + " atacar, se prepare !!!\n");
                String ataqueNPC = jogadorEscolhidoNPC.getPokemonEscolhido().buscarAtaqueRandomico();
                int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueNPC);
                jogador.getPokemonEscolhido().aplicarDano(valorAtaque);
                Utilidades.imprimirComPausa("\n" + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " atacando " +
                        jogador.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueNPC + " de Poder " + valorAtaque + "\n\n");
                Utilidades.imprimirComPausa("Vida do seu Pokémon " + jogador.getPokemonEscolhido().getNome() +
                        " = " + jogador.getPokemonEscolhido().getVida() + "\n");
                if (jogador.getPokemonEscolhido().getVida() <= 0) {
                    Utilidades.imprimirComPausa("Oh não! Você perdeu o pokemon " + jogador.getPokemonEscolhido().getNome() + "!\n");
                    Utilidades.imprimirComPausa(jogadorEscolhidoNPC.getNOME() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("mataPokemon") + "\"\n");
                }
            }

            if (proximoAtacante.equals("Jogador")) {
                proximoAtacante = "NPC";
            } else {
                proximoAtacante = "Jogador";
            }
        }
    }


    public static JogadorNPC escolherNPC(JogadorNPC... jogadores) {
        int opcaoNPCJogador;
        JogadorNPC jogadorEscolhidoNPC = null;
        int[] arrayMapaNPCs = new int[jogadores.length];
        int contadorOpcoes = 0;
        StringBuilder mensagem = new StringBuilder("\nEstes são os Times Adversários Disponíveis:\n");

        for (int i = 0; i < jogadores.length; i++){
            if (jogadores[i].aptoJogar()){
                arrayMapaNPCs[contadorOpcoes] = i;
                mensagem.append(++contadorOpcoes).append(" - ").append(jogadores[i].getNOME());
                mensagem.append(": ").append(jogadores[i].getDESCRICAO()).append("\n").append("    Especialidade: ");
                mensagem.append(jogadores[i].getESPECIALIDADE()).append("\n");
            }
        }

        if (contadorOpcoes == 1){
            jogadorEscolhidoNPC =  jogadores[arrayMapaNPCs[0]];
        } else {
            StringBuilder mensagem2 = new StringBuilder("(");
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2.append(i).append(" ou ");
                } else if(i == contadorOpcoes){
                    mensagem2.append(i).append("): ");
                } else{
                    mensagem2.append(i).append(", ");
                }
            }
            mensagem.append("\nEscolha qual Time você quer enfrentar entre o(s) ").append(contadorOpcoes).append(" que pode enfrentar ").append(mensagem2);
        }

        while (contadorOpcoes > 1) {

            opcaoNPCJogador = Utilidades.lerIntUsuario(mensagem.toString());
            if (opcaoNPCJogador <= contadorOpcoes && opcaoNPCJogador > 0) {
                jogadorEscolhidoNPC = jogadores[arrayMapaNPCs[opcaoNPCJogador-1]];
                contadorOpcoes = 1;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }

        assert jogadorEscolhidoNPC != null;
        Utilidades.imprimirComPausa("\nVocê enfrentará o Time: " + jogadorEscolhidoNPC.getNOME() + "\n");
        jogadorEscolhidoNPC.escolherPokemonNPCRandomico();

        return jogadorEscolhidoNPC;
    }

    //lembrar de não deixar solicitar ataque com pokemons mortos e com revive disponivel
    public static String solicitaAtaque(Jogador jogador) {
        int opcaoAtaqueJogador;
        while (true) {
            opcaoAtaqueJogador = Utilidades.lerIntUsuario("\nOs ataques disponíveis do " +
                    jogador.getPokemonEscolhido().getNome() + " são:\n" + jogador.getPokemonEscolhido().buscarAtaques() +
                    "\nEscolha uma opção de ataque (1, 2 ou 3): ");
            if (opcaoAtaqueJogador == 1 || opcaoAtaqueJogador == 2 || opcaoAtaqueJogador == 3) {
                jogador.getPokemonEscolhido().setIndiceAtaqueEscolhido(opcaoAtaqueJogador-1);
                return jogador.getPokemonEscolhido().getAtaqueEscolhido();
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

    public static boolean existeNPCVivo(JogadorNPC[] jogadoresNPCs){
        for (JogadorNPC jogadorNPC : jogadoresNPCs) {
            if(jogadorNPC.aptoJogar()){
                return true;
            }
        }
        return false;
    }

    private static boolean verificaZerarJogo(JogadorNPC[] jogadoresNPCs, Jogador jogador) {
        if (!existeNPCVivo(jogadoresNPCs)){
            Utilidades.imprimirComPausa("\nParabéns " + jogador.getNOME() +
                    " !!!\nA Batalha foi árdua, mas não haviam dúvidas sobre sua Vitória !!!\n" +
                    "Nos vemos na próxima Batalha PokeRPG !!!\n\n");
            return true;
        }
        return false;
    }

    private static void verificaGameOver(Jogador jogador, JogadorNPC jogadorEscolhidoNPC) {
        if (!jogador.aptoJogar()){
            Utilidades.imprimirComPausa(jogadorEscolhidoNPC.getNOME() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("vitoria") + "\"\n");
            Utilidades.imprimirComPausa("\n" + jogador.getNOME() + ", Infelizmente você perdeu todos os seus Pokémons !!!\nO time: " +
                    jogadorEscolhidoNPC.getNOME() + " é o Grande Vencedor !!!\nDesejo mais sorte em sua próxima Batalha !!!\n\n");
        }
    }

    private static JogadorNPC trocarNPC(Jogador jogador, JogadorNPC jogadorEscolhidoNPC, JogadorNPC[] jogadoresNPCs) {
        Utilidades.imprimirComPausa(jogadorEscolhidoNPC.getNOME() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("derrota") + "\"\n\n");
        Utilidades.imprimirComPausa("Saudações " +jogador.getNOME() + ", você desintegrou todo o time: " + jogadorEscolhidoNPC.getNOME() + "\n");
        Utilidades.imprimirComPausa("Por sua bravura, você vai ganhar a Pedra da Evolução !!!\n");
        jogador.ganharPedraEvolucao();
        if (jogador.escolherPokemonParaEvoluir()){
            Utilidades.imprimirComPausa("Processo de Evolução Concluído !\n");
        }
        else {
            System.err.println("Falha no processo de Evolução, ligar no Suporte");
        }
        jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
        jogador.escolherPokemon();

        return jogadorEscolhidoNPC;
    }

}
