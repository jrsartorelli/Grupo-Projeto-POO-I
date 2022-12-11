package ada.projeto_final;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        int opcaoPokemonJogador;
        int opcaoAtaqueJogador;
        Scanner sc = new Scanner(System.in);
        nomeJogador = lerStringUsuario(sc, "Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        Jogador jogador = new Jogador(nomeJogador);
        MapaAtaques.inicializarValoresAtaque();
        opcaoPokemonJogador = lerIntUsuario(sc, "\nEstes são seus Pokémons:\n" +
                jogador.imprimirPokemons() + "\n" + nomeJogador +
                ", escolha seu Pokémon para ataque (1, 2 ou 3): ");
        Pokemon escolhaJogador = jogador.getPokemon(opcaoPokemonJogador-1);
        System.out.println("\n" + escolhaJogador);
        opcaoAtaqueJogador = lerIntUsuario(sc, "Os ataques disponíveis do " +
                escolhaJogador.getNome() + " são:\n" + escolhaJogador.buscarAtaques() +
                "Escolha uma opção de ataque (1, 2 ou 3):");

        sc.close();
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
            frasesDeEfeito.put("perdePokemon", "Não se mete com a gente!"); // quando perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Equipe Rocket decolando na velocidade da luz!"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Aqua")) {
            frasesDeEfeito.put("inicio", "Renda-se agora ou prepare-se para lutar!"); // Início da luta
            frasesDeEfeito.put("derrota", ""); // quando o NPC perde
            frasesDeEfeito.put("vitoria", "A destruição mundial é nosso trabalho imundo!"); // quando o NPC ganha
            frasesDeEfeito.put("perdePokemon", "GRRRR!"); // quando perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Encrenca em dobro!"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        } else if (numeroNPC.getNome().equalsIgnoreCase("Team Galactic")) {
            frasesDeEfeito.put("inicio", "Outra vez é a questão? É o pirralho que temos então!"); // Início da luta
            frasesDeEfeito.put("derrota", ""); // quando o NPC perde
            frasesDeEfeito.put("vitoria", "Levando o caos para toda parte"); // quando o NPC ganha
            frasesDeEfeito.put("perdePokemon", "Estas águas são traiçoeiras!"); // quando perde um pokemon
            frasesDeEfeito.put("mataPokemon", "Tirando de todas as pessoas a fé"); // quando mata o pokemon do jogador
            numeroNPC.setFrasesDeEfeito(frasesDeEfeito);
        }
    }
}
