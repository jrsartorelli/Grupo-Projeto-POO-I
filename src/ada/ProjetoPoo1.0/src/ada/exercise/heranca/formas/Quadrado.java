package ada.exercise.heranca.formas;

public class Quadrado extends Retangulo {

    public Quadrado(double lado, String cor) {
        super(lado, lado, cor);
    }

    @Override
    public String toString() {
        return "Quadrado { " +
                "lado = " + this.getLado1() +
                "cor = " + super.getCor() +
                " }";
    }
}
