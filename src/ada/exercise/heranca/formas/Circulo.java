package ada.exercise.heranca.formas;

public class Circulo extends Figura {
    private double raio;

    public Circulo(double raio, String cor) {
        this.raio = raio;
        super.setCor(cor);
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public double area() {
        return (Math.PI * Math.pow(raio, 2));
    }

    public double getDiametro() {
        return (raio * 2);
    }

    @Override
    public String toString() {
        return "Circulo { " +
                "raio = " + raio +
                ", cor = " + super.getCor() +
                '}';
    }
}
