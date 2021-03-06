package br.com.gerenciador.interfaces;

/**
 * A interface de gerenciamento do sistema, para interação
 * com o usuário.
 * */
public interface GerenciamentoIF {

	/**
	 * Opção inválida escolhida pelo usuário.
	 * */
	public static final String ENTRADA_INVALIDA = 
			new String("Opcão indisponível! Tente novamente... ");
	
	/**
	 * Método para gerenciar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	public void selecionar(int opcao);
	
}