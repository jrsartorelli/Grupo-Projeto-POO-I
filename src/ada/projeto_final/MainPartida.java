package ada.projeto_final;

import ada.projeto_final.mapas.MapaNPCs;
import ada.projeto_final.mapas.MapaPokemons;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        int vezDeAtaque = -1; // sinaliza de quem é a vez de atacar ou seja, de quem é o turno. (1 = vez do jogador e 0 = vez do npc)
        boolean usouRevive = false;
        Scanner sc = new Scanner(System.in);
        nomeJogador = Utilidades.lerStringUsuario(sc, "Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        inicializarValoresMapas();
        Jogador jogador = new Jogador(nomeJogador);
        JogadorNPC[] jogadoresNPCs = MapaNPCs.buscarNPCsRandomicos();

        int rodada = 1; // vai contar em qual rodada está
        while (jogador.aptoJogar()) {
            if (rodada == 1) {
                System.out.println("Vai começar a batalha " + rodada + ", escolha seu pokemon para o campo de batalha!!!");
                jogador.escolherPokemon(sc);
                //Pokemon pokemonJogador = jogador.getPokemonEscolhido();
                System.out.println("Você escolheu: " + jogador.getNomePokemonEscolhido() + " para iniciar no campo de batalha!");
                System.out.println();
                JogadorNPC jogadorNPC = solicitaNPC(sc, jogadoresNPCs);
                Pokemon pokemonAdversario = SolicitaPokemonNPC(jogadorNPC);
                System.out.println("Seu adversário escolheu " + pokemonAdversario.getNome() + " para iniciar no campo de batalha!" );
                System.out.println();
                System.out.println("Sorteando quem começa atacando...");
                System.out.println();
                if (vezDeAtaque == -1) {
                    vezDeAtaque = estabeleceVez();
                }
                while(jogador.aptoJogar() && jogadorNPC.aptoJogar()) {
                    if (vezDeAtaque == 1) {
                        //para fazer=> escolher a ação: atacar ou reviver.
                        System.out.println("É a sua vez, " + jogador.getNome() + " ! ");
                        System.out.println("O pokemon do adversário é: " + pokemonAdversario.getNome());
                        System.out.println("E possui o seguinte HP: " + pokemonAdversario.getVida());
                        int acaoEscolhida = escolherAcao(sc, jogador, usouRevive);
                        while (acaoEscolhida == -1) {
                            escolherAcao(sc, jogador, usouRevive);
                        }
                        if (acaoEscolhida == 2) { // revive
                            System.out.println("Revive solicitado!");
                            if (!usouRevive) {
                                System.out.println();
                                boolean aux = reviverPokemon(jogador.getPokemonEscolhido(), jogador);
                                if(aux) {
                                    usouRevive = true;
                                } else {
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
                            int escolhaAtaque = solicitaAtaque(sc, jogador);
                            System.out.println("\nAtaque escolhido de número " + escolhaAtaque);
                            System.out.println("ataque executado"); // a titulo de testar funcionamento
                            System.out.println();
                            //para fazer=> metodo aplicar dano e mostrar vida restante do adversário
                        }
                    }else {
                        System.out.println("É a vez do seu adversário: " + jogadorNPC.getNome() + " ! ");
                        System.out.println("Seu adversário executou uma ação!");
                        System.out.println();
                        //para fazer=> chamar o ataque do npc, aplicar dano e mostrar a vida restante
                    }
                    vezDeAtaque=1-vezDeAtaque;
                }
                rodada++;
                usouRevive = false;
                // vezDeAtaque = -1;
            } else if (rodada == 2) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar()) {     //se morreu o primeiro npc, passa o segundo e o terceiro como escolha
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[1], jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar()) {    //se morreu o segundo npc, passa o primeiro e o terceiro como escolha
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[0], jogadoresNPCs[2]);
                } else {         //se morreu o terceiro npc, passa o segundo e o primeiro como escolha
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[0], jogadoresNPCs[1]);
                }
                break;
            } else if (rodada == 3) {
                JogadorNPC jogadorNPC;
                if (jogadoresNPCs[0].aptoJogar() && jogadoresNPCs[1].aptoJogar()) {   // segue a lógica da segunda rodada, com a diferença que morreram dois
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[2]);
                } else if (jogadoresNPCs[1].aptoJogar() && jogadoresNPCs[2].aptoJogar()) {
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[0]);
                } else {
                    jogadorNPC = solicitaNPC(sc, jogadoresNPCs[1]);
                }
                break;
            }
        }
        sc.close();
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

    public static void setFrasesDeEfeito(JogadorNPC[] jogadoresNPCs) {
        Map<String, String> frasesDeEfeito = new HashMap<>();
        frasesDeEfeito.put("inicio", "Prepare-se para encrenca e possivelmente em dobro!"); // Início da luta
        frasesDeEfeito.put("derrota", "Droga! Eu ainda te pego!"); // quando o NPC perde
        frasesDeEfeito.put("vitoria", "Para estender nosso poder às estrelas!"); // quando o NPC ganha
        frasesDeEfeito.put("perdePokemon", "Não se mete com a gente!"); // quando o NPC perde um pokemon
        frasesDeEfeito.put("mataPokemon", "Equipe decolando na velocidade da luz!"); // quando mata o pokemon do jogador
        jogadoresNPCs[0].setFrasesDeEfeito(frasesDeEfeito);

        frasesDeEfeito = new HashMap<>();
        frasesDeEfeito.put("inicio", "Renda-se agora ou prepare-se para lutar!"); // Início da luta
        frasesDeEfeito.put("derrota", "Bem que disseram que você não é iniciante."); // quando o NPC perde
        frasesDeEfeito.put("vitoria", "A destruição mundial é nosso trabalho imundo!"); // quando o NPC ganha
        frasesDeEfeito.put("perdePokemon", "GRRRR!"); // quando o NPC perde um pokemon
        frasesDeEfeito.put("mataPokemon", "Encrenca em dobro!"); // quando mata o pokemon do jogador
        jogadoresNPCs[1].setFrasesDeEfeito(frasesDeEfeito);

        frasesDeEfeito = new HashMap<>();
        frasesDeEfeito.put("inicio", "Outra vez é a questão? É o pirralho que temos então!"); // Início da luta
        frasesDeEfeito.put("derrota", "Não tem jeito! Vou ter que ficar mais forte para a próxima!"); // quando o NPC perde
        frasesDeEfeito.put("vitoria", "Levando o caos para toda parte"); // quando o NPC ganha
        frasesDeEfeito.put("perdePokemon", "Estas águas são traiçoeiras!"); // quando o NPC perde um pokemon
        frasesDeEfeito.put("mataPokemon", "Tirando de todas as pessoas a fé"); // quando mata o pokemon do jogador
        jogadoresNPCs[2].setFrasesDeEfeito(frasesDeEfeito);
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

    public static JogadorNPC solicitaNPC(Scanner sc, JogadorNPC... jogadores) {
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
            opcaoNPCJogador = Utilidades.lerIntUsuario(sc, mensagem);
            if (opcaoNPCJogador <= jogadores.length && opcaoNPCJogador > 0) {
                System.out.println("Você enfrentará: " + jogadores[opcaoNPCJogador - 1].getNome());
                return jogadores[opcaoNPCJogador - 1];
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

    //lembrar de não deixar solicitar ataque com pokemons mortos e com revive disponivel
    public static int solicitaAtaque(Scanner sc, Jogador jogador) {
        int opcaoAtaqueJogador;
        while (true) {
            opcaoAtaqueJogador = Utilidades.lerIntUsuario(sc, "\nOs ataques disponíveis do " +
                    jogador.getNomePokemonEscolhido() + " são:\n" + jogador.getPokemonEscolhido().buscarAtaques() +
                    "Escolha uma opção de ataque (1, 2 ou 3): ");
            if (opcaoAtaqueJogador == 1 || opcaoAtaqueJogador == 2 || opcaoAtaqueJogador == 3) {
                return opcaoAtaqueJogador;
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
    public static int escolherAcao(Scanner sc, Jogador jog, boolean usouRevive){
        while (true) {
            System.out.println("\nQual ação deseja realizar? (1 ou 2)");
            System.out.println("1 - Atacar");
            System.out.println((jog.getNumRevives()>0 && !usouRevive)?"2 - Reviver":"");
            int entrada = sc.nextInt();

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
