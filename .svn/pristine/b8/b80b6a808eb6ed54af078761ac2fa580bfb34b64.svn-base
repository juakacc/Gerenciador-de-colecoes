package br.com.gerenciador.exceptions;

/**
 * Exceção para sinalizar que uma saga não existe ou não
 * foi encontrada, para que não seja necessária a 
 * verificação se a saga que está sendo manipulada é null
 * ou não.
 * */
public class SagaNotFoundException extends Exception {

	private static final long serialVersionUID = 5961514371947823579L;

	/**
	 * Constrói um nova exceção com a mensagem padrão.
	 * */
	public SagaNotFoundException() {
		super("Saga não encontrada!");
	}
	
	/**
	 * Constrói uma nova exceção com uma mensagem 
	 * de detalhe especificada.
	 * @param mensagem
	 * */
	public SagaNotFoundException(String msg) {
		super(msg);
	}
}