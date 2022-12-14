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

            if(jogadorEscolhidoNPC == null || !jogadorEscolhidoNPC.aptoJogar()){
                jogadorEscolhidoNPC = escolherNPC(jogadoresNPCs);
                jogadorEscolhidoNPC.escolherPokemonNPCRandomico();
                System.out.println("\n" + jogadorEscolhidoNPC.getNome() + " escolheu o Pokémon " +
                        jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " para iniciar no campo de batalha!");
                System.out.println(jogadorEscolhidoNPC.getNome() + ": \"" + jogadorEscolhidoNPC.getFrasesDeEfeito().get("inicio") + "\"");
            }
            if (rodada == 1) {
                jogador.escolherPokemon();
                System.out.println("\nVocê escolheu: " + jogador.getPokemonEscolhido().getNome() +
                        " para iniciar no campo de batalha!\n");
                System.out.println("Vai começar a batalha de número \"" + rodada + "\" !!!\n");
                String atacante;
                if(proximoAtacante.equals("Jogador")){
                    atacante = "Você";
                } else {
                    atacante = jogadorEscolhidoNPC.getNome();
                }
                System.out.println("Nessa rodada, " + atacante + " começa atacando...");
                System.out.println();

                while(jogador.aptoJogar() && jogadorEscolhidoNPC.aptoJogar()) {
                    if (proximoAtacante.equals("Jogador")) {
                        //para fazer=> escolher a ação: atacar ou reviver.
                        System.out.println("É a sua vez, " + jogador.getNome() + " ! ");
                        System.out.println("O Pokémon do adversário é: " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome());
                        System.out.println("E possui o seguinte HP: " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());
                        int acaoEscolhida = escolherAcao(jogador);
                        while (acaoEscolhida == -1) {
                            escolherAcao(jogador);
                        }
                        if (acaoEscolhida == 2) { // revive
                            System.out.println("Revive solicitado!");
                            if (jogador.getNumRevives() > 0) {
                                System.out.println();
                                if(!jogador.usarRevive(jogador.getPokemonEscolhido())) {
                                    System.out.println("Você deve atacar agora.");
                                    acaoEscolhida = 1;
                                }
                            } else {
                                System.out.println("Você já usou o revive nessa roodada. Não é possível mais reviver nenhum pokemon.");
                                System.out.println("Você deve atacar agora.");
                                acaoEscolhida = 1;
                            }
                        }
                        if (acaoEscolhida == 1) { // ataque
                            String ataqueRodada = solicitaAtaque(jogador);
                            int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueRodada);
                            jogadorEscolhidoNPC.getPokemonEscolhido().aplicarDano(valorAtaque);
                            System.out.println("\n" + jogador.getPokemonEscolhido().getNome() + " atacando " +
                                    jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueRodada +
                                    " de Poder " + valorAtaque + "\n");
                            System.out.println("Vida do Pokémon " + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " de " +
                                    jogadorEscolhidoNPC.getNome() +
                                    " = " + jogadorEscolhidoNPC.getPokemonEscolhido().getVida());
                        }
                    }else {
                        String ataqueNPC = jogadorEscolhidoNPC.getPokemonEscolhido().buscarAtaqueRandomico();
                        int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueNPC);
                        System.out.println("\nÉ a vez do seu adversário: " + jogadorEscolhidoNPC.getNome() + " ! ");
                        jogador.getPokemonEscolhido().aplicarDano(valorAtaque);
                        System.out.println("\n" + jogadorEscolhidoNPC.getPokemonEscolhido().getNome() + " atacando " +
                                jogador.getPokemonEscolhido().getNome() + "\nCom seu Ataque " + ataqueNPC + " de Poder " + valorAtaque + "\n");
                        System.out.println("Vida do seu Pokémon " + jogador.getPokemonEscolhido().getNome() +
                                " = " + jogador.getPokemonEscolhido().getVida() + "\n");
                    }
                    //Realiza a troca de quem vai atacar na próxima rodada
                    if (proximoAtacante.equals("Jogador")){
                        proximoAtacante = "NPC";
                    } else {
                        proximoAtacante = "Jogador";
                    }
                }
                rodada++;
            } else if (rodada == 2) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar()) {     //se morreu o primeiro npc, passa o segundo e o terceiro como escolha
                    jogadorNPC = escolherNPC(jogadoresNPCs[1], jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar()) {    //se morreu o segundo npc, passa o primeiro e o terceiro como escolha
                    jogadorNPC = escolherNPC(jogadoresNPCs[0], jogadoresNPCs[2]);
                } else {         //se morreu o terceiro npc, passa o segundo e o primeiro como escolha
                    jogadorNPC = escolherNPC(jogadoresNPCs[0], jogadoresNPCs[1]);
                }
                break;
            } else if (rodada == 3) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar() && jogadoresNPCs[1].aptoJogar()) {   // segue a lógica da segunda rodada, com a diferença que morreram dois
                    jogadorNPC = escolherNPC(jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar() && jogadoresNPCs[2].aptoJogar()) {
                    jogadorNPC = escolherNPC(jogadoresNPCs[0]);
                } else {
                    jogadorNPC = escolherNPC(jogadoresNPCs[1]);
                }
                break;
            }
        }
        Utilidades.fecharScanner();
    }

    private static void inicializarValoresMapas(){
        MapaPokemons.inicializarValoresAtaque();
        MapaPokemons.inicializarValoresPokemonsJaUtilizados();
        MapaNPCs.inicializarValoresNPCsJaUtilizados();
    }

    public static boolean reviverPokemon(Pokemon pokemonAtual, Jogador J) {
        boolean aux = J.usarRevive(pokemonAtual);
        if (aux) {
            System.out.println("O pokemon " + pokemonAtual.getNome() + " está de volta ao campo de batalha!");
            return true;
        } else {
            System.out.println("Não é possível reviver o pokemon.");
            return false;
        }
    }

    public static JogadorNPC escolherNPC(JogadorNPC... jogadores) {
        int opcaoNPCJogador;
        int[] arrayMapaNPCs = new int[jogadores.length];
        int contadorOpcoes = 0;
        String mensagem = "\nEstes são os Times Adversários:\n";

        for (int i = 0; i < jogadores.length; i++){
            if (jogadores[i].aptoJogar()){
                arrayMapaNPCs[contadorOpcoes] = i;
                mensagem += ++contadorOpcoes + " - " + jogadores[i].getNome() + ": " + jogadores[i].getDescricao() + "\n";
            }
        }

        if (contadorOpcoes == 1){
            System.out.println("Você enfrentará: " + jogadores[arrayMapaNPCs[0]].getNome());
            return jogadores[arrayMapaNPCs[0]];
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
            mensagem += "\nEscolha qual Time você quer enfrentar entre o(s) " + jogadores.length +
                    " que pode enfrentar " + mensagem2;
        }

        while (true) {
            opcaoNPCJogador = Utilidades.lerIntUsuario(mensagem);
            if (opcaoNPCJogador <= jogadores.length && opcaoNPCJogador > 0) {
                System.out.println("\nVocê enfrentará o Time: " + jogadores[arrayMapaNPCs[opcaoNPCJogador-1]].getNome());
                return jogadores[arrayMapaNPCs[opcaoNPCJogador-1]];
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
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
    public static int estabeleceVez(){
        return Utilidades.random.nextInt(1);
    }

    public static int escolherAcao(Jogador jog){
        while (true) {
            int entrada = Utilidades.lerIntUsuario("\nQual ação deseja realizar? (1 ou 2)\n" +
                    "1 - Atacar\n" + ((jog.getNumRevives()>0 && jog.getNumRevives() > 0)?"2 - Reviver: ":": ") +
                    "");
            if (entrada == 1) {
                return entrada;
            }

            if (entrada == 2 && jog.getNumRevives()>0) {
                return entrada;
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
