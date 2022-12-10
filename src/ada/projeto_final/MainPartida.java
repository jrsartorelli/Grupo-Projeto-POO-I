package ada.projeto_final;

import java.util.Scanner;

public class MainPartida {
    public static void main(String[] args) {
        String nomeJogador;
        Scanner sc = new Scanner(System.in);
        nomeJogador = lerStringUsuario(sc, "Bem vindo ao jogo PokeRPG!\n" +
                "Para iniciarmos digite o seu nome: ");

        sc.close();
    }

    private static String lerStringUsuario(Scanner input, String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }
}
