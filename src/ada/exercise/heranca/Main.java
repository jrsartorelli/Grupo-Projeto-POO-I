package ada.exercise.heranca;

import ada.exercise.heranca.formas.Circulo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tipoForma;
        tipoForma = lerIntUsuario("Digite a opção com a forma geométrica que você quer calcular:\n" +
                         "1 - Círculo\n2 - Triângulo\n3 - Retângulo\n4 - Quadrado\n" +
                         "Escolha uma opção (1, 2, 3 ou 4): ");

        switch (tipoForma) {
            case 1:
                criarCirculo();
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.err.println("Erro: opção escolhida inválida.");
                break;
        }
    }

    public static void criarCirculo() {
        double raio = lerDoubleUsuario("Digite o raio do círculo: ");
        String cor = lerStringUsuario("Digite a cor do círculo: ");
        Circulo circulo = new Circulo(raio, cor);
        System.out.println(circulo);
        System.out.println("Área: " + circulo.area());
        System.out.println("Diâmetro: " + circulo.getDiametro());
    }

    private static String lerStringUsuario(String mensagem) {
        Scanner input = new Scanner(System.in);
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        input.close();
        return valorRecebido;
    }

    private static int lerIntUsuario(String mensagem) {
        Scanner input = new Scanner(System.in);
        int valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextInt();
        input.close();
        return valorRecebido;
    }

    private static double lerDoubleUsuario(String mensagem) {
        Scanner input = new Scanner(System.in);
        double valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextDouble();
        input.close();
        return valorRecebido;
    }
}