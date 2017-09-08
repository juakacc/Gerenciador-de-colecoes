package br.com.gerenciador.colecao.itemColecionavel.saga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.Hq;
import br.com.gerenciador.interfaces.SerializadoIF;

/**
 * Classe para representar a Saga de um determinado HQ
 * */
public class Saga implements SerializadoIF {
	
	private String nome;
	private int itensPertencentes;
	private Hq[] hqs;
	
	/**
	 * Constrói uma saga atribuindo o nome e inicializando
	 * o array de itens dessa saga com o número passado como parametro 
	 * @param nome - nome da saga a que pertence
	 * @param quantidadeItens - quantidade de itens dessa saga
	 * */
	public Saga(String nome, int quantidadeItens) {
		this(nome);
		hqs = new Hq[quantidadeItens];
	}
	
	/**
	 * Constrói uma saga atribuindo o nome.
	 * (para efeito de comparação com outras)
	 * @param nome - Nome para a saga
	 * */
	public Saga(String nome) {
		this.nome = nome;
		this.itensPertencentes = 0;
	}
	
	/**
	 * Adiciona um novo HQ a saga
	 * @param hq - novo HQ a ser adicionado
	 * @param numero - número desse HQ na saga. (1 <= numero <= TAM)
	 * @return True se adicionou com sucesso ou False, caso contrário
	 * */
	public boolean adicionarItemNaSaga(Hq hq, int numero) {
		if (hq == null || hq.getSaga() != null || pertence(hq)) {
			return false;
		}
		numero--;		// apenas para acertar no índice do array
		if (numero >= tamanhoDaSaga() || numero < 0) {
			System.out.println("A saga não contém o número informado...");
			return false;
		} else {
			if (hqs[numero] == null) {
				hqs[numero] = hq;
				hq.setSaga(this);
				
				this.itensPertencentes++;
				return true;
			} else {
				System.out.println("HQ com mesmo número já cadastrado...");
				return false;
			}
		}
	}
	
	/**
	 * Edita um HQ nessa saga
	 * @param novo
	 * @param antigo
	 * @return True se conseguir editar ou False, caso contrário.
	 * */
	public boolean editarItemSaga(Hq novo, Hq antigo) {
		if (novo == null || antigo == null) {
			return false;
		}
		if (novo.getSaga() != null) {
			return false;
		}
		
		for (int i = 0; i < tamanhoDaSaga(); i++) {
			
			if (antigo.equals(hqs[i])) {
				hqs[i] = novo;
				antigo.setSaga(null);
				novo.setSaga(this);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove um HQ da Saga, caso esse HQ for o 
	 * único pertencente a saga, essa saga é removida
	 * da lista de sagas do usuário
	 * @param hq - HQ a ser removido
	 * @return True se for removido com sucesso ou False, caso contrário
	 * */
	public boolean removerItemSaga(Hq hq) {
		if (hq == null) {
			return false;
		}
		for (int i = 0; i < tamanhoDaSaga(); i++) {
			
			if (hq.equals(hqs[i])) {
				hqs[i] = null;
				hq.setSaga(null);		
				this.itensPertencentes--;
				
				if (totalCadastrado() == 0) {	// era o último HQ
					RepositorioSaga.getInstancia().removerSaga(this);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica se a saga tem esse HQ cadastrado.
	 * @param hq - HQ a ser procurado
	 * @return True se pertence a saga ou False, caso contrário
	 * */
	public boolean pertence(Hq hq) {
		if (hq == null) {
			return false;
		}
		for (int i = 0; i < tamanhoDaSaga(); i++) {
			if (hq.equals(hqs[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return A quantidade de HQ cadastrados nessa saga.
	 * */
	public int totalCadastrado() {
		return this.itensPertencentes;
	}
	
	/**
	 * Retorna um valor de código de hash para a saga
	 * @return O código de hash
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	* Testa a igualdade de um objeto com este item. Dois itens 
	* são iguais se eles possuem o mesmo nome.
	* @param obj - O objeto a comparar com este itme.
	* @return true se o objeto for igual ou false caso contrário.
	*/	
	@Override 
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Saga)) {
			return false;
		}
		Saga outra = (Saga) obj;
		return this.getNome().equalsIgnoreCase(outra.getNome());
	}
	
	/**
	 * Representa uma saga como String
	 * @return String de representação
	 * */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("-> Saga: " + getNome() + "\n");
		
		for (int i = 0; i < tamanhoDaSaga(); i++) {
			Item item = hqs[i];
			if (item == null) {
				str.append((i+1)+" => Faltando...\n");
			} else {
				str.append((i+1)+" => " + item.toString() + "\n");
			}
		}
		if (totalCadastrado() == tamanhoDaSaga()) {
			str.append("\nA saga está completa! :D");
		} else {
			str.append("\nFalta " + (tamanhoDaSaga() - totalCadastrado()) + " itens para completar a saga!");
		}
		return str.toString();
	}
	
	/**
	 * Retorna o nome da saga
	 * @return String nome
	 * */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Configura o nome da saga
	 * @param nome - novo nome
	 * */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o numero do item na saga
	 * @return int numero
	 * */
	public int getNumero(Hq hq) {
		for (int i = 0; i < tamanhoDaSaga(); i++) {
			if (hq.equals(hqs[i])) {
				return i+1;
			}
		}
		return -1;
	}
	
	/**
	 * @return Quantos HQ's essa saga possui.
	 * */
	public int tamanhoDaSaga() {
		return hqs.length;
	}

	/**
	 * Altera a quantidade de itens de uma Saga
	 * Se a nova quantidade for maior do que a atual...
	 * @param quantItens - nova quantidade de itens
	 * */
	public void setQuantItens(int quantItens) {
		if (quantItens < tamanhoDaSaga()) {
			return;
		}
		Hq[] aux = new Hq[quantItens];

		for (int i = 0; i < tamanhoDaSaga(); i++) {
			aux[i] = hqs[i];
		}
		hqs = aux;
	}

	/**
	 * Recupera os HQ's que estão cadastrados nessa saga.
	 * @return Uma Lista contendos os HQ's
	 * */
	public List<Hq> getItens() {
		return new ArrayList<>(Arrays.asList(hqs));
	}

	/**
	 * Método que retorna uma String para auxiliar na persistencia
	 * de dados.
	 * @return A string para gravação
	 * */
	@Override
	public String imprimirParaArquivo() {
		StringBuilder str = new StringBuilder();
		
		str.append(getNome() + ";");
		str.append(tamanhoDaSaga());
		
		return str.toString();
	}

}