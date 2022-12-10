package ada.projeto_final;

import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        int opcao;
        Scanner sc = new Scanner(System.in);
        nomeJogador = lerStringUsuario(sc, "Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        Jogador jogador = new Jogador(nomeJogador);
        opcao = lerIntUsuario(sc, "\nEstes são seus Pokémons:\n" +
                jogador.imprimirPokemons() + "\n" + nomeJogador +
                ", escolha seu Pokémon para ataque (1, 2 ou 3): ");
        Pokemon escolhaJogador = jogador.getPokemon(opcao-1);
        System.out.println("\nO Pokemon escolhido foi\n" + escolhaJogador);

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

    public static boolean Revive(Scanner input,Pokemon pokemonAtual, Jogador J){
        if(J.usarRevive(pokemonAtual)){
            System.out.println("O pokemon " + pokemonAtual.getNome() + " está de volta ao campo de batalha!");
            return true;
        }else{
            System.out.println("Não foi possível reviver o pokemon");
            return false;
        }

    }

}