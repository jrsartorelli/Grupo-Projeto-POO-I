package ada.projeto_final;

import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        Scanner sc = new Scanner(System.in);
        System.out.print("Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");
        nomeJogador = sc.nextLine();

        sc.close();
    }
}
