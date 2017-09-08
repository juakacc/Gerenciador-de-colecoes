package br.com.gerenciador.exceptions;

/**
 * Exceção para sinalizar que um item não existe ou não
 * foi encontrado, para que não seja necessária a 
 * verificação se o item que está sendo manipulado é null
 * ou não.
 * 
 * */
public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 8633375386171770943L;
	
	/**
	 * Constrói um nova exceção com a mensagem padrão.
	 * */
	public ItemNotFoundException() {
		super("O item não foi encontrado!");
	}
	
	/**
	 * Constrói uma nova exceção com uma mensagem 
	 * de detalhe especificada.
	 * @param mensagem
	 * */
	public ItemNotFoundException(String mensagem) {
		super(mensagem);
	}
}