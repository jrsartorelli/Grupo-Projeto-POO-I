package ada.exercise.morse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean parar = false;
        while (!parar) {
            System.out.print("Escolha uma opção: \n1 - Português para Morse\n2 - Morse para Português\nDigite a opção (1 ou 2): ");
            String escolha = sc.next();
            sc.nextLine();

            String frase = "";
            switch (escolha) {
                case "1":
                    System.out.print("Digite a mensagem em Português: ");
                    frase = sc.nextLine().toLowerCase();

                    String saidaMorse = DicionarioPortuguesMorse.conveterPortuguesParaMorse(frase);
                    System.out.println("Sua frase em Morse é: ");
                    System.out.println(saidaMorse);
                    parar = true;
                    break;
                case "2":
                    System.out.print("Digite a mensagem em Morse (palavras separadas por ' / '): ");
                    frase = sc.nextLine();

                    String saidaPortugues = DicionarioMorsePortugues.conveterMorseParaPortugues(frase);
                    System.out.println("Sua frase em Português é: ");
                    System.out.println(saidaPortugues);
                    parar = true;
                    break;
                default:
                    System.err.println("Erro: Opção escolhida inválida.");
                    System.out.println();
                    break;
            }
        }
        sc.close();
    }
}
