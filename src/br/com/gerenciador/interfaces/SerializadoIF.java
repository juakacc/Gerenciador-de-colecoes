package br.com.gerenciador.interfaces;

/**
 * Interface para a serialização das instituições existentes
 * no sistema, ajuda na persistencia das informações.
 * Assegura que o objeto pode ser persistido de forma correta.
 * */
public interface SerializadoIF {
	
	/**
	 * Método que retorna uma String para auxiliar na persistencia
	 * de dados.
	 * @return A string para gravação do objeto em arquivo.
	 * */
	public String imprimirParaArquivo();
	
}