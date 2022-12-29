package ada.projeto_final;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;

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
        int tempoMiliSegundos = random.nextInt(30, 90);
        for (int i = 0; i < mensagem.length(); i++) {  // Itera pelos caracteres da string
            System.out.print(mensagem.charAt(i));  // Exibe o caractere atual no console
            try {
                TimeUnit.MILLISECONDS.sleep(tempoMiliSegundos);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void escreverLog(String log){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        try {
            FileWriter writer = new FileWriter("log.txt", true); // true para adicionar o conteúdo ao final do arquivo em vez de sobrescrever o arquivo existente
            writer.write(formatter.format(date) + ": " + log + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fecharScanner(){
        input.close();
    }
}
