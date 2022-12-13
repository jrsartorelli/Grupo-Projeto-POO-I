package ada.projeto_final;

import java.util.Scanner;

public class Utilidades {

    private static Scanner input = new Scanner(System.in);

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
