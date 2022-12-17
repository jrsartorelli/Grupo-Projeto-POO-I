package ada.projeto_final;

import ada.projeto_final.mapas.MapaPokemons;


public class Jogador {
    private final String nome;
    private final Pokemon[] arrayPokemon;
    private Integer indicePokemonEscolhido;
    private Integer numRevives;
    private Integer pedrasEvolucao;

    public Jogador(String nome) {
        this.nome = nome;
        this.arrayPokemon = MapaPokemons.buscarPokemonsRandomicos();
        this.indicePokemonEscolhido = null;
        this.numRevives = 1;
        this.pedrasEvolucao = 0;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumRevives() {
        return numRevives;
    }

    public Pokemon[] getArrayPokemon() {
        return arrayPokemon;
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
        return indicePokemonEscolhido != null;
    }

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
        return this.numRevives > 0;
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
        StringBuilder mensagem = new StringBuilder("\nEstes são seus Pokémons:\n");

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemonsVivos[contadorOpcoes] = i;
                mensagem.append(++contadorOpcoes).append(" - ").append(getArrayPokemon()[i]).append("\n");
            }
        }

        if (contadorOpcoes == 1){
            indicePokemonEscolhido = arrayMapaPokemonsVivos[0];
        } else {
            StringBuilder mensagem2 = new StringBuilder("(");
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2.append(i).append(" ou ");
                } else if(i == contadorOpcoes){
                    mensagem2.append(i).append("): ");
                } else{
                    mensagem2.append(i).append(", ");
                }
            }
            mensagem.append("Escolha com qual Pokémon você quer entrar na Batalha ").append(mensagem2);
        }

        while (contadorOpcoes > 1) {

            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem.toString());
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

    public boolean escolherPokemonParaEvoluir() {

        if (pedrasEvolucao <= 0){
            return false;
        }

        int opcaoPokemonJogador;
        int[] arrayMapaPokemonsVivos = new int[getArrayPokemon().length];
        int contadorOpcoes = 0;
        StringBuilder mensagem = new StringBuilder("\nEstes são seus Pokémons:\n");

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemonsVivos[contadorOpcoes] = i;
                mensagem.append(++contadorOpcoes).append(" - ").append(getArrayPokemon()[i]).append("\n");
            }
        }

        if (contadorOpcoes == 1){
            indicePokemonEscolhido = arrayMapaPokemonsVivos[0];
        } else {
            StringBuilder mensagem2 = new StringBuilder("(");
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2.append(i).append(" ou ");
                } else if(i == contadorOpcoes){
                    mensagem2.append(i).append("): ");
                } else{
                    mensagem2.append(i).append(", ");
                }
            }
            mensagem.append("Escolha qual Pokémon você quer Evoluir ").append(mensagem2);
        }

        while (contadorOpcoes > 1) {

            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem.toString());
            if (opcaoPokemonJogador <= contadorOpcoes && opcaoPokemonJogador > 0) {
                indicePokemonEscolhido = arrayMapaPokemonsVivos[opcaoPokemonJogador-1];
                System.out.println("\nVocê escolheu: " + getPokemonEscolhido().getNome() +
                        " para iniciar o Processo da Evolução !\n");
                break;
            } else {
                System.err.println("Erro: número escolhido inválido.\n");
            }
        }
        System.out.println("Se prepare para ver seu Pokémon Evoluído !!!\n");
        return arrayPokemon[indicePokemonEscolhido].evoluirPokemon();
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
        StringBuilder mensagem = new StringBuilder("\nEstes são seus Pokémons Mortos em Batalha:\n");

        for (int i = 0; i < getArrayPokemon().length; i++){
            if (!getArrayPokemon()[i].estaVivo()){
                arrayMapaPokemonsMortos[contadorOpcoes] = i;
                mensagem.append(++contadorOpcoes).append(" - ").append(getArrayPokemon()[i]).append("\n");
            }
        }

        if (contadorOpcoes == 1){
            indicePokemonMortoEscolhido = arrayMapaPokemonsMortos[0];
        } else {
            StringBuilder mensagem2 = new StringBuilder("(");
            for (int i = 1; i <= contadorOpcoes; i++){
                if(i == (contadorOpcoes - 1)){
                    mensagem2.append(i).append(" ou ");
                } else if(i == contadorOpcoes){
                    mensagem2.append(i).append("): ");
                } else{
                    mensagem2.append(i).append(", ");
                }
            }
            mensagem.append("Escolha qual Pokémon você quer Reviver ").append(mensagem2);
        }

        while (contadorOpcoes > 1) {

            opcaoPokemonJogador = Utilidades.lerIntUsuario(mensagem.toString());
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
        pedrasEvolucao++;
    }
}
