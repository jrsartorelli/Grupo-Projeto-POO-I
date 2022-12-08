package ada.exercise.morse;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a mensagem em Português: ");
        String frase = sc.nextLine().toLowerCase();
        sc.close();

        String[] palavras = frase.split(" ");
        String saidaMorse = "";
        for(int i = 0; i < palavras.length; i++){
            System.out.println(palavras[i]);
            char[] letras = palavras[i].toCharArray();
            for (int j = 0; j < letras.length; j++){
                for (int y = 0; y < AlfabetoPortugues.CARACTERES.length; y++){
                    if (letras[j] == AlfabetoPortugues.CARACTERES[y]){
                        saidaMorse += CodigoMorse.CODIGOSMORSE[y];
                    }
                }
                saidaMorse += " ";
            }
            saidaMorse += "/ ";
        }
        System.out.println(saidaMorse);
    }
}
