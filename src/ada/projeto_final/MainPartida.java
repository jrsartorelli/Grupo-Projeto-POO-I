package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

import java.util.Random;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        String proximoAtacante = "Jogador"; //Armazena de quem é a vez de atacar, Jogador sempre inicia atacando
        nomeJogador = Utilidades.lerStringUsuario("Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        inicializarValoresMapas();
        Jogador jogador = new Jogador(nomeJogador);
        JogadorNPC[] jogadoresNPCs = MapaNPCs.buscarNPCsRandomicos();

        int rodada = 1; // vai contar em qual rodada está
        while (jogador.aptoJogar()) {
            if (rodada == 1) {
                System.out.println("Vai começar a batalha " + rodada + ", escolha seu pokemon para o campo de batalha!!!");
                jogador.escolherPokemon();
                //Pokemon pokemonJogador = jogador.getPokemonEscolhido();
                System.out.println("Você escolheu: " + jogador.getNomePokemonEscolhido() + " para iniciar no campo de batalha!");
                System.out.println();
                JogadorNPC jogadorNPC = solicitaNPC(jogadoresNPCs);
                Pokemon pokemonAdversario = SolicitaPokemonNPC(jogadorNPC);
                System.out.println("Seu adversário escolheu " + pokemonAdversario.getNome() + " para iniciar no campo de batalha!" );
                System.out.println();
                System.out.println("Nessa rodada, " + proximoAtacante + " começa atacando...");
                System.out.println();

                while(jogador.aptoJogar() && jogadorNPC.aptoJogar()) {
                    if (proximoAtacante.equals("Jogador")) {
                        //para fazer=> escolher a ação: atacar ou reviver.
                        System.out.println("É a sua vez, " + jogador.getNome() + " ! ");
                        System.out.println("O pokemon do adversário é: " + pokemonAdversario.getNome());
                        System.out.println("E possui o seguinte HP: " + pokemonAdversario.getVida());
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
                            // jogador.escolherPokemon(sc);
                            //pokemonJogador = jogador.getPokemonEscolhido();
                            String ataqueRodada = solicitaAtaque(jogador);
                            int valorAtaque = MapaPokemons.buscarValorAtaque(ataqueRodada);
                            pokemonAdversario.aplicarDano(valorAtaque);
                            System.out.println("\n" + jogador.getNomePokemonEscolhido() + " atacando " +
                                    pokemonAdversario.getNome() + "\nCom seu Ataque " + ataqueRodada + " com Poder " + valorAtaque + "\n"); // a titulo de testar funcionamento
                            System.out.println("Vida de " + pokemonAdversario.getNome() + " = " + pokemonAdversario.getVida());
                        }
                    }else {
                        System.out.println("É a vez do seu adversário: " + jogadorNPC.getNome() + " ! ");
                        System.out.println("Seu adversário executou uma ação!");
                        System.out.println();
                        //para fazer=> chamar o ataque do npc, aplicar dano e mostrar a vida restante
                    }
                    //Realiza a troca de quem vai atacar na próxima rodada
                    if (proximoAtacante.equals("Jogador")){
                        proximoAtacante = "NPC";
                    } else {
                        proximoAtacante = "Jogador";
                    }
                }
                rodada++;
                // vezDeAtaque = -1;
            } else if (rodada == 2) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar()) {     //se morreu o primeiro npc, passa o segundo e o terceiro como escolha
                    jogadorNPC = solicitaNPC(jogadoresNPCs[1], jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar()) {    //se morreu o segundo npc, passa o primeiro e o terceiro como escolha
                    jogadorNPC = solicitaNPC(jogadoresNPCs[0], jogadoresNPCs[2]);
                } else {         //se morreu o terceiro npc, passa o segundo e o primeiro como escolha
                    jogadorNPC = solicitaNPC(jogadoresNPCs[0], jogadoresNPCs[1]);
                }
                break;
            } else if (rodada == 3) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar() && jogadoresNPCs[1].aptoJogar()) {   // segue a lógica da segunda rodada, com a diferença que morreram dois
                    jogadorNPC = solicitaNPC(jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar() && jogadoresNPCs[2].aptoJogar()) {
                    jogadorNPC = solicitaNPC(jogadoresNPCs[0]);
                } else {
                    jogadorNPC = solicitaNPC(jogadoresNPCs[1]);
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

    public static void setPokemonsNPC(JogadorNPC numeroNPC) {
        if (numeroNPC.getNome().equalsIgnoreCase("Team Rocket")) {
            Pokemon[] array = new Pokemon[] // pokemons de nível 1
                    {new Pokemon("Charmander"),
                            new Pokemon("Squirtle"),
                            new Pokemon("Bulbasaur")
                    };
            numeroNPC.setArrayPokemon(array);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Aqua")) {
            Pokemon[] array = new Pokemon[] // pokemons de nível 2
                    {new Pokemon("Charmeleon"),
                            new Pokemon("Ivysaur"),
                            new Pokemon("Wartortle")
                    };
            numeroNPC.setArrayPokemon(array);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Galactic")) {
            Pokemon[] array = new Pokemon[] // pokemons de nivel 3
                    {new Pokemon("Charizard"),
                            new Pokemon("Venusaur"),
                            new Pokemon("Blastoise")
                    };
            numeroNPC.setArrayPokemon(array);
        }
    }

    public static JogadorNPC solicitaNPC(JogadorNPC... jogadores) {
        int opcaoNPCJogador;
        int contadorOpcoes = 1;
        String mensagem = "\nEsses são seus vilões:\n";

        for (JogadorNPC Jog : jogadores) {
            mensagem += Jog.getNome();
            mensagem += ' ';
            mensagem += contadorOpcoes++;
            mensagem += "\n";
        }
        mensagem += "Escolha qual NPC você quer enfrentar dentro do total de " + jogadores.length + " NPCs restantes que você pode enfrentar:  ";

        while (true) {
            opcaoNPCJogador = Utilidades.lerIntUsuario(mensagem);
            if (opcaoNPCJogador <= jogadores.length && opcaoNPCJogador > 0) {
                System.out.println("Você enfrentará: " + jogadores[opcaoNPCJogador - 1].getNome());
                return jogadores[opcaoNPCJogador - 1];
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
                    jogador.getNomePokemonEscolhido() + " são:\n" + jogador.getPokemonEscolhido().buscarAtaques() +
                    "Escolha uma opção de ataque (1, 2 ou 3): ");
            if (opcaoAtaqueJogador == 1 || opcaoAtaqueJogador == 2 || opcaoAtaqueJogador == 3) {
                jogador.getPokemonEscolhido().setIndiceAtaqueEscolhido(opcaoAtaqueJogador-1);
                return jogador.getPokemonEscolhido().getAtaqueEscolhido();
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }
    public static int estabeleceVez(){
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(1);
    }
    public static Pokemon SolicitaPokemonNPC (JogadorNPC NPCDaVez){
        Random random = new Random(System.currentTimeMillis());
        int indice = random.nextInt(2);
        while(NPCDaVez.getArrayPokemon()[indice].getVida()<1){
             indice++;
             indice%=3;
         }
        return NPCDaVez.getArrayPokemon()[indice];
    }
    public static int escolherAcao(Jogador jog){
        while (true) {
            int entrada = Utilidades.lerIntUsuario("\nQual ação deseja realizar? (1 ou 2)\n" +
                    "1 - Atacar\n" + ((jog.getNumRevives()>0 && jog.getNumRevives() > 0)?"2 - Reviver":""));
            if (entrada == 1) {
                return entrada;
            }

            if (entrada == 2 && jog.getNumRevives()>0) {
                return entrada;
            }

            System.err.println("Erro: número escolhido inválido.\n");
        }
    }
}
