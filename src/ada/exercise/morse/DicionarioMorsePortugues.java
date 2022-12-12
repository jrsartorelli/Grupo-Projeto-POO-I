package ada.exercise.morse;

import java.util.HashMap;
import java.util.Map;

public class DicionarioMorsePortugues {
    private static final Map<String, Character> DICIONARIO = new HashMap<>() {{
        put(".-", 'a');
        put("-...", 'b');
        put("-.-.", 'c');
        put("-..", 'd');
        put(".", 'e');
        put("..-.", 'f');
        put("--.", 'g');
        put("....", 'h');
        put("..", 'i');
        put(".---", 'j');
        put("-.-", 'k');
        put(".-..", 'l');
        put("--", 'm');
        put("-.", 'n');
        put("---", 'o');
        put(".--.", 'p');
        put("--.-", 'q');
        put(".-.", 'r');
        put("...", 's');
        put("-", 't');
        put("..-", 'u');
        put("...-", 'v');
        put(".--", 'w');
        put("-..-", 'x');
        put("-.--", 'y');
        put("--..", 'z');
        put(".----", '1');
        put("..---", '2');
        put("...--", '3');
        put("....-", '4');
        put(".....", '5');
        put("-....", '6');
        put("--...", '7');
        put("---..", '8');
        put("----.", '9');
        put("-----", '0');
    }};

    public static String conveterMorseParaPortugues(String frase){
        String[] arrayPalavras = frase.split("/");
        StringBuilder saidaFrasePortugues = new StringBuilder();

        for (String palavra : arrayPalavras) {
            saidaFrasePortugues.append(converterPalavraMorse(palavra)).append(" ");
        }
        return saidaFrasePortugues.toString().trim().toUpperCase();
    }

    private static String converterPalavraMorse(String palavra){
        String[] arrayLetras = palavra.split(" ");
        StringBuilder saidaPalavraPortugues = new StringBuilder();

        for (String codigo : arrayLetras) {
            Character letra = DICIONARIO.get(codigo);
            if (letra != null){
                saidaPalavraPortugues.append(letra);
            }
        }
        return saidaPalavraPortugues.toString();
    }
}
