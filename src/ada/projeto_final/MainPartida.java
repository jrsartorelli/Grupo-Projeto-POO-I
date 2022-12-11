package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        String descricao;
        Scanner sc = new Scanner(System.in);
        nomeJogador = lerStringUsuario(sc, "Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        Jogador jogador = new Jogador(nomeJogador);
        MapaAtaques.inicializarValoresAtaque();

        descricao = "";
        JogadorNPC primeiroNPC = new JogadorNPC("Team Rocket", descricao, "Fogo");

        descricao = "Se vestem como piratas, com uma camiseta listrada e um pano azul na cabeça, onde se encontra o emblema da Equipe Aqua.";
        JogadorNPC segundoNPC = new JogadorNPC("Team Aqua", descricao, "Água");

        descricao = "Os membros vestem roupas similares às de um astronauta e, em função disso, algumas pessoas que não conhecem a organização ou seus integrantes os chamam de \"homens espaciais\".";
        JogadorNPC terceiroNPC = new JogadorNPC("Team Galactic", descricao, "Eletrico");

        int rodada = 1; // vai contar em qual rodada está
        while ( (jogador.getArrayPokemon()[0].estaVivo() ||
                jogador.getArrayPokemon()[1].estaVivo() ||
                jogador.getArrayPokemon()[2].estaVivo()) ||
                jogador.getVida() > 0) {
            if (rodada == 1) {
                JogadorNPC jogadorNPC = solicitaNPC(sc, primeiroNPC, segundoNPC, terceiroNPC);
//                Pokemon escolhaJogador = solicitaPokemon(sc, jogador);
//                System.out.println("\n" + escolhaJogador);
//                int escolhaAtaque = solicitaAtaque(sc, escolhaJogador);
//                System.out.println("\nAtaque escolhido de número " + escolhaAtaque);
                break;
            } else if (rodada == 2) {
                break;
            } else if (rodada == 3) {
                break;
            }
        }
        // sc.close();
    }

    private static String lerStringUsuario(Scanner input, String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }

    private static int lerIntUsuario(Scanner input, String mensagem) {
        int valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextInt();
        return valorRecebido;
    }

    public static boolean reviverPokemon(Scanner input,Pokemon pokemonAtual, Jogador J){
        if(J.usarRevive(pokemonAtual)){
            System.out.println("O pokemon " + pokemonAtual.getNome() + " está de volta ao campo de batalha!");
            return true;
        }else{
            System.out.println("Não foi possível reviver o pokemon");
            return false;
        }
    }

    public static void setFrasesDeEfeito(JogadorNPC numeroNPC) {
        Map<String, String> frasesDeEfeito = new HashMap<>();

        if (numeroNPC.getNome().equalsIgnoreCase("Team Rocket")) {
            frasesDeEfeito.put("inicio", "Prepare-se para encrenca"); // Início da luta
            frasesDeEfeito.put("derrota", ""); // quando o NPC perde
            frasesDeEfeito.put("vitoria", "Para estender nosso poder às estrelas!"); // quando o NPC ganha
            frasesDeEfeito.put("perdePokemon", "Não se mete com a gente!"); // quando o NPC perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Equipe Rocket decolando na velocidade da luz!"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Aqua")) {
            frasesDeEfeito.put("inicio", "Renda-se agora ou prepare-se para lutar!"); // Início da luta
            frasesDeEfeito.put("derrota", ""); // quando o NPC perde
            frasesDeEfeito.put("vitoria", "A destruição mundial é nosso trabalho imundo!"); // quando o NPC ganha
            frasesDeEfeito.put("perdePokemon", "GRRRR!"); // quando o NPC perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Encrenca em dobro!"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Galactic")) {
            frasesDeEfeito.put("inicio", "Outra vez é a questão? É o pirralho que temos então!"); // Início da luta
            frasesDeEfeito.put("derrota", ""); // quando o NPC perde
            frasesDeEfeito.put("vitoria", "Levando o caos para toda parte"); // quando o NPC ganha
            frasesDeEfeito.put("perdePokemon", "Estas águas são traiçoeiras!"); // quando o NPC perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Tirando de todas as pessoas a fé"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        }
    }

    public static void setPokemonsNPC(JogadorNPC numeroNPC) {
        if (numeroNPC.getNome().equalsIgnoreCase("Team Rocket")) {
            Pokemon[] array = new Pokemon[] // pokemons de nível 1
                    {       new Pokemon("Charmander"),
                            new Pokemon("Squirtle"),
                            new Pokemon("Bulbasaur")
                    };
            numeroNPC.setListaPokemon(array);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Aqua")) {
            Pokemon[] array = new Pokemon[] // pokemons de nível 2
                    {       new Pokemon("Charmeleon"),
                            new Pokemon("Ivysaur"),
                            new Pokemon("Wartortle")
                    };
            numeroNPC.setListaPokemon(array);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Galactic")) {
            Pokemon[] array = new Pokemon[] // pokemons de nivel 3
                    {       new Pokemon("Charizard"),
                            new Pokemon("Venusaur"),
                            new Pokemon("Blastoise")
                    };
            numeroNPC.setListaPokemon(array);
        }
    }

    public static JogadorNPC solicitaNPC (Scanner sc, JogadorNPC primeiroNPC,
                                          JogadorNPC segundoNPC, JogadorNPC terceiroNPC) {
        int opcaoNPCJogador;
        while (true) {
            opcaoNPCJogador = lerIntUsuario(sc, "\nEsses são seus vilões:\n" +
                    primeiroNPC.toString() + "\n" + segundoNPC.toString() + "\n" + terceiroNPC + "\n" +
                    "Escolha o NPC que deseja enfrentar (1, 2 ou 3): ");
            if (opcaoNPCJogador == 1) {
                return primeiroNPC;
            } else if (opcaoNPCJogador == 2) {
                return segundoNPC;
            } else if (opcaoNPCJogador == 3) {
                return terceiroNPC;
            } else {
            System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

    public static Pokemon solicitaPokemon (Scanner sc, Jogador jogador) {
        int opcaoPokemonJogador;
        while (true) {
            opcaoPokemonJogador = lerIntUsuario(sc, "\nEstes são seus Pokémons:\n" +
                    jogador.imprimirPokemons() + "\n" + jogador.getNome() +
                    ", escolha seu Pokémon para ataque (1, 2 ou 3): ");
            if (opcaoPokemonJogador == 1 || opcaoPokemonJogador == 2 || opcaoPokemonJogador == 3) {
                Pokemon escolhaJogador = jogador.getPokemon(opcaoPokemonJogador-1);
                return escolhaJogador;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

    public static int solicitaAtaque (Scanner sc, Pokemon escolhaJogador) {
        int opcaoAtaqueJogador;
        while(true) {
            opcaoAtaqueJogador = lerIntUsuario(sc, "\nOs ataques disponíveis do " +
                    escolhaJogador.getNome() + " são:\n" + escolhaJogador.buscarAtaques() +
                    "Escolha uma opção de ataque (1, 2 ou 3): ");
            if (opcaoAtaqueJogador == 1 || opcaoAtaqueJogador == 2 || opcaoAtaqueJogador == 3) {
                return opcaoAtaqueJogador;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }
}
