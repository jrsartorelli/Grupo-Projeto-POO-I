package ada.projeto_final;

import java.util.Arrays;

public class Pokemon extends Evolucoes{
    private final String nome;
    private float vida;
    private final String[] ataques;
    private int level;

    // Cria um pokémon com o nome e os ataques especificados
    public Pokemon(String nome) {
        this.nome = nome;
        this.ataques = ataques;
        this.vida = 1000; // Valor padrão de vida para todos os pokémons
        this.level = 1;
    }

    public String getNome() {
        return nome;
    }

    public int getLevel() {
        return level;
    }

    public float getVida() {
        return vida;
    }

    public void aumentarLevel(){
        this.level += 1;
    }

    // Retorna se o pokémon ainda está vivo (se a vida ainda é maior que 0)
    public boolean estaVivo() {
        return vida > 0;
    }

    // Aplica o dano especificado ao pokémon
    public void aplicarDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    // Exibe os ataques do pokémon
    public void mostrarAtaques() {
        for (int i = 0; i < ataques.length; i++) {
            System.out.println((i + 1) + " - " + ataques[i]);
        }
    }

    @Override
    public String toString() {
        return "Pokemon " + nome + ": \n" +
                " - Vida Atual = " + vida + "\n" +
                " - Ataques = " + Arrays.toString(ataques) + "\n" +
                " - Nivel Atual = " + level + "\n";
    }
}

