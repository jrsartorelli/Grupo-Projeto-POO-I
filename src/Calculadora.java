public class Calculadora {
    private Double valor1;
    private Double valor2;

    public Calculadora(Double valor1, Double valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    private boolean isValoresDefinidos(){
        if(valor1 != null && valor2 != null){
            return true;
        } else {
            return false;
        }
    }

    public Double somar(){
        if (isValoresDefinidos()){
            return valor1 + valor2;
        } else {
            return null;
        }
    }

    public Double subtrair(){
        if (isValoresDefinidos()){
            return valor1 - valor2;
        } else {
            return null;
        }
    }

    public Double multiplicar(){
        if (isValoresDefinidos()){
            return valor1 * valor2;
        } else {
            return null;
        }
    }

    public Double dividir(){
        if (isValoresDefinidos()){
            Double resultado;
            try {
                resultado = valor1 / valor2;
            }
            catch (ArithmeticException e){
                System.err.println("Não é possível a divisão por zero");
                return null;
            }
            return resultado;
        } else {
            return null;
        }
    }
}
