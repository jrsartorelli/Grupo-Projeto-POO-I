package ada.projeto_final;

import ada.projeto_final.mapas.MapaPokemons;


public class Jogador {
    private final String nome;
    private Integer level;
    private Pokemon[] arrayPokemon;
    private Integer indicePokemonEscolhido;
    private Integer numRevives;
    private Integer pedrasEvolução;

    public Jogador(String nome) {
        this.nome = nome;
        this.level = 1;
        this.arrayPokemon = MapaPokemons.buscarPokemonsRandomicos();
        this.indicePokemonEscolhido = null;
        this.numRevives = 1;
        this.pedrasEvolução = 0;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getNumRevives() {
        return numRevives;
    }

    public void setLevel(Integer level) { this.level = level; }

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
    }

    public Pokemon getPokemon(int indexArrayPokemon){
        return arrayPokemon[indexArrayPokemon];
    }

    public Pokemon getPokemonEscolhido(){
        if (indicePokemonEscolhido != null){
            return arrayPokemon[indicePokemonEscolhido];
        } else{
            System.err.println("Erro! O Pokémon não foi selecionado");
        }
        return null;
    }

    public boolean isPokemonEscolhido(){
        if (indicePokemonEscolhido != null){
            return true;
        }
        return false;
    }

    public String imprimirPokemons(){
        StringBuilder textoPokemons = new StringBuilder();
        int aux = 1;
        for (Pokemon pokemon : arrayPokemon) {
            if (pokemon.estaVivo()) {
                textoPokemons.append(aux + " - " + pokemon);
                aux++;
            }
        }
        return textoPokemons.toString();
    }

    public void setNumRevives(Integer numRevives) { this.numRevives = numRevives; }

   //a função retorna true caso seja possível usar Reviver
    public boolean usarRevive(){
        if(numRevives>0) {
            this.numRevives--;
            return true;
        }else{
            return false;
        }
    }

    public boolean aptoJogar(){
        for(Pokemon pokemon:this.arrayPokemon){
            if(pokemon.estaVivo()){
                return true;
            }
        }
        if(this.numRevives>0){
            return true;
        }
        return false;
    }

    public boolean existePokemonVivo(){
        for(Pokemon pokemon:this.arrayPokemon){
            if(pokemon.estaVivo()){
                return true;
            }
        }
        return false;
    }

    public void escolherPokemon() {

        int opcaoPokemonJogador;
        int[] arrayMapaPokemonsVivos = new int[getArrayPokemon().length];
        int contadorOpcoes = 0;
        String mensagem = "\nEstes são seus Pokémons:\n";

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemonsVivos[contadorOpcoes] = i;
                mensagem += ++contadorOpcoes + " - " + getArrayPokemon()[i] + "\n";
            }
        }

        if (contadorOpcoes == 1){
            indicePokemonEscolhido = arrayMapaPokemonsVivos[0];
        } else {
            String mensagem2 = "(";
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2 += i + " ou ";
                } else if(i == contadorOpcoes){
                    mensagem2 += i + "): ";
                } else{
                    mensagem2 += i + ", ";
                }
            }
            mensagem += "Escolha com qual Pokémon você quer entrar na Batalha " + mensagem2;
        }

        while (contadorOpcoes > 1) {

            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem);
            if (opcaoPokemonJogador <= contadorOpcoes && opcaoPokemonJogador > 0) {
                indicePokemonEscolhido = arrayMapaPokemonsVivos[opcaoPokemonJogador-1];
                System.out.println("\nVocê escolheu: " + getPokemonEscolhido().getNome() +
                        " para iniciar no campo de Batalha!\n");
                System.out.println("Se prepare! Vai começar a Batalha !!!");
                return;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
        System.out.println("\n" + getPokemonEscolhido() + "\nEste é o seu último Pokémon, cuide bem dele !!!\n");
    }

    public boolean querReviverPokemon(){

        // Se todos os Pokémons estiverem mortos -> não perguntamos se deseja utilizar o revive
        // Irá obrigatoriamente utilizar o Revive
        if (!aptoJogar()){
            return true;
        }
        while (true) {
            int opcao = Utilidades.lerIntUsuario("\n" + nome + ", você possui um ou mais Pokémons com possibilidade de Reviver!\n" +
                    "Deseja utilizar essa opção?\n1 - Sim\n2 - Não\nEscolha sua opção (1 ou 2): ");
            if (opcao == 1){
                return true;
            } else if (opcao == 2) {
                return false;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
    }

    public void escolherPokemonParaReviver() {

        int opcaoPokemonJogador;
        int indicePokemonMortoEscolhido = -1;
        int[] arrayMapaPokemonsMortos = new int[getArrayPokemon().length];
        int contadorOpcoes = 0;
        String mensagem = "\nEstes são seus Pokémons Mortos em Batalha:\n";

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (!getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemonsMortos[contadorOpcoes] = i;
                mensagem += ++contadorOpcoes + " - " + getArrayPokemon()[i] + "\n";
            }
        }

        if (contadorOpcoes == 1){
            indicePokemonMortoEscolhido = arrayMapaPokemonsMortos[0];
        } else {
            String mensagem2 = "(";
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2 += i + " ou ";
                } else if(i == contadorOpcoes){
                    mensagem2 += i + "): ";
                } else{
                    mensagem2 += i + ", ";
                }
            }
            mensagem += "Escolha qual Pokémon você quer Reviver " + mensagem2;
        }

        while (contadorOpcoes > 1) {

            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem);
            if (opcaoPokemonJogador <= contadorOpcoes && opcaoPokemonJogador > 0) {
                indicePokemonMortoEscolhido = arrayMapaPokemonsMortos[opcaoPokemonJogador-1];

                System.out.println("\nVocê escolheu: " + arrayPokemon[indicePokemonMortoEscolhido].getNome() +
                        " para reviver!\n");
                break;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
        if (usarRevive()){
            arrayPokemon[indicePokemonMortoEscolhido].revive();
            System.out.println(arrayPokemon[indicePokemonMortoEscolhido].getNome() + " está Vivo !!!");
        } else {
            System.err.println(nome + ", você já utilizou o Revive, portanto não é possível reviver o " +
                    arrayPokemon[indicePokemonMortoEscolhido].getNome());
        }
    }

    public void ganharPedraEvolucao(){
        pedrasEvolução++;
    }
}
