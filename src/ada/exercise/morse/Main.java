package ada.exercise.morse;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < AlfabetoPortugues.CARACTERES.length ; i++){
            System.out.println(AlfabetoPortugues.CARACTERES[i] + " = " + CodigoMorse.CODIGOSMORSE[i]);
        }
    }
}
