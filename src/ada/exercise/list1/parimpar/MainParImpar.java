package ada.exercise.list1.parimpar;

import java.util.Scanner;

public class MainParImpar {
    public static void main(String[] args) {
        int numero;
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número que deseja classificar como Par ou Ímpar: ");
        numero = sc.nextInt();


        if (NumeroParImpar.isPar(numero)){
            System.out.printf("O número %d é Par!", numero);
        } else {
            System.out.printf("O número %d é Ímpar!", numero);
        }
    }
}
