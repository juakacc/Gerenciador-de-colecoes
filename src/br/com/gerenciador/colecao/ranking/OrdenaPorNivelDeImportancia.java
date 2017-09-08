package br.com.gerenciador.colecao.ranking;

import java.util.ArrayList;
import java.util.List;

import br.com.gerenciador.colecao.Item;

/**
 * Classe para comparação e ordenação dos itens 
 * de acordo com o nivel de importância.
 * */
public class OrdenaPorNivelDeImportancia {

	/**
	 * Ordena a lista de forma decrescente em relação ao 
	 * nível de importância do item para o usuário.
	 * @param lista - A lista a ser ordenada
	 * @return Uma lista de forma ordenada
	 * */
	public List<Item> ordenarPorMaisImportantes(List<Item> lista) {
		
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
	 * Comparar um item com outro de acordo com o nivel de importancia que 
	 * ele tem para o usuario.
	 * @return -1, 0, 1 - para A menor do que B, para A igual a B ou para 
	 * A maior do que B, respectivamente
	 * */
	public int compare(Item itemA, Item itemB) {
		
		if (itemA.getNivelDeImportancia() < itemB.getNivelDeImportancia()) {
			return -1;
		}
		if (itemA.getNivelDeImportancia() > itemB.getNivelDeImportancia()) {
			return 1;
		}
		return 0;
	}
}
