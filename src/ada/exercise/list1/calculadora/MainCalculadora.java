package ada.exercise.list1.calculadora;

import ada.exercise.list1.calculadora.Calculadora;

public class MainCalculadora {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora(50, 0);
        System.out.printf("Soma = %f\n", calculadora.somar());
        System.out.printf("Subtração = %f\n", calculadora.subtrair());
        System.out.printf("Multiplicação = %f\n", calculadora.multiplicar());
        System.out.printf("Divisão = %f\n", calculadora.dividir());
    }
}
