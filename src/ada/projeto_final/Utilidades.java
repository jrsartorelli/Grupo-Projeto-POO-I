package ada.projeto_final;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utilidades {

    private static final Scanner input = new Scanner(System.in);
    public static final Random random = new Random();

    public static String lerStringUsuario(String mensagem) {
        String valorRecebido;
        System.out.println(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }

    public static int lerIntUsuario(String mensagem) {
        int valorRecebido;
        System.out.println(mensagem);
        valorRecebido = input.nextInt();
        return valorRecebido;
    }

    public static void imprimirComPausa(String mensagem){
        int tempoMiliSegundos = random.nextInt(0, 10);
        for (int i = 0; i < mensagem.length(); i++) {  // Itera pelos caracteres da string
            System.out.print(mensagem.charAt(i));  // Exibe o caractere atual no console
            try {
                TimeUnit.MILLISECONDS.sleep(tempoMiliSegundos);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void fecharScanner(){
        input.close();
    }
}
