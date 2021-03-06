package br.com.gerenciador.colecao.trilogia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.exceptions.TrilogiaNotFoundException;

/**
 * Armazenamento e gerenciamento da lista de trilogias
 * cadastradas do usuário.
 * 
 * Utiliza Singleton para controlar o número de 
 * instâncias dessa classe no sistema.
 * */
public class RepositorioTrilogia {
	
	/**
	 * Instância do Repositório.
	 * */
	private static RepositorioTrilogia instancia;
	
	/**
	 * Recupera a instância do Repositório, se a mesma ainda não
	 * tiver sido instanciada, chama-se o construtor e instancia
	 * antes de retornar.
	 * @return A instancia do Repositório.
	 * */
	public static RepositorioTrilogia getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioTrilogia();
		}
		return instancia;
	}
	
	/**
	 * Lista de itens do usuário.
	 * */
	private Set<Trilogia> trilogias;
	
	/**
	 * Constrói um Repositório e
	 * inicializa a lista para armazenamento.
	 * */
	private RepositorioTrilogia() {
		trilogias = new HashSet<Trilogia>();
	}

	/**
	 * Adiciona uma trilogia ao pool de trilogias
	 * @param trilogia - trilogia que será adicionada
	 * @return - true caso a trilogia seja adicionada e false caso contrario
	 */	
	public boolean adicionarTrilogia(Trilogia trilogia) {
		if (trilogia == null) {
			return false;
		}
		return trilogias.add(trilogia);
	}
	
	/**
	 * Remove a trilogia caso pertenca ao Acervo de Trilogias
	 * @param nome - titulo da triogia que esta sendo removida
	 * @return true caso a trilogia tenha sido encontrada e removida
	 * @throws TrilogiaNotFoundException trilogia inexistente
	 */
	public boolean removerTrilogia(Trilogia trilogia) {		
		if (trilogias.remove(trilogia)) {
			for (Item item : trilogia.getItens()) {
				item.setSeriado(null);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Remove todas as trilogias que estão cadastradas.
	 * @return True se a operação ocorreu com sucesso ou False caso contrário.
	 * */
	public boolean removerTodas() {
		List<Trilogia> trilogiasAux = getTrilogias();
		
		if (trilogias.removeAll(trilogias)) {
			for (Trilogia trilogia : trilogiasAux) {
				for (Item item : trilogia.getItens()) {
					item.setSeriado(null);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Recupera uma lista  trilogia do Acervo de Trilogias.	 * 
	 * @param nome - titulo da trilogia que esta sendo pesquisada
	 * @return a lista de trilogias caso exista pelo menos uma
	 * @throws TrilogiaNotFoundException - caso não exista nenhuma trilogia que atenda os requisitos
	 */	
	public List<Trilogia> getTrilogia(String nome) throws TrilogiaNotFoundException {
		List<Trilogia> trilogiasAux = new ArrayList<>();		
		
		for (Trilogia trilogia : trilogias) {
			if (nome.equalsIgnoreCase(trilogia.getNome())) {
				trilogiasAux.add(trilogia);
			}
		}
		if (trilogiasAux.isEmpty()) {
			throw new TrilogiaNotFoundException();
		} 
		return trilogiasAux;		
	}
	
	/**
	 * Recupera uma Trilogia em particular
	 * @param nome - nome da trilogia
	 * @param tipo - tipo de item que a trilogia armazena
	 * @return Trilogia correspondente
	 * */
	public Trilogia getTrilogia(String nome, int tipo) {
		Trilogia trilogia = new Trilogia(nome, tipo);
		
		for (Trilogia tri : trilogias) {
			if (trilogia.equals(tri)) {
				return tri;
			}
		}
		return null;
	}
	
	/**
	 * @return O total de trilogias cadastradas pelo usuário.
	 * */
	public int totalTrilogias() {
		return trilogias.size();
	}
	
	/**
	 * Imprime uma trilogia especificada pelo usuário
	 * @param nome - nome da trilogia pesquisada pelo usuário
	 */
	public void exibirTrilogia(String nome) {
		try {
			List<Trilogia> lista = getTrilogia(nome);
			
			System.out.println("\n--------------------------------");
			System.out.println("|::: TRILOGIA EM PARTICULAR :::|");
			System.out.println("--------------------------------");
			for (Trilogia trilogia : lista) {
				System.out.println("\n" + trilogia.toString() + "\n");
			}
			System.out.println("--------------------------------");
		} catch (TrilogiaNotFoundException e) {
			System.out.println("\nNenhuma trilogia cadastrada com esse nome... :(");
		}
	}
	
	/**
	 * Exibe todas as trilogias cadastradas na lista de trilogias do usuário
	 * */
	public void mostrarTrilogias() {
		if (trilogias.isEmpty()) {
			System.out.println("\nNenhuma trilogia cadastrada... :(");
		} else {
			System.out.println("\n-----------------------------");
			System.out.println("|:::::::: TRILOGIAS ::::::::|");
			System.out.println("-----------------------------");
			System.out.println(toString());
			System.out.println("-----------------------------");
		}
	}
	
	/**
	 * Verifica se a lista de trilogias está vazia ou não.
	 * @return True se estiver vazia ou False caso contrário.
	 * */
	public boolean isVazio() {
		return trilogias.isEmpty();
	}
	
	/**
	 * Verifica se uma determinada trilogia pertence a lista
	 * de trilogias cadastradas.
	 * @param trilogia - trilogia a ser verificada
	 * @return True se pertence ou False caso contrário
	 * */
	public boolean pertence(Trilogia trilogia) {
		return trilogias.contains(trilogia);
	}
	
	/**
	 * Recupera todas as trilogias cadastradas na lista do usuário.
	 * @return Uma lista contendo todas as trilogias.
	 * */
	public List<Trilogia> getTrilogias() {
		return new ArrayList<>(trilogias);
	}
	
	/**
	 * Representa a lista de trilogias do usuário como String.
	 * @return A String representante.
	 * */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (Trilogia trilogia : trilogias) {
			str.append("\n" + trilogia.toString() + "\n");
		}		
		return str.toString();		
	}	
}