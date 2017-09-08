package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;

import java.util.List;

import br.com.gerenciador.colecao.*;
import br.com.gerenciador.colecao.serie.*;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.exceptions.SerieNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para iterar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de séries
 * cadastradas com itens da coleção do usuário.
 * */
public class GerenciaSerie implements GerenciamentoIF {

	private static RepositorioSerie series;
	
	/**
	 * Constrói o gerenciador e inicializa 
	 * os atributos necessários.
	 * */
	public GerenciaSerie() {
		series = RepositorioSerie.getInstancia();
	}
	
	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	public void selecionar(int opcao) {
		int escolha = 0;
		String nome;
		Item item = null;
		Serie serie = null;
		
		switch (opcao) {
		case 1: 			//adicionar Serie
			System.out.println("\nEscolha o Item a ser adicionado! ");
			try {
				item = GerenciaSeriado.recuperarItemParaSeriado();
				criarSerie(item);	
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 2:				 //editar Serie
			if (series.isVazio()) {
				System.out.println("\nNenhuma série cadastrada! Realize um cadastro...");
				break;
			} else { 
				if (recuperarBoolean("Mostrar Series disponíveis(1-sim/2-não)? ")) {
					series.mostrarSeries();			
				}
				try {
					serie = recuperarSerie();
					do {
						menuEditarSerie();
						escolha = recuperarInt("O que deseja fazer(0,1,2,3,4,5)? ");
						if (escolha == 0) {
							break;
						} else if (escolha >= 1 && escolha <= 5) {
							editar(serie, escolha);
						} else {
							System.out.println(ENTRADA_INVALIDA);
						}
					} while (true);
				} catch (SerieNotFoundException e2) {
					System.err.println(e2.getMessage());
				}
			}
			break;
			
		case 3:				 //remover Serie			
			do {
				menuRemoverSerie();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");				
				
				if (escolha == 0) {
					break;
				} else if (escolha == 1 || escolha == 2) {
					removerSerie(escolha);
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}						
			} while (true);				
			break;
			
		case 4:				 //exibir Serie			
			
			do {				
				menuExibirSerie();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");				
				if (escolha == 0) {
					break;
				} else if (escolha == 1) {
					nome = recuperarString("Informe nome da Serie pesquisada: ");					
					series.verSerie(nome);
					break;
				} else if (escolha == 2) {
					series.mostrarSeries();
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			break;
			
		case 0:
			break;
		}
	}
	
	/**
	 * Remove série(s) de acordo com a opção escolhida pelo usuário.
	 * @param opcao - opção de remoção escolhida pelo usuário.
	 * */
	private void removerSerie(int escolha) {
		
		switch(escolha) {
		case 1:		// específica
			try {
				Serie serie = recuperarSerie();
				if (series.removerSerie(serie)) {
					System.out.println("Serie removida...");
					
					// Perigoso ...
					if (recuperarBoolean("Deseja remover os itens da séria também da coleção(1-sim/2-não)?")) {
						Acervo acervo = Acervo.getInstancia();
						List<Item> itens = serie.getItens();
						for (Item i : itens) {
							acervo.removerItem(i);
						}
					}
				}
			} catch (SerieNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 2:			// todas as séries
			List<Serie> seriesAux = series.getSeries();
			
			if (series.removerTodas()) {
				System.out.println("Todas Series removidas...");
				// Perigoso ...
				if (recuperarBoolean("Deseja remover os itens das séries também da coleção(1-sim/2-não)?")) {
					Acervo acervo = Acervo.getInstancia();		
					
					for (Serie serieAux : seriesAux) {
						List<Item> itens = serieAux.getItens();
						
						for (Item i : itens) {
							acervo.removerItem(i);
						}
					}
				}
			}
			break;
		}
	}
	
	/**
	 * Edita uma série de acordo com opção escolhida pelo usuário.
	 * @param escolha - opção para edição da série (0 < opcao < 5)
	 * */
	private void editar(Serie serie, int escolha) {
		
		Item item = null;
		
		switch (escolha) {					
		case 1: 			//editar nome da Serie							 
			String nome = recuperarString("Informe novo nome da Serie: ");							 
			serie.setNome(nome);							
			break;							
				
		case 2: 		//editar quantida de itens da serie
			int quantidade = recuperarInt("Informe a nova quantidade de itens da série: ");
			serie.setQuantItens(quantidade);
			break;
				
		case 3: 		//adicionar item
			do {
				System.out.println("Escolha o Item a ser adicionado!");
				try {
					item = GerenciaSeriado.recuperarItemParaSeriado();
					if (serie.adicionarItem(item)) {
						System.out.println("Item adicionado...");
					} else {
						break;
					}
				} catch (ItemNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}								
			} while (recuperarBoolean("Deseja adicionar mais itens(1-sim/2-não)? "));											
			break;
			
		case 4:	//editar item							
			do {
				try {
					item = recuperarItemSerie(serie);
					System.out.println("\nInforme o Novo Item!");
					Item novo = GerenciaSeriado.recuperarItemParaSeriado();								
						
					if (serie.editarItem(novo, item)) {
						System.out.println("Item editado...");
					} else {
						break;
					}
				} catch (ItemNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
			} while (recuperarBoolean("Deseja editar mais itens(1-sim/2-não)? "));
			break;
			
		case 5:	//excluir item				
			do {
				try {
					item = recuperarItemSerie(serie);
					if (serie.totalCadastrados() == 1) {		// ultimo item 
						System.out.println("A Serie possui apenas um item. Caso "
								+ "seja removido a Serie também será removida! ");
							
						if(recuperarBoolean("\nDeseja realmente remover o item(1-sim/2-não)? ")) {
							if (serie.removerItem(item)) {
								System.out.println("Item e Série removidos...");
								if (recuperarBoolean("Deseja remover item do acervo(1-sim/2-não)? ")) {
									if (Acervo.getInstancia().removerItem(item))
										System.out.println("Item removido do acervo...");
								}	
							}
							break;
						}
					} else {
						if (serie.removerItem(item)) {
							System.out.println("Item removido da série...");
							if (recuperarBoolean("Deseja remover esse item do acervo(1-sim/2-não)? ")) {
								if (Acervo.getInstancia().removerItem(item))
									System.out.println("Item removido do acervo...");
							}
						}
					}							
				} catch (ItemNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
			} while (recuperarBoolean("Deseja remover mais itens(1-sim/2-não)? "));
			break;
		}
	}

	/**
	 * Cria uma nova série ou adiciona a uma existente com o mesmo nome que
	 * estiver cadastrada caso o usuário opte por isso.
	 * @param item - item para inicializar a série
	 * */
	public static void criarSerie(Item item) {
		Serie serie = null;
		
		String nome = recuperarString("Informe o nome da Serie a ser adicionada....: ");
		int tipo = FabricaItem.tipoItem(item);
		serie = new Serie(nome, tipo);
		
		if (series.pertence(serie)) {
			System.out.println("Existe uma série cadastrada com esse nome!");
			if (recuperarBoolean("Deseja adicionar o item a uma Serie já existente(1-sim/2-não)? ")) {
				serie = series.getSerie(nome, tipo);
				if (serie.adicionarItem(item)) {
						System.out.println("Item adicionado a Serie...");
				}
				return;
			}
		}
		int quantidade;
		do {
			quantidade = recuperarInt("Informe a quantidade de itens dessa série.....: ");
		} while (quantidade <= 0); 
			
		serie = new Serie(nome, FabricaItem.tipoItem(item), quantidade);
		if (series.adicionarSerie(serie)) {
			if (serie.adicionarItem(item)) {
				System.out.println("Nova Serie adicionada...");
			}
		}
	}
	
	/**
	 * Método para que o usuário escolha uma série dentre as cadastradas.
	 * @return Série escolhida pelo usuário
	 * @throws SerieNotFoundException - caso o usuário não escolha nenhuma.
	 * */
	public static Serie recuperarSerie() throws SerieNotFoundException {
		int escolha;
		String nome = recuperarString("Informe a Serie: ");
		
		List<Serie> seriesAux = series.getSerie(nome);
		
		System.out.println("\n ID");
		for (int i = 0; i < seriesAux.size(); i++) {
			System.out.println("[" + (i+1) + "] - " + seriesAux.get(i).toString() + "\n");
		}
		System.out.println("---------------------------\n");
		
		do {
			escolha = recuperarInt("Qual o ID da Serie(0-sair)? ");
			if (escolha == 0) {
				throw new SerieNotFoundException("Nenhuma serie escolhida!");
			}
		} while (escolha < 1 || escolha > seriesAux.size());
		
		Serie serie = seriesAux.get(escolha - 1);
		return serie;
	}
	
	/**
	 * Método para que o usuário escolha um item dentre os cadastrados
	 * em uma determinada série.
	 * @param serie - serie onde se encontra os itens
	 * @return item escolhido pelo usuário
	 * @throws ItemNotFoundException - caso o usuário não escolha nenhum
	 * dos itens disponiveis.
	 * */
	public Item recuperarItemSerie(Serie serie) throws ItemNotFoundException {
		int escolha = 0;
		List<Item> itens = serie.getItens();
		
		System.out.println("\n ID");
		for (int i = 0; i < itens.size(); i++) {
			System.out.println("[" + (i+1) + "] - " + itens.get(i).getTitulo());
		}	
		System.out.println("-------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do Item da Serie(0-sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > serie.totalCadastrados());
		
		Item item = serie.getItens().get(escolha - 1);
		return item;
	}

}