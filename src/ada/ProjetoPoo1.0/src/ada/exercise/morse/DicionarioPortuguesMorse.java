package ada.exercise.morse;

import java.util.HashMap;
import java.util.Map;

public class DicionarioPortuguesMorse {
    private static final Map<Character, String> DICIONARIO = new HashMap<>() {{
            put('a', ".-");
            put('b', "-...");
            put('c', "-.-.");
            put('d', "-..");
            put('e', ".");
            put('f', "..-.");
            put('g', "--.");
            put('h', "....");
            put('i', "..");
            put('j', ".---");
            put('k', "-.-");
            put('l', ".-..");
            put('m', "--");
            put('n', "-.");
            put('o', "---");
            put('p', ".--.");
            put('q', "--.-");
            put('r', ".-.");
            put('s', "...");
            put('t', "-");
            put('u', "..-");
            put('v', "...-");
            put('w', ".--");
            put('x', "-..-");
            put('y', "-.--");
            put('z', "--..");
            put('1', ".----");
            put('2', "..---");
            put('3', "...--");
            put('4', "....-");
            put('5', ".....");
            put('6', "-....");
            put('7', "--...");
            put('8', "---..");
            put('9', "----.");
            put('0', "-----");
    }};

    public static String conveterPortuguesParaMorse(String frase){
        String[] arrayPalavras = frase.split(" ");
        StringBuilder saidaFraseMorse = new StringBuilder();

        for(int i = 0; i < arrayPalavras.length; i++){
            saidaFraseMorse.append(converterPalavra(arrayPalavras[i]));
            if (i != (arrayPalavras.length - 1)){
                saidaFraseMorse.append(" / ");
            }
        }
        return saidaFraseMorse.toString();
    }

    private static String converterPalavra(String palavra){
        char[] arrayLetras = palavra.toCharArray();
        StringBuilder saidaPalavraMorse = new StringBuilder();

        for (char letra : arrayLetras) {
            String codigo = DICIONARIO.get(letra);
            if (codigo != null){
                saidaPalavraMorse.append(codigo).append(" ");
            }
        }
        return saidaPalavraMorse.toString().trim();
    }
}
