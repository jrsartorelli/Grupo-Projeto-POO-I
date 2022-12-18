package ada.projeto_final;

import ada.projeto_final.mapas.MapaPokemons;

public class Pokemon{
    private String nome;
    private int vida;
    private String[] ataques;
    private Integer indiceAtaqueEscolhido;
    private int level;
    private final int VIDA_PADRAO = 1000;

    // Cria um pokémon com o nome e os ataques especificados
    public Pokemon(String nome) {
        this.nome = nome;
        this.ataques = MapaPokemons.buscarAtaques(nome);
        this.level = 1;
        this.vida = VIDA_PADRAO + Utilidades.random.nextInt(100, this.level * 300);
        this.indiceAtaqueEscolhido = null;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
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
            textoAtaques.append(i + 1).append(" - ").append(ataques[i]).append("\n");
        }
        return textoAtaques.toString();
    }

    public boolean evoluirPokemon(){
        try{
            this.nome = MapaPokemons.getEvolucao(this.nome);
            this.ataques = MapaPokemons.buscarAtaques(nome);
            aumentarLevel();
            aumentarVida();
            Utilidades.imprimirComPausa("\n" + this + "\n");
            return true;
        } catch (Exception e){
            Utilidades.imprimirComPausa("Este pokemon atingiu a evolução máxima!");
            return false;
        }
    }

    public void aumentarVida(){
        int aumento = Utilidades.random.nextInt(0,this.level * 200);
        this.vida += VIDA_PADRAO + aumento;
        Utilidades.imprimirComPausa("Seu Pokémon agora se chama " + this.nome+ ", seus status atuais são:" );
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

    public String buscarAtaquesResumo(){
        StringBuilder textoAtaques = new StringBuilder();
        textoAtaques.append("[");
        for (int i = 0; i < ataques.length; i++){
            if (i != (ataques.length-1)){
                textoAtaques.append(ataques[i]).append(", ");
            } else {
                textoAtaques.append(ataques[i]).append("]");
            }
        }
        return textoAtaques.toString();
    }

    public void revive() {
        vida = (int) (0.75f * VIDA_PADRAO);
    }

    @Override
    public String toString() {
        return "Pokemon " + nome + ": \n" +
                "  - Vida Atual = " + vida + "\n" +
                "  - Ataques = " + buscarAtaquesResumo() + "\n" +
                "  - Nivel Atual = " + level + "\n";
    }
}

