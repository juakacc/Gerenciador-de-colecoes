package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;

import java.util.List;

import br.com.gerenciador.colecao.*;
import br.com.gerenciador.colecao.trilogia.*;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.exceptions.TrilogiaNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de trilogias
 * cadastradas com itens da coleção do usuário.
 * */
public class GerenciaTrilogia implements GerenciamentoIF {
	
	private static RepositorioTrilogia trilogias;
	
	/**
	 * Constrói o gerenciador e inicializa 
	 * os atributos necessários.
	 * */
	public GerenciaTrilogia() {
		trilogias = RepositorioTrilogia.getInstancia();
	}
	
	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	public void selecionar(int opcao) {
		int escolha = 0;
		String nome;
		Item item = null;
		Trilogia trilogia = null;
		
		switch (opcao) {
		case 1: 		//adicionar uma nova trilogia
			System.out.println("\nEscolha o Item a ser adicionado! ");
			try {
				item = GerenciaSeriado.recuperarItemParaSeriado();
				criarTrilogia(item);	
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 2: 		// editar trilogia
			if (trilogias.isVazio()) {
				System.err.println("\nNenhuma Trilogia cadastrada! Realize um cadastro!");
				break;
			} else {
				if (recuperarBoolean("Mostrar trilogias disponíveis(1-sim/2-não)? ")) {
					trilogias.mostrarTrilogias();			
				}
				try {
					trilogia = recuperarTrilogia();
					do {
						menuEditarTrilogia();
						escolha = recuperarInt("O que deseja fazer(0,1,2,3,4)? ");
						if (escolha == 0) {
							break;
						} else if (escolha >= 1 && escolha <= 4) {
							editarTrilogia(trilogia, escolha);
						} else {
							System.out.println(ENTRADA_INVALIDA);
						}
					} while (true);
				} catch (TrilogiaNotFoundException e) {
					System.err.println(e.getMessage());
				}
			}
			break;
			
		case 3: 		//remover trilogia		
			do {
				menuRemoverTrilogia();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");				
				
				if (escolha == 0) {
					break;
				} else if (escolha == 1 || escolha == 2) {	
					removerTrilogia(escolha);
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}									
			} while (true);				
			break;
			
		case 4: //exibir trilogia			
			do {				
				menuExibirTrilogia();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");	
			
				if (escolha == 0) {
					break;
				} else if (escolha == 1) {
					nome = recuperarString("Informe nome da trilogia pesquisada: ");					
					trilogias.exibirTrilogia(nome);
					break;
				} else if (escolha == 2) {
					trilogias.mostrarTrilogias();
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			
			break;
			
		case 0:		// voltar
			break;
		}
	}
	
	/**
	 * Cria uma nova trilogia ou adiciona a uma existente com o mesmo nome que
	 * estiver cadastrada caso o usuário opte por isso.
	 * @param item - item para inicializar a trilogia
	 * */
	public static void criarTrilogia(Item item) {
		Trilogia trilogia = null;
		
		String nome = recuperarString("Informe o nome da Trilogia a ser criada....: ");
		int tipo = FabricaItem.tipoItem(item);
		trilogia = new Trilogia(nome, tipo);
		
		if (trilogias.pertence(trilogia)) {
			System.out.println("Existe um trilogia cadastrada com esse nome!");
			
			if (recuperarBoolean("Deseja adicionar o item a uma Trilogia já existente(1-sim/2-não)? ")) {
				trilogia = trilogias.getTrilogia(nome, tipo);
				if (trilogia.adicionarItem(item)) {
					System.out.println("Item adicionado a Trilogia...");
				}
				return;
			}
		}
		if (trilogias.adicionarTrilogia(trilogia)) {
			if (trilogia.adicionarItem(item)) {
				System.out.println("Nova trilogia adicionada...");
			}
		}	
	}
	
	/**
	 * Remove trilogia(s) de acordo com a opção escolhida pelo usuário.
	 * @param opcao - opção de remoção escolhida pelo usuário.
	 * */
	public void removerTrilogia(int opcao) {
		
		switch (opcao) {
		case 1:				// especifica
			try {
				Trilogia trilogia = recuperarTrilogia();
				
				if (trilogias.removerTrilogia(trilogia)) {
					System.out.println("Trilogia removida...");
					
					// Perigoso .... Verificar se está funcionando
					if (recuperarBoolean("Deseja remover os itens do acervo também (1-sim/2-não)? ")) {
						Acervo acervo = Acervo.getInstancia();
						List<Item> itens = trilogia.getItens();
						
						for (Item itemAtual : itens) {
							acervo.removerItem(itemAtual);
						}
					} 
				}
			} catch (TrilogiaNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 2:				// todas
			List<Trilogia> todas = trilogias.getTrilogias();
			
			if (trilogias.removerTodas()) {
				System.out.println("Todas trilogias removidas...");
				// Perigoso .... Verificar se está funcionando
				if (recuperarBoolean("Deseja remover os itens do acervo também (1-sim/2-não)? ")) {
					Acervo acervo = Acervo.getInstancia();
					
					for (Trilogia trilogiaAtual : todas) {
						List<Item> itens = trilogiaAtual.getItens();
					
						for (Item itemAtual : itens) {
							acervo.removerItem(itemAtual);
						}
					}
				} 
			}
			break;
		}
	}
	
	/**
	 * Método para que o usuário escolha uma trilogia dentre as cadastradas.
	 * @return Trilogia escolhida pelo usuário
	 * @throws TrilogiaNotFoundException - caso o usuário não escolha nenhuma.
	 * */
	public static Trilogia recuperarTrilogia() throws TrilogiaNotFoundException {
		int escolha;
		String nome = recuperarString("Informe o nome da trilogia: ");
		
		List<Trilogia> aux = trilogias.getTrilogia(nome);
		
		System.out.println("\n ID");
		for (int i = 0; i < aux.size(); i++) {
			System.out.println("[" + (i+1) + "] - " + aux.get(i).toString());
		}
		System.out.println("-----------------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID da Trilogia(0 - sair)? ");
			if (escolha == 0) {
				throw new TrilogiaNotFoundException("Nenhuma trilogia escolhida!");
			}
		} while (escolha < 1 || escolha > aux.size());
		
		Trilogia retorno = aux.get(escolha - 1);
		return retorno;
	}
	
	/**
	 * Método para que o usuário escolha um item dentre os cadastrados
	 * em uma determinada trilogia.
	 * @param trilogia - trilogia onde se encontra os itens
	 * @return item escolhido pelo usuário
	 * @throws ItemNotFoundException - caso o usuário não escolha nenhum
	 * dos itens disponiveis.
	 * */
	public Item recuperarItemTrilogia(Trilogia trilogia) throws ItemNotFoundException {
		int escolha = 0;	
		List<Item> itens = trilogia.getItens();
		
		System.out.println("\n ID");
		for (int i = 0; i < itens.size(); i++) {
			System.out.println("[" + (i+1) + "] - " + itens.get(i).getTitulo());
		}	
		System.out.println("-----------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do Item da trilogia(0 - sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > itens.size());
		
		Item item = itens.get(escolha - 1);
		return item;
	}
	
	/**
	 * Edita uma trilogia de acordo com opção escolhida pelo usuário.
	 * @param trilogia - Trilogia a ser editada
	 * @param escolha - opção para edição da trilogia (0 < opcao < 5)
	 * */
	public void editarTrilogia(Trilogia trilogia, int escolha) {
		Item item = null;
		
		switch (escolha) {					
		case 1:				 //editar nome da trilogia							 
			String nome = recuperarString("Informe o novo nome da Trilogia: ");							 
			trilogia.setNome(nome);							
			break;
		
		case 2: 			//adicionar item
			do {
				System.out.println("Escolha o Item a ser adicionado!");
				try {
					item = GerenciaSeriado.recuperarItemParaSeriado();
					if (trilogia.adicionarItem(item)) {
						System.out.println("Item adicionado à trilogia...");
					} else {
						break;
					}
				} catch (ItemNotFoundException e) {
					System.err.println(e.getMessage());
					break;
				}								
			} while (recuperarBoolean("Deseja adicionar outro item(1-sim/2-não)? "));											
			break;
				
		case 3:				//editar item
			do {
				try {
					item = recuperarItemTrilogia(trilogia);
					System.out.println("\nInforme o Novo Item!");
					Item novo = GerenciaSeriado.recuperarItemParaSeriado();								
						
					if (trilogia.editarItem(novo, item)) {
						System.out.println("Item editado...");
					} else {
						break;
					}	
				} catch (ItemNotFoundException e) {
					System.err.println(e.getMessage());
					break;
				}
			} while (recuperarBoolean("Deseja editar mais itens(1-sim/2-não)? "));
			break;
				
		case 4:		// remover item
			do {
				try {
					item = recuperarItemTrilogia(trilogia);
					if (trilogia.totalCadastrados() == 1) {
						System.err.println("A trilogia possui apenas um item. "
								+ "Caso seja removido a trilogia também será removida!");
						
						if(recuperarBoolean("Deseja realmente remover o item(1-sim/2-não)? ")) {
							if (trilogia.removerItem(item)) {
								System.out.println("Item e Trilogia removidos...");
								if (recuperarBoolean("Deseja remover este item do acervo(1-sim/2-não)? ")) {
									if (Acervo.getInstancia().removerItem(item))
										System.out.println("Item removido do acervo...");
								}	
							}
							break;
						}
					} else {
						if (trilogia.removerItem(item)) {
							System.out.println("Item removido da Trilogia...");
							if (recuperarBoolean("Deseja remover item do acervo(1-sim/2-não)? ")) {
								if (Acervo.getInstancia().removerItem(item))
									System.out.println("Item removido do acervo...");	
							}
						}
					}
				} catch (ItemNotFoundException e) {
					System.err.println(e.getMessage());
					break;
				}
			} while (recuperarBoolean("Deseja remover mais itens(1-sim/2-não)? "));
			break;
		}
	}
}