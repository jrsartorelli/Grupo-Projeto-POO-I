package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        String proximoAtacante = "Jogador"; //Armazena de quem é a vez de atacar, Jogador sempre inicia atacando
        nomeJogador = Utilidades.lerStringUsuario("Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        inicializarValoresMapas();
        Jogador jogador = new Jogador(nomeJogador);
        JogadorNPC[] jogadoresNPCs = MapaNPCs.buscarNPCsRandomicos();
        JogadorNPC jogadorEscolhidoNPC = null;
        int rodada = 1;

        while (jogador.aptoJogar()) {

            // Se todos os NPCs estão mortos, o Jogador venceu e o programa finaliza
            if (!existeNPCVivo(jogadoresNPCs)){
                System.out.println("\nParabéns " + jogador.getNome() +
                        " !!!\nA Batalha foi árdua, mas não haviam dúvidas sobre sua Vitória !!!\n" +
                        "Nos vemos na próxima Batalha PokeRPG !!!\n");
                break;
            }

            // Se for a primeira iteração — jogadorEscolhidoNPC será null
            if (jogadorEscolhidoNPC == null){
                jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
            }

            // Se jogadorEscolhidoNPC não posuir Pokémons com vida — será necessário selecionar um NPC que esteja apto e
            // será necessário que o Jogador escolha um novo Pokémon para jogar após selecionado outro NPC
            if (!jogadorEscolhidoNPC.aptoJogar()){
                jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
                jogador.escolherPokemon();
            }

            // Se o Pokémon do NPC não estiver vivo — será escolhido um novo Pokémon Vivo da sua lista
            // Toda a vez que o NPC substituir o seu Pokémon o Jogador também poderá escolher outro Pokémon
            if (!jogadorEscolhidoNPC.getPokemonEscolhido().estaVivo()){
                jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
                jogador.escolherPokemon();
            }

            // Se for a primeira iteração — Jogador ainda não terá escolhido o seu Pokémon para a Batalha
            if (!jogador.isPokemonEscolhido()){
                jogador.escolherPokemon();
            }

            // Se Pokémon do Jogador não estiver vivo, será necessário escolher outro
            if (!jogador.getPokemonEscolhido().estaVivo()){
                // Perguntamos ao Jogador se deseja reviver um dos Pokémons mortos
                // Se todos os Pokémons estiverem mortos, não é perguntado se deseja utilizar o revive,
                // mostramos a lista para selecionar qual Pokémon ele deseja reviver
                if (jogador.getNumRevives() > 0){
                    if (jogador.querReviverPokemon()) {
                        jogador.escolherPokemonParaReviver();
                    }
                }
                jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
                jogador.escolherPokemon();
            }

            System.out.println("Estamos iniciando a Rodada: " + rodada);
            rodada++;

            while (jogador.getPokemonEscolhido().estaVivo() && jogadorEscolhidoNPC.getPokemonEscolhido().estaVivo()) {

                if (proximoAtacante.equals("Jogador")) {
                    System.out.println("\nÉ a sua vez, " + jogador.getNome() + " ! ");
                    System.out.println("O Pokémon do adversário é: " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome());
                    System.out.println("E possui o seguinte HP: " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());

                    String ataqueRodada = solicitaAtaque(jogador);
                    int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueRodada);
                    jogadorEscolhidoNPC.getPokemonEscolhido().aplicarDano(valorAtaque);
                    System.out.println("\n" + jogador.getPokemonEscolhido().getNome() + " atacando " +
                            jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueRodada +
                            " de Poder " + valorAtaque + "\n");
                    System.out.println("Vida do Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " de " +
                            jogadorEscolhidoNPC.getNome() +
                            " = " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());

                    if (jogadorEscolhidoNPC.getPokemonEscolhido().getVida() <= 0) {
                        System.out.println("\nParabéns " + jogador.getNome() + " !!!\nSeu Pokémon " + jogador.getPokemonEscolhido().getNome() +
                                " detonou o Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " do Time: " +
                                jogadorEscolhidoNPC.getNome() + "\n");
                        System.out.println(jogadorEscolhidoNPC.getNome() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("perdePokemon") + "\"");
                    }
                } else {
                    System.out.println("\nAgora é a vez de " + jogadorEscolhidoNPC.getNome() + " atacar, se prepare !!!");
                    String ataqueNPC = jogadorEscolhidoNPC.getPokemonEscolhido().buscarAtaqueRandomico();
                    int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueNPC);
                    jogador.getPokemonEscolhido().aplicarDano(valorAtaque);
                    System.out.println("\n" + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " atacando " +
                            jogador.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueNPC + " de Poder " + valorAtaque + "\n");
                    System.out.println("Vida do seu Pokémon " + jogador.getPokemonEscolhido().getNome() +
                            " = " + jogador.getPokemonEscolhido().getVida());
                    if (jogador.getPokemonEscolhido().getVida() <= 0) {
                        System.out.println("Oh não! Você perdeu o pokemon " + jogador.getPokemonEscolhido().getNome() + "!");
                        System.out.println(jogadorEscolhidoNPC.getNome() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("mataPokemon") + "\"");
                    }
                }

                if (proximoAtacante.equals("Jogador")) {
                    proximoAtacante = "NPC";
                } else {
                    proximoAtacante = "Jogador";
                }
            }
            if (!jogador.aptoJogar()){
                System.out.println("\n" + jogador.getNome() + ", Infelizmente você perdeu todos os seus Pokémons !!!\nO time: " +
                        jogadorEscolhidoNPC.getNome() + " é o Grande Vencedor !!!\nDesejo mais sorte em sua próxima Batalha !!!\n");
            }
        }
        Utilidades.fecharScanner();
    }

    private static void inicializarValoresMapas(){
        MapaPokemons.inicializarValoresAtaque();
        MapaPokemons.inicializarValoresPokemonsJaUtilizados();
        MapaNPCs.inicializarValoresNPCsJaUtilizados();
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
                mensagem.append(++contadorOpcoes).append(" - ").append(jogadores[i].getNome());
                mensagem.append(": ").append(jogadores[i].getDescricao()).append("\n").append("    Especialidade: ");
                mensagem.append(jogadores[i].getEspecialidade()).append("\n");
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
                break;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }

        System.out.println("\nVocê enfrentará o Time: " + jogadorEscolhidoNPC.getNome());
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
}
