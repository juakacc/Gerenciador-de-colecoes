package br.com.gerenciador.colecao.itemColecionavel.auxiliares;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.serie.Serie;
import br.com.gerenciador.colecao.trilogia.Trilogia;
import br.com.gerenciador.interfaces.SerializadoIF;

/**
 * Classe para representar um objeto do tipo SERIE
 * @see Trilogia
 * @see Serie
 * */
public abstract class Seriado implements SerializadoIF {
	
	public static final int SERIE = 1;
	public static final int TRILOGIA = 2;

	private String nome;
	private int tipo;
	protected Set<Item> itens;
	
	/**
	 * Constrói uma nova SERIE e inicializa os atributos
	 * necessários.
	 * @param nome
	 * @param tipo
	 */
	public Seriado(String nome, int tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.itens = new HashSet<>();
	}

	/**
	 * Adiciona um novo item a SERIE, caso atenda as restrinções.
	 * @param item - item a ser adicionado.
	 * @return True se adicionar com sucesso ou False, caso contrário.
	 * */
	public abstract boolean adicionarItem(Item item);
	
	/**
	 * Remove um item da SERIE.
	 * @param item - item a ser removido.
	 * @return True se remover com sucesso ou False, caso contrário.
	 * */
	public abstract boolean removerItem(Item item);
	
	/**
	 * Edita um item na SERIE.
	 * @param novo
	 * @param antigo
	 * @return True se editar com sucesso ou False, caso contrário.
	 * */
	public abstract boolean editarItem(Item novo, Item antigo);
	
	/**
	 * Verifica se um determinado item pertence a SERIE
	 * @return True se o item pertence ou False, caso contrário
	 * */
	public boolean pertence(Item item) {
		return itens.contains(item);
	}
	
	/**
	 * @return total de itens que estão cadastrados nessa SERIE
	 * */
	public int totalCadastrados() {
		return itens.size();
	}
	
	/**
	 * Método que retorna uma String para auxiliar na persistencia
	 * de dados.
	 * @return A string para gravação
	 * */
	@Override
	public String imprimirParaArquivo() {
		
		StringBuilder s = new StringBuilder();
		s.append(getTipo() + ";");
		s.append(getNome());
		
		return s.toString();
	}
	
	/**
	 * @return O código de Hash para um SERIE
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + tipo;
		return result;
	}

	/**
	* Testa a igualdade de um objeto com essa {@link Seriado}. 
	* Duas {@link Seriado} são iguais se elas possuem o mesmo nome e tipo.
	* @param obj - O objeto a comparar.
	* @return true se o objeto for igual ou false caso contrário.
	* */	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Seriado)) {
			return false;
		}
		Seriado outra = (Seriado) obj;
		return this.getNome().equalsIgnoreCase(outra.getNome()) &&
				this.getTipo() == outra.getTipo();
	}

	/**
	 * @return Uma lista contendo os itens pertencentes a essa SERIE
	 * */
	public List<Item> getItens() {
		return new ArrayList<>(itens);
	}
	
	/**
	 * @return O nome da trilogia
	 * */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome da trilogia
	 * @param nome - novo nome para a trilogia
	 * */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return O tipo da trilogia
	 * */
	public int getTipo() {
		return tipo;
	}
}
