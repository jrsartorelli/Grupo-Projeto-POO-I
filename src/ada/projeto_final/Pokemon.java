package ada.projeto_final;

import java.util.Arrays;

public class Pokemon extends Evolucoes{
    private final String nome;
    private float vida;
    private final String[] ataques;
    private int level;
    private final int VIDA_PADRAO = 1000;

    // Cria um pokémon com o nome e os ataques especificados
    public Pokemon(String nome) {
        this.nome = nome;
        this.ataques = MapaAtaques.buscarAtaques(nome);
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

    // Retorna os ataques do pokémon
    public String buscarAtaques() {
        StringBuilder textoAtaques = new StringBuilder();
        for (int i = 0; i < ataques.length; i++){
            textoAtaques.append((i + 1) + " - " + ataques[i] + " - Poder de ataque: " +
                    MapaAtaques.buscarValorAtaque(ataques[i]) + "\n");
        }
        return textoAtaques.toString();
    }

    @Override
    public String toString() {
        return "Pokemon " + nome + ": \n" +
                " - Vida Atual = " + vida + "\n" +
                " - Ataques = " + Arrays.toString(ataques) + "\n" +
                " - Nivel Atual = " + level + "\n";
    }

    public void revive() {
        vida = ((3/4)*VIDA_PADRAO);
    }
}

