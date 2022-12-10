package ada.exercise.heranca;

import ada.exercise.heranca.formas.Circulo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a opção com a forma geométrica que você quer calcular:\n" +
                         "1 - Círculo\n2 - Triângulo\n3 - Retângulo\n4 - Quadrado\n" +
                         "Escolha uma opção (1, 2, 3 ou 4): ");
        int forma = sc.nextInt();
        sc.nextLine();

        switch (forma) {
            case 1:
                criarCirculo(sc);
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
        sc.close();
    }

    public static void criarCirculo(Scanner sc) {
        double raio = leDoubleUsuario(sc, "Digite o raio do círculo: ");
        String cor = leStringUsuario(sc, "Digite a cor do círculo: ");
        Circulo circulo = new Circulo(raio, cor);
        System.out.println(circulo);
        System.out.println("Área: " + circulo.area());
        System.out.println("Diâmetro: " + circulo.getDiametro());
    }

    private static String leStringUsuario(Scanner input, String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }

    private static double leDoubleUsuario(Scanner input, String mensagem) {
        double valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextDouble();
        input.nextLine();
        return valorRecebido;
    }
}