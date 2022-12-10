package ada.exercise.heranca;

import ada.exercise.heranca.formas.Circulo;
import ada.exercise.heranca.formas.Quadrado;
import ada.exercise.heranca.formas.Retangulo;
import ada.exercise.heranca.formas.Triangulo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tipoForma = 0;

        while (tipoForma != 5) {
            tipoForma = lerIntUsuario(sc, "\nDigite a opção com a forma geométrica que você quer calcular:\n" +
                    "1 - Círculo\n2 - Triângulo\n3 - Retângulo\n4 - Quadrado\n5 - Sair\n" +
                    "Escolha uma opção (1, 2, 3, 4 ou 5): ");

            switch (tipoForma) {
                case 1:
                    criarCirculo(sc);
                    break;
                case 2:
                    criarTriangulo(sc);
                    break;
                case 3:
                    criarRetangulo(sc);
                    break;
                case 4:
                    criarQuadrado(sc);
                    break;
                case 5:
                    System.out.println("Finalizando o Programa...");
                    break;
                default:
                    System.err.println("Erro: opção escolhida inválida.");
                    break;
            }
        }
        sc.close();
    }

    public static void criarCirculo(Scanner input) {
        double raio = lerDoubleUsuario(input, "Digite o valor do raio do círculo: ");
        String cor = lerStringUsuario(input, "Digite a cor do círculo: ");
        Circulo circulo = new Circulo(raio, cor);
        System.out.println(circulo);
        System.out.println("Área: " + circulo.area());
        System.out.println("Diâmetro: " + circulo.getDiametro());
    }

    public static void criarTriangulo(Scanner input) {
        double base = lerDoubleUsuario(input, "Digite o valor da base do triângulo: ");
        double altura = lerDoubleUsuario(input, "Digite o valor da altura do triângulo: ");
        String cor = lerStringUsuario(input, "Digite a cor do triângulo: ");
        Triangulo triangulo = new Triangulo(base, altura, cor);
        System.out.println(triangulo);
        System.out.println("Área: " + triangulo.area());
    }

    public static void criarRetangulo(Scanner input) {
        double lado1 = lerDoubleUsuario(input, "Digite o valor de um lado do retângulo: ");
        double lado2 = lerDoubleUsuario(input, "Digite o valor do outro lado do retângulo: ");
        String cor = lerStringUsuario(input, "Digite a cor do retângulo: ");
        Retangulo retangulo = new Retangulo(lado1, lado2, cor);
        System.out.println(retangulo);
        System.out.println("Área: " + retangulo.area());
    }

    public static void criarQuadrado(Scanner input) {
        double lado = lerDoubleUsuario(input, "Digite o valor do lado do quadrado: ");
        String cor = lerStringUsuario(input, "Digite a cor do quadrado: ");
        Quadrado quadrado = new Quadrado(lado, cor);
        System.out.println(quadrado);
        System.out.println("Área: " + quadrado.area());
    }

    private static String lerStringUsuario(Scanner input, String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine();
        return valorRecebido;
    }

    private static int lerIntUsuario(Scanner input, String mensagem) {
        int valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextInt();
        return valorRecebido;
    }

    private static double lerDoubleUsuario(Scanner input, String mensagem) {
        double valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextDouble();
        input.nextLine();
        return valorRecebido;
    }
}