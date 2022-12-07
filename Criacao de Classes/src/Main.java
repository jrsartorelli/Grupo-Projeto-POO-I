import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GarrafaTermica garrafa = new GarrafaTermica("Tramontina", "marrom", 1.5);
        String opcao = "";
        Scanner input = new Scanner(System.in);

        while (!opcao.equalsIgnoreCase("sair")){
            opcao = leStringUsuario(input, "\nDigite a ação desejada:" +
                    "\nabrir - Abre a tampa da garrafa" +
                    "\nfechar - Fecha a tampa da garrafa" +
                    "\ncolocar - Adiciona líquido na garrafa" +
                    "\nretirar - Retira líquido da garrafa" +
                    "\nmedir - Mede a temperatura do líquido na garrafa" +
                    "\ncalcular - Calcula a quantidade de líquido na garrafa" +
                    "\nsair - Finaliza o programa: ");

            switch (opcao){
                case "abrir":
                    garrafa.abrirGarrafa();
                    System.out.println("\nA garrafa está aberta");
                    break;
                case "fechar":
                    garrafa.fecharGarrafa();
                    System.out.println("\nA garrafa está fechada");
                    break;
                case "colocar":
                    double quantidadeLiquidoAdicionada = leDoubleUsuario(input, "\nDigite a quantidade de líquido a ser adicionada (em Litros): ");
                    double temperaturaLiquido = leDoubleUsuario(input, "Digite a temperatura do líquido adicionado (em graus Celsius): ");
                    garrafa.colocarLiquido(quantidadeLiquidoAdicionada, temperaturaLiquido);
                    break;
                case "retirar":
                    double quantidadeLiquidoRetirada = leDoubleUsuario(input, "\nDigite a quantidade de líquido a ser retirada (em Litros): ");
                    garrafa.retirarLiquido(quantidadeLiquidoRetirada);
                    break;
                case "medir":
                    if (garrafa.medirTemperatura() == -1){
                        System.out.println("\nA garrafa ainda está vazia!");
                    } else {
                        System.out.printf("\nA temperatura do líquido na garrafa é %.2fºC\n", garrafa.medirTemperatura());
                    }
                    break;
                case "calcular":
                    System.out.printf("\nA garrafa possui %.2f litros em seu interior\n", garrafa.calcularQuantidadeLiquido());
                    break;
                default:
                    break;
            }
        }
        System.out.println("\nFinalizando o programa...");
        input.close();
    }
    private static String leStringUsuario(Scanner input, String mensagem) {
        String valorRecebido;
        System.out.print(mensagem);
        valorRecebido = input.nextLine().toLowerCase();
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
