package ada.exercise.morse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a mensagem em Português: ");
        String frase = sc.nextLine().toLowerCase();
        sc.close();

        String saidaMorse = DicionarioPortuguesMorse.conveterPortuguesParaMorse(frase);
        System.out.println(saidaMorse);
    }
}
