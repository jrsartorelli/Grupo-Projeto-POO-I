package ada.projeto_final;

import ada.projeto_final.mapas.MapaPokemons;

public class Pokemon{
    private String nome;
    private float vida;
    private final String[] ataques;
    private Integer indiceAtaqueEscolhido;
    private int level;
    private final int VIDA_PADRAO = 1000;

    // Cria um pokémon com o nome e os ataques especificados
    public Pokemon(String nome) {
        this.nome = nome;
        this.ataques = MapaPokemons.buscarAtaques(nome);
        this.vida = VIDA_PADRAO; // Valor padrão de vida para todos os pokémons
        this.level = 1;
        this.indiceAtaqueEscolhido = null;
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
                    MapaPokemons.buscarValorAtaque(ataques[i]) + "\n");
        }
        return textoAtaques.toString();
    }

    public boolean evoluirPokemon(){
        try{
            this.nome = MapaPokemons.getEvolucao(this.nome);
            return true;
        }catch (Exception e){
            System.out.println("Este pokemon atingiu a evolução máxima!");
            return false;
        }
    }

    public void setIndiceAtaqueEscolhido(Integer indiceAtaqueEscolhido) {
        this.indiceAtaqueEscolhido = indiceAtaqueEscolhido;
    }

    public String getAtaqueEscolhido(){
        if (indiceAtaqueEscolhido != null){
            return ataques[indiceAtaqueEscolhido];
        } else{
            System.err.println("Erro! O Ataque não foi selecionado");
        }
        return null;
    }

    public String buscarAtaqueRandomico(){
        int indice = Utilidades.random.nextInt(2);
        indiceAtaqueEscolhido = indice;
        return ataques[indice];
    }

    @Override
    public String toString() {
        return "Pokemon " + nome + ": \n" +
                "  - Vida Atual = " + vida + "\n" +
                "  - Ataques = " + buscarAtaquesResumo() + "\n" +
                "  - Nivel Atual = " + level + "\n";
    }

    public String buscarAtaquesResumo(){
        StringBuilder textoAtaques = new StringBuilder();
        textoAtaques.append("[");
        for (int i = 0; i < ataques.length; i++){
            if (i != (ataques.length-1)){
                textoAtaques.append(ataques[i] + " - " +
                        MapaPokemons.buscarValorAtaque(ataques[i]) + ", ");
            } else {
                textoAtaques.append(ataques[i] + " - " +
                        MapaPokemons.buscarValorAtaque(ataques[i]) + "]");
            }
        }
        return textoAtaques.toString();
    }

    public void revive() {
        vida = ((3/4)*VIDA_PADRAO);
    }
}

