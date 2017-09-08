package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;
import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de
 * seriados (trilogias, series, sagas e dlc's) cadastradas com 
 * itens da coleção do usuário.
 * */
public class GerenciaSeriado implements GerenciamentoIF {

	private GerenciaTrilogia mTrilogia;
	private GerenciaSerie mSerie;
	private GerenciaSaga mSaga;
	private GerenciaDlc mDlc;
	
	/**
	 * Constrói o gerenciador e instancia os atributos
	 * necessários para o gerenciamento dos seriados. 
	 * */
	public GerenciaSeriado() {
		this.mTrilogia = new GerenciaTrilogia();
		this.mSerie = new GerenciaSerie();
		this.mSaga = new GerenciaSaga();
		this.mDlc = new GerenciaDlc();
	}

	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuário
	 * */
	public void selecionar(int opcao) {
		
		switch(opcao) {
		
		case 1:			// trilogia
			do {
				menuTrilogia();
				opcao = recuperarInt("O que deseja fazer(0,1,2,3,4)? ");
				if (opcao == 0) {
					break;
				} else if (opcao >= 1 && opcao <= 4) {
					mTrilogia.selecionar(opcao);
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			break;
		
		case 2:			// serie
			do {
				menuSerie();
				opcao = recuperarInt("O que deseja fazer(0,1,2,3,4)? ");
				
				if (opcao == 0) {
					break;
				} else if (opcao >= 1 && opcao <= 4) {
					mSerie.selecionar(opcao);
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			break;
		
		case 3:			// sagas
			do {
				menuSaga();
				opcao = recuperarInt("O que deseja fazer(0,1,2,3,4)? ");
				
				if (opcao == 0) {
					break;
				} else if(opcao >= 1 && opcao <= 4) {
					mSaga.selecionar(opcao);
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while(true);
			break;
			
		case 4:			// dlcs
			do {
				menuDlc();
				opcao = recuperarInt("O que deseja fazer(0,1,2,3)? ");
				
				if (opcao == 0) {
					break;
				} else if(opcao >= 1 && opcao <= 3) {
					mDlc.selecionar(opcao);
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			break;
		}
	}
	
	/**
	 * Método para que o usuário escolha um item do acervo
	 * que esteja disponivel para ser adicionado em um seriado (serie, trilogia)
	 * @return o item escolhido
	 * @throws ItemNotFoundException caso o usuário não escolha nenhum
	 * */
	public static Item recuperarItemParaSeriado() throws ItemNotFoundException {
		
		Item item;
		do {
			try {
				item = GerenciaItem.recuperarItemAll();
				
				if (item.getSeriado() != null) {
					System.out.println("Esse item já pertence a um SERIADO!");
				} else {
					return item;
				}
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
				if (!(recuperarBoolean("Tentar novamente(1-sim/2-não)? "))) {
					throw e;
				}
			}
		} while (true);
	}
}