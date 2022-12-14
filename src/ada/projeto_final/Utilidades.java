package ada.projeto_final;

import java.util.Random;
import java.util.Scanner;

public class Utilidades {

    private static final Scanner input = new Scanner(System.in);
    public static final Random random = new Random();

    public static String lerStringUsuario(String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }

    public static int lerIntUsuario(String mensagem) {
        int valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextInt();
        return valorRecebido;
    }

    public static void fecharScanner(){
        input.close();
    }
}
