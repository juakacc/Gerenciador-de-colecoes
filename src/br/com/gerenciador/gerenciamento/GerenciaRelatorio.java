package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;

import java.util.ArrayList;
import java.util.List;

import br.com.gerenciador.colecao.*;
import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.colecao.itemColecionavel.dlc.Dlc;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com a pesquisa avançada de itens.
 * Utilizando filtros especificos de cada tipo de item, dependendo da 
 * escolha do usuário.
 * */
public class GerenciaRelatorio implements GerenciamentoIF {

    private Acervo acervo;

    /**
	 * Constrói o gerenciador e 
	 * inicializa o acervo de itens do usuario
	 * */
    public GerenciaRelatorio() {
        this.acervo = Acervo.getInstancia();
    }

    /**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
    public void selecionar(int opcao) {
        
        switch(opcao) {
        case 1:       	// Relatório completo dos itens
	        acervo.verPorCategoria();
	        break;

        case 2:			// Relatório por tipo de filtro
	        menuTipoItem();
	        opcao = recuperarInt("O que deseja fazer(0,1,2,3,4)? ");
	        
	        if (opcao == 0) {
	        	break;
	        } else if (opcao >= 1 && opcao <= 4) {
	        	filtragem(opcao);
	        } else {
	        	System.out.println(ENTRADA_INVALIDA);
	        }
        }
    }
    
    /**
     * Método para gerenciar a filtragem; recolhe os filtros escolhidos
     * e filtra, depois exibe os resultados na tela para o usuário.
     * @param tipo - tipo do item que será filtrado.
     * */
    private void filtragem(int tipo) {
    	List<Integer> filtros;
    	List<Item> resultados = null;
    	
    	switch(tipo) {
        case Item.DVDCD:            
            dvdCdFiltros();
            filtros = recolherFiltros(5);
            
            resultados = filtrarDvdCd(filtros);
            break;

        case Item.HQ:
            hqFiltros();
            filtros = recolherFiltros(7);

            resultados = filtrarHq(filtros);
            break;

        case Item.TABULEIRO:           
            jogoDeTabuleiroFiltros();
            filtros = recolherFiltros(3);
            
            resultados = filtrarJogosDeTabuleiro(filtros);
            break;

        case Item.GAME:
            jogoDeVideoGameFiltros();
            filtros = recolherFiltros(5);

            resultados = filtrarJogosDeVideoGame(filtros);
            break;
        }
    	
    	if (resultados == null) {
    		System.out.println("\nNenhum item foi encontrado com essas especificações...");
    	} else {
    		System.out.println("\n----------------------------");
    		System.out.println("|::::::: RESULTADOS :::::::|");
    		System.out.println("----------------------------\n");
    		for (Item item : resultados) {
    			System.out.println(item.toString() + "\n");
    		}
    		System.out.println("----------------------------");
    	}
    }
    
    /**
     * Método para recolhimento dos filtros que o usuário vai usar
     * na pesquisa.
     * @param maximo - número máximo de filtros que podem ser utilizados.
     * É determinado pelo tipo do item que ele está pesquisando.
     * @return A List contendo os filtros escolhidos.
     * */
    private List<Integer> recolherFiltros(int maximo) {
    	List<Integer> filtros = new ArrayList<>();
    	int escolha;
    	
    	do {
            escolha = recuperarInt("Qual(is) o(s) filtro(s) deseja utilizar (0-sair)? ");

            if(escolha == 0) {
            	return filtros;
            } else if(escolha >= 1 && escolha <= maximo) {
                filtros.add(escolha);
            } else {
                System.out.println(ENTRADA_INVALIDA);
            }
        } while (true);
    }

    /**
	 * Método para filtrar os itens em relação aos atributos que eles possuem em comum
	 * @param filtros - filtros que vão ser utilizados.
	 * @param itens - itens a serem filtrados.
	 * @return A List contendo os itens restantes ou NULL caso nenhum item atendeu os requesitos.
	 * */
	private List<Item> filtrarEmComum(List<Integer> filtros, List<Item> itens) {
		List<Item> aux = new ArrayList<>();
		
		if (filtros.contains(1)) {    // titulo
	        String titulo = recuperarString("Qual o título? ");
	       
	        for(Item item : itens) {
	            if(titulo.equalsIgnoreCase(item.getTitulo())) {
	                aux.add(item);
	            }
	        }
	        if (aux.isEmpty()) {
	            return null;
	        } else {
	            itens = new ArrayList<>(aux);
	            aux.clear();
	        }
	    }
	    
	    if (filtros.contains(2)) {    // estado
	        String estado = recuperarString("Qual o estado? ");
	        
	        for(Item item : itens) {
	            if(estado.equalsIgnoreCase(item.getEstado())) {
	                aux.add(item);
	            }
	        }
	        if (aux.isEmpty()) {
	            return null;
	        } else {
	            itens = new ArrayList<>(aux);
	            aux.clear();
	        }
	    }
	    
	    if (filtros.contains(3)) {  //preço
	        double preco = recuperarDouble("Qual o preço(Ex: 9.99)? ");
	        
	        for(Item item : itens){
	            if(preco == item.getPrecoDeCompra()) {
	                aux.add(item);
	            }
	        }
	        if (aux.isEmpty()) {
	            return null;
	        } else {
	            itens = new ArrayList<>(aux);
	            aux.clear();
	        }
	    }
	    
	    return itens;
	}
	
	/**
     * Método para filtragem de DVD's/CD's.
     * @param filtros - filtros que serão utilizados.
     * @return Uma lista contendo os itens filtrados ou 
     * NULL caso nenhum seja encontrado.
     * */
    private List<Item> filtrarDvdCd(List<Integer> filtros) {
        List<Item> itens = acervo.dvdCdList();
        List<Item> aux = new ArrayList<>();

        itens = filtrarEmComum(filtros, itens);
        if (itens == null) {
        	return null;
        }
        
        if (filtros.contains(4)) {  		// marca
            String marca = recuperarString("Qual a marca? ");
            
            for(Item item : itens) {
                DvdCd dvdAux = (DvdCd) item;
                if(marca.equalsIgnoreCase(dvdAux.getMarca())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
            	return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        
        if (filtros.contains(5)) {  // conteúdo
            String conteudo = recuperarString("Qual o conteúdo? ");
            
            for(Item item : itens) {
                DvdCd dvdAux = (DvdCd) item;
                if(conteudo.equalsIgnoreCase(dvdAux.getConteudo())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        return itens;
    }

    /**
     * Método para filtragem de HQ's.
     * @param filtros - filtros que serão utilizados.
     * @return Uma lista contendo os itens filtrados ou 
     * NULL caso nenhum seja encontrado.
     * */
    private List<Item> filtrarHq(List<Integer> filtros) {

        List<Item> itens = acervo.hqList();
        List<Item> aux = new ArrayList<>();

        itens = filtrarEmComum(filtros, itens);
        if (itens == null) {
        	return null;
        }
        
        if (filtros.contains(4)) { 		 // número
            int numero = recuperarInt("Qual o número? ");
           
            for(Item item : itens) {
                Hq hqAux = (Hq) item;
                if(numero == hqAux.getNumero()) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        
        if (filtros.contains(5)) {  // editora
            String editora = recuperarString("Qual a editora? ");
            for(Item item : itens) {
            	Hq hqAux = (Hq) item;
                if(editora.equalsIgnoreCase(hqAux.getEditora())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        
        if (filtros.contains(6)) {  //universo
            String universo = recuperarString("Qual o universo? ");
           
            for(Item item : itens) {
            	Hq hqAux = (Hq) item;
                if(universo.equalsIgnoreCase(hqAux.getEditora())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        
        if (filtros.contains(7)) {  // nome da saga
            String nomeDaSaga = recuperarString("Qual o nome da saga? ");
           
            for(Item item : itens) {
            	Hq hqAux = (Hq) item;
                if(nomeDaSaga.equalsIgnoreCase(hqAux.getSaga().getNome())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }
        
        return itens;
    }

    /**
     * Método para filtragem de Jogos de tabuleiro
     * @param filtros - filtros que serão utilizados.
     * @return Uma lista contendo os itens filtrados ou 
     * NULL caso nenhum seja encontrado.
     * */
    private List<Item> filtrarJogosDeTabuleiro (List<Integer> filtros) {
        List<Item> itens = acervo.jogoDeTabuleiroList(); 
        
        return filtrarEmComum(filtros, itens);
    }
    
    /**
     * Método para filtragem de Jogos de video game.
     * @param filtros - filtros que serão utilizados.
     * @return Uma lista contendo os itens filtrados ou 
     * NULL caso nenhum seja encontrado.
     * */
    private List<Item> filtrarJogosDeVideoGame(List<Integer> filtros) {
        List<Item> itens = acervo.jogoDeVideoGameList();
        List<Item> aux = new ArrayList<>();

        itens = filtrarEmComum(filtros, itens);
        if (itens == null) {
        	return null;
        }
        
        if (filtros.contains(4)) { 		 // console
            String console = recuperarString("Qual o console? ");
            
            for(Item item : itens) {
                JogoDeVideoGame jogoAux = (JogoDeVideoGame) item;
                if(console.equalsIgnoreCase(jogoAux.getConsole())) {
                    aux.add(item);
                }
            }
            if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }

        if (filtros.contains(5)) {		// titulo da dlc
        	String titulo = recuperarString("Qual o título da DLC? ");
        	
        	for (Item item : itens) {
        		 JogoDeVideoGame jogoAux = (JogoDeVideoGame) item;
        		 
        		 for (Dlc dlc : jogoAux.getDlcs()) {
        			 if (titulo.equalsIgnoreCase(dlc.getTitulo())) {
        				 aux.add(item);
        			 }
        		 }
        	}
        	if (aux.isEmpty()) {
                return null;
            } else {
                itens = new ArrayList<>(aux);
                aux.clear();
            }
        }

        return itens;
    }
 
}