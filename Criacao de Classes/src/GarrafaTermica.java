public class GarrafaTermica {
    private String marca;
    private String cor;
    private double capacidadeTotal; //Capacidade em Litros
    private double capacidadeUtilizada = 0; //Capacidade em Litros
    final private double TEMPERATURA_AMBIENTE = 25;
    private double temperaturaInterna = TEMPERATURA_AMBIENTE; // Temperatura em graus Celsius
    private boolean estaAberta = false;

    public GarrafaTermica(String marca, String cor, double capacidadeTotal) {
        this.marca = marca;
        this.cor = cor;
        this.capacidadeTotal = capacidadeTotal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(double capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public boolean colocarLiquido(double litros, double temperaturaLitros){
        if (!estaAberta){
            System.out.println("\nA garrafa está com a tampa fechada!");
            return false;
        } else if (this.capacidadeTotal >= (this.capacidadeUtilizada + litros)){
            this.temperaturaInterna = ((this.temperaturaInterna * this.capacidadeUtilizada) + (litros * temperaturaLitros))
                    / (this.capacidadeUtilizada + litros);
            this.capacidadeUtilizada += litros;
            return true;
        } else {
            System.out.printf("\nA garrafa não comporta a adição de %.2f litros!\n", litros);
            return false;
        }
    }

    public boolean retirarLiquido(double litros){
        if (!estaAberta){
            System.out.println("\nA garrafa está com a tampa fechada!");
            return false;
        } else if (this.capacidadeUtilizada >= litros){
            this.capacidadeUtilizada -= litros;
            if (this.capacidadeUtilizada == 0){
                this.temperaturaInterna = TEMPERATURA_AMBIENTE;
            }
            return true;
        } else {
            System.out.printf("\nA garrafa possui apenas %.2f litros, portanto não é possível retirar %.2f litros!\n", this.capacidadeUtilizada, litros);
            return false;
        }
    }

    public void abrirGarrafa(){
        this.estaAberta = true;
    }

    public void fecharGarrafa(){
        this.estaAberta = false;
    }

    public double medirTemperatura(){
        if (this.capacidadeUtilizada > 0){
            return this.temperaturaInterna;
        } else{
            return -1;
        }
    }

    public double calcularQuantidadeLiquido(){
        return this.capacidadeUtilizada;
    }

    @Override
    public String toString() {
        return "GarrafaTermica{" +
                "marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", capacidadeTotal=" + String.format("%.2f", capacidadeTotal) +
                "litros, capacidadeUtilizada=" + String.format("%.2f", capacidadeUtilizada) +
                "litros, temperaturaInterna=" + String.format("%.2f", temperaturaInterna) +
                "ºC, Tampa=" + (estaAberta ? "Aberta" : "Fechada") +
                '}';
    }
}