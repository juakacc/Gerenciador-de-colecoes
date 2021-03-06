package br.com.gerenciador.exceptions;

/**
 * Exceção para sinalizar que um amigo não existe ou não
 * foi encontrado, para que não seja necessária a 
 * verificação se o amigo que está sendo manipulado é null
 * ou não.
 * */
public class AmigoNotFoundException extends Exception {

	private static final long serialVersionUID = -5055998045018990932L;
	
	/**
	 * Constrói um nova exceção com a mensagem padrão.
	 * */
	public AmigoNotFoundException() {
		super("Amigo não foi encontrado!");
	}
	
	/**
	 * Constrói uma nova exceção com uma mensagem 
	 * de detalhe especificada.
	 * @param mensagem
	 * */
	public AmigoNotFoundException(String mensagem) {
		super(mensagem);
	}
}