package br.com.gerenciador.colecao.ranking;

import java.util.ArrayList;
import java.util.List;

import br.com.gerenciador.colecao.Item;

/**
 * Classe para comparação e ordenação de itens 
 * de acordo com o número de vezes que ele foi emprestado.
 * */
public class OrdenaPorMaisEmprestados {
	
	/**
	 * Ordena a lista de forma decrescente em relação ao 
	 * número de vezes que o item foi emprestado.
	 * @param lista - A lista a ser ordenada
	 * @return Uma lista de forma ordenada
	 * */
	public List<Item> ordenarPorMaisEmprestados(List<Item> lista) {
		List<Item> ordenada = new ArrayList<>(lista);
		boolean alterou = true;
		
		while (alterou) {
			alterou = false;
			
			for (int i = 0; i < ordenada.size() - 1; i++) {
				
				if (compare(ordenada.get(i), ordenada.get(i+1)) < 0) {
					alterou = true;
					Item temp = ordenada.get(i);
					ordenada.set(i, ordenada.get(i+1));
					ordenada.set(i+1, temp);
				}
			}
		}
		return ordenada;
	}
	
	/**
	 * Compara um item com outro de acordo com o numero de vezes que foi emprestado
	 * @return -1, 0 , 1 - para A menor do que B, para A igual a 
	 * B ou para A maior que B, respectivamente.
	 * */
	private int compare(Item itemA, Item itemB) {
		
		if (itemA.getVezesEmprestado() < itemB.getVezesEmprestado()) {
			return -1;
		}
		if (itemA.getVezesEmprestado() > itemB.getVezesEmprestado()) {
			return 1;
		}
		return 0;
	}
}
