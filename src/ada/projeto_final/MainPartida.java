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
        Pokemon[] pokemonsJogador = jogador.getArrayPokemon();
        opcao = lerIntUsuario(sc, "Escolha o Pokémon para inciar:" +
                "\n1 - " + pokemonsJogador[0].getNome() +
                "\n2 - " + pokemonsJogador[1].getNome() +
                "\n3 - " + pokemonsJogador[2].getNome() + ": ");
        Pokemon escolhaJogador = pokemonsJogador[opcao-1];
        System.out.println("O Pokemon escolhido foi\n" + escolhaJogador);

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
}
