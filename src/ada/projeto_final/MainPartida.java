package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        boolean jogadorPerdeuPokemon = false;
        boolean NPCperdeuPokemon = false;
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
                System.out.println("Parabéns " + jogador.getNome() +
                        " !!!\nA Batalha foi árdua, mas não haviam dúvidas sobre sua Vitória !!!\n" +
                        "Nos vemos na próxima Batalha PokeRPG !!!\n");
                break;
            }

            // Se for a primeira iteração -> jogadorEscolhidoNPC será null
            // Se jogadorEscolhidoNPC não posuir Pokémons com vida -> será necessário selecionar um NPC que esteja apto e
            // será necessário que o Jogador escolha um novo Pokémon para jogar depois de selecionado um outro NPC
            if (jogadorEscolhidoNPC == null || !jogadorEscolhidoNPC.aptoJogar()){
                boolean precisaJogadorEscolherPokemon = false;
                if (jogadorEscolhidoNPC != null){
                    precisaJogadorEscolherPokemon = true;
                }
                jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
                if (precisaJogadorEscolherPokemon){
                    jogador.escolherPokemon();
                }
            }

            // Se o Pokémon do NPC não estiver vivo -> será escolhido um novo Pokémon Vivo de sua lista
            if (!jogadorEscolhidoNPC.getPokemonEscolhido().estaVivo()){
                jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
                jogador.escolherPokemon();
            }

            // Se for a primeira iteração -> Jogador ainda não terá escolhido seu Pokémon para a Batalha
            // Se Pokémon do Jogador não estiver vivo, tem que escolher outro
            if (!jogador.isPokemonEscolhido() || !jogador.getPokemonEscolhido().estaVivo()){
                if(jogador.existePokemonMorto() && jogador.getNumRevives() > 0){
                    if (jogador.querReviverPokemon()){
                        jogador.escolherPokemonParaReviver();
                    }
                }
                jogador.escolherPokemon();
                jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
            }

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

//            if (!jogador.aptoJogar()) {
//                System.out.println("Game over: você perdeu a batalha.");
//                System.out.println(jogadorEscolhidoNPC.getNome() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("vitoria") + "\"");
//                break;
//            }
//
//
//                while(jogador.aptoJogar() && jogadorEscolhidoNPC.aptoJogar()) {
//                    if (proximoAtacante.equals("Jogador")) {
//                        //para fazer=> escolher a ação: atacar ou reviver.
//                        System.out.println("É a sua vez, " + jogador.getNome() + " ! ");
//                        System.out.println("O Pokémon do adversário é: " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome());
//                        System.out.println("E possui o seguinte HP: " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());
//                        int acaoEscolhida = escolherAcao(jogador);
//                        while (acaoEscolhida == -1) {
//                            escolherAcao(jogador);
//                        }
//                        if (acaoEscolhida == 2) { // revive
//                            System.out.println("Revive solicitado!");
//                            if (jogador.getNumRevives() > 0) {
//                                System.out.println();
//                                if(!jogador.usarRevive(jogador.getPokemonEscolhido())) {
//                                    System.out.println("Não é possível reviver o pokemon.");
//                                    System.out.println("Você deve atacar agora.");
//                                    acaoEscolhida = 1;
//                                }
//                            } else {
//                                System.out.println("Você já usou o revive nessa rodada. Não é possível mais reviver nenhum pokemon.");
//                                System.out.println("Você deve atacar agora.");
//                                acaoEscolhida = 1;
//                            }
//                        }
//                        if (acaoEscolhida == 1) { // ataque
//                            String ataqueRodada = solicitaAtaque(jogador);
//                            int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueRodada);
//                            jogadorEscolhidoNPC.getPokemonEscolhido().aplicarDano(valorAtaque);
//                            System.out.println("\n" + jogador.getPokemonEscolhido().getNome() + " atacando " +
//                                    jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueRodada +
//                                    " de Poder " + valorAtaque + "\n");
//                            System.out.println("Vida do Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " de " +
//                                    jogadorEscolhidoNPC.getNome() +
//                                    " = " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());
//                        }
//                    } else {
//                        String ataqueNPC = jogadorEscolhidoNPC.getPokemonEscolhido().buscarAtaqueRandomico();
//                        int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueNPC);
//                        System.out.println("\nÉ a vez do seu adversário: " + jogadorEscolhidoNPC.getNome() + " ! ");
//                        jogador.getPokemonEscolhido().aplicarDano(valorAtaque);
//                        System.out.println("\n" + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " atacando " +
//                                jogador.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueNPC + " de Poder " + valorAtaque + "\n");
//                        System.out.println("Vida do seu Pokémon " + jogador.getPokemonEscolhido().getNome() +
//                                " = " + jogador.getPokemonEscolhido().getVida() + "\n");
//                        if (jogador.getPokemonEscolhido().getVida() <= 0) {
//                            System.out.println("Oh não! Você perdeu o pokemon " + jogador.getPokemonEscolhido().getNome() + "!");
//                            System.out.println(jogadorEscolhidoNPC.getNome() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("mataPokemon") + "\"");
//                            if (!jogador.aptoJogar()) {
//                                System.out.println("Game over: você perdeu a batalha.");
//                                System.out.println(jogadorEscolhidoNPC.getNome() + " diz: \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("vitoria") + "\"");
//                                break;
//                            }
//                            jogadorPerdeuPokemon = true;
//                        }
//                        if (jogadorPerdeuPokemon) {
//                            jogador.escolherPokemon();
//                            System.out.println("\nVocê escolheu: " + jogador.getPokemonEscolhido().getNome() +
//                                    " para continuar no campo de batalha!\n");
//                            // implementar continuação após jogador perder pokemon
//                        }
//                    }
//                    //Realiza a troca de quem vai atacar na próxima rodada
//                    if (proximoAtacante.equals("Jogador")){
//                        proximoAtacante = "NPC";
//                    } else {
//                        proximoAtacante = "Jogador";
//                    }
//                }
//                rodada++;
//            } else if (rodada == 2) {
//                JogadorNPC jogadorNPC;
//                if (jogadoresNPCs[0].aptoJogar()) {     //se morreu o primeiro npc, passa o segundo e o terceiro como escolha
//                    jogadorNPC = escolherNPC(jogadoresNPCs[1], jogadoresNPCs[2]);
//                } else if (jogadoresNPCs[1].aptoJogar()) {    //se morreu o segundo npc, passa o primeiro e o terceiro como escolha
//                    jogadorNPC = escolherNPC(jogadoresNPCs[0], jogadoresNPCs[2]);
//                } else {         //se morreu o terceiro npc, passa o segundo e o primeiro como escolha
//                    jogadorNPC = escolherNPC(jogadoresNPCs[0], jogadoresNPCs[1]);
//                }
//                break;
//            } else if (rodada == 3) {
//                JogadorNPC jogadorNPC;
//                if (jogadoresNPCs[0].aptoJogar() && jogadoresNPCs[1].aptoJogar()) {   // segue a lógica da segunda rodada, com a diferença que morreram dois
//                    jogadorNPC = escolherNPC(jogadoresNPCs[2]);
//                } else if (jogadoresNPCs[1].aptoJogar() && jogadoresNPCs[2].aptoJogar()) {
//                    jogadorNPC = escolherNPC(jogadoresNPCs[0]);
//                } else {
//                    jogadorNPC = escolherNPC(jogadoresNPCs[1]);
//                }
//                break;
//            }
        }
        Utilidades.fecharScanner();
    }

    private static void inicializarValoresMapas(){
        MapaPokemons.inicializarValoresAtaque();
        MapaPokemons.inicializarValoresPokemonsJaUtilizados();
        MapaNPCs.inicializarValoresNPCsJaUtilizados();
    }

    // Esqueci que já estava implementado, Afffff
//    public static boolean reviverPokemon(Pokemon pokemonAtual, Jogador J) {
//        boolean aux = J.usarRevive(pokemonAtual);
//        if (aux) {
//            System.out.println("O pokemon " + pokemonAtual.getNome() + " está de volta ao campo de batalha!");
//            return true;
//        } else {
//            System.out.println("Não é possível reviver o pokemon.");
//            return false;
//        }
//    }

    public static JogadorNPC escolherNPC(JogadorNPC... jogadores) {
        int opcaoNPCJogador;
        JogadorNPC jogadorEscolhidoNPC = null;
        int[] arrayMapaNPCs = new int[jogadores.length];
        int contadorOpcoes = 0;
        String mensagem = "\nEstes são os Times Adversários:\n";

        for (int i = 0; i < jogadores.length; i++){
            if (jogadores[i].aptoJogar()){
                arrayMapaNPCs[contadorOpcoes] = i;
                mensagem += ++contadorOpcoes + " - " + jogadores[i].getNome() + ": " +
                        jogadores[i].getDescricao() + "\n" + "    Especialidade: " +
                        jogadores[i].getEspecialidade() + "\n";
            }
        }

        if (contadorOpcoes == 1){
            jogadorEscolhidoNPC =  jogadores[arrayMapaNPCs[0]];
        } else {
            String mensagem2 = "(";
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2 += i + " ou ";
                } else if(i == contadorOpcoes){
                    mensagem2 += i + "): ";
                } else{
                    mensagem2 += i + ", ";
                }
            }
            mensagem += "\nEscolha qual Time você quer enfrentar entre o(s) " + contadorOpcoes +
                    " que pode enfrentar " + mensagem2;
        }

        while (contadorOpcoes > 1) {

            opcaoNPCJogador = Utilidades.lerIntUsuario(mensagem);
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

    public static int escolherAcao(Jogador jog){
        while (true) {
            int numeroOpcoes = (jog.getNumRevives() > 0) ? 2 : 1;
            if (numeroOpcoes == 1){
                return 1;
            } else{
                int entrada = Utilidades.lerIntUsuario("\nQual ação deseja realizar?\n" +
                        "1 - Atacar\n" + "2 - Reviver" +
                        "\nEscolha uma opção (1 ou 2): ");
                if (entrada == 1 || entrada == 2){
                    return entrada;
                }
            }
            System.err.println("Erro: número escolhido inválido.\n");
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
