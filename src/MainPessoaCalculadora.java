public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Ricardo", 42, 1.81f);
        System.out.println(pessoa);

        Calculadora calculadora = new Calculadora(50, 0);
        System.out.printf("Soma = %f\n", calculadora.somar());
        System.out.printf("Subtração = %f\n", calculadora.subtrair());
        System.out.printf("Multiplicação = %f\n", calculadora.multiplicar());
        System.out.printf("Divisão = %f\n", calculadora.dividir());
    }
}
