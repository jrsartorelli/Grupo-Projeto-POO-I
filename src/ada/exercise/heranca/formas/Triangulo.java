package ada.exercise.heranca.formas;

public class Triangulo extends Figura {
    private double base;
    private double altura;

    public Triangulo(double base, double altura, String cor) {
        this.base = base;
        this.altura = altura;
        super.setCor(cor);
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double area() {
        return ((this.base * this.altura)/2);
    }

    @Override
    public String toString() {
        return "Triangulo {" +
                "base = " + base +
                ", altura = " + altura +
                ", cor = " + super.getCor() +
                '}';
    }
}
