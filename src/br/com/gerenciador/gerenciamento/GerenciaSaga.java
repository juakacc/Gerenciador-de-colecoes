package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;

import java.util.ArrayList;
import java.util.List;

import br.com.gerenciador.colecao.*;
import br.com.gerenciador.colecao.itemColecionavel.Hq;
import br.com.gerenciador.colecao.itemColecionavel.saga.*;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.exceptions.SagaNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de sagas
 * */
public class GerenciaSaga implements GerenciamentoIF {

	private static RepositorioSaga sagas;
	
	/**
	 * Constrói o gerenciador e 
	 * inicializa a coleção de sagas do usuário
	 * */
	public GerenciaSaga() {
		sagas = RepositorioSaga.getInstancia();
	}
	
	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	public void selecionar(int opcao) {
		int escolha = 0;
		String nome;
		Hq hq = null;
		Saga saga = null;
		
		switch (opcao) {
		case 1: //adicionar Saga
			
			System.out.println("\nEscolha o Item a ser adicionado! ");
			do {
				try {
					hq = recuperarHq();
					
					if (hq.getSaga() != null) { 
						System.out.println("Esse HQ já pertence a uma saga...");
					} else {
						int numero;
						do {
							numero = recuperarInt("Esse HQ tem qual número na saga? ");
						} while(numero < 1);
						
						criarSaga(hq, numero);
						break;
					}	
				} catch (ItemNotFoundException e) {
					System.err.println(e.getMessage());
					if (!(recuperarBoolean("Tentar novamente (1-sim/2-não)? ")))
						break;
				}	
			} while (true);	
			break;
			
		case 2: //editar saga
			
			if (sagas.isVazio()) {
				System.out.println("\nNenhuma saga cadastrada! Realize um cadastro...");
				break;
			}
			if (recuperarBoolean("Mostrar Sagas disponíveis(1-sim/2-não)? ")) {
				sagas.mostrarSagas();			
			}
			
			try {
				saga = recuperarSaga();
				do {
					menuEditarSaga();
					escolha = recuperarInt("O que deseja fazer(0,1,2,3,4,5)? ");
					
					if (escolha == 0) {
						break;
					} else if (escolha >= 1 && escolha <= 5) {
						editar(saga, escolha);
					} else {
						System.out.println("Opcão indisponível! Tente novamente... ");
					}
				} while (true);
			} catch (SagaNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 3: //remover Saga
			do {
				menuRemoverSaga();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");				
				
				if (escolha == 0) {
					break;
				} else if (escolha == 1 || escolha == 2) {			
					removerSaga(escolha);
					break;
				} else {
					System.out.println("Opção indisponível! Tente novamente... ");
				}						
			} while (true);				
			break;
			
		case 4: //exibir Saga			
			do {				
				menuExibirSaga();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");				
				
				if (escolha == 0) {
					break;
				} else if (escolha == 1) {
					nome = recuperarString("Informe nome da Saga pesquisada: ");					
					sagas.verSaga(nome);
					break;
				} else if (escolha == 2) {
					sagas.mostrarSagas();
					break;
				} else {
					System.out.println("Opção indisponível! Tente novamente... ");
				}
			} while (true);
			break;
			
		case 0:
			break;
		}
	}
	
	/**
	 * Edita uma saga de acordo com opção escolhida pelo usuário.
	 * @param saga - Saga a ser editada
	 * @param escolha - opção para edição da saga (0 < opcao < 5)
	 * */
	private void editar(Saga saga, int escolha) throws ItemNotFoundException {
		Hq hq = null;
		
		switch (escolha) {					
		case 1: 		//editar nome da Saga							 
			String nome = recuperarString("Informe o novo nome da Saga: ");							 
			saga.setNome(nome);							
			break;							
				
		case 2: 		//editar quantidade de itens da Saga
			int quantidade = recuperarInt("Informe a nova quantidade de itens da saga: ");
			saga.setQuantItens(quantidade);
			break;
				
		case 3: 		//adicionar item
			do {
				System.out.println("Escolha o Item a ser adicionado!");
				hq = recuperarHq();						
				int numero;
				do {
					numero = recuperarInt("Esse HQ tem qual número na saga? ");
				} while(numero < 1);
				
				if (saga.adicionarItemNaSaga(hq, numero)) {
					System.out.println("Item adicionado...");
				} else {
					break;
				}
			} while (recuperarBoolean("Deseja adicionar mais itens(1-sim/2-não)? "));											
			break;
				
		case 4:	//editar item	
		// Verificar...
			do {
				Hq antigo = recuperarItemSaga(saga);
					
				System.out.println("\nInforme o Novo Item!");
				Hq novo = recuperarHq();								
				
				if (saga.editarItemSaga(novo, antigo))
					System.out.println("Item editado...");
				
			} while (recuperarBoolean("Deseja editar mais itens(1-sim/2-não)? "));
			break;
				
		case 5:	//excluir item					
			do {
				hq = recuperarItemSaga(saga);
				
				if (saga.totalCadastrado() == 1) {		// ultimo item 
					System.out.println("A Saga possui apenas um HQ. Caso seja removido a Saga também será removida!");
					
					if(recuperarBoolean("\nDeseja realmente remover o item (1-sim/2-não)? ")) {
						
						if (saga.removerItemSaga(hq)) {
							System.out.println("HQ e Saga removidos...");
							if (recuperarBoolean("Deseja remover item do acervo(1-sim/2-não)? ")) {
								if (Acervo.getInstancia().removerItem(hq))
									System.out.println("HQ removido do acervo...");
							}	
						}
						break;
					}
				} else {
					
					if (saga.removerItemSaga(hq)) {
						System.out.println("Item removido da saga...");
							
						if (recuperarBoolean("Deseja remover esse item do acervo(1-sim/2-não)? ")) {
							if (Acervo.getInstancia().removerItem(hq))
								System.out.println("Item removido do acervo...");
						}
					}
				}							
			} while (recuperarBoolean("Deseja remover mais itens(1-sim/2-não)? "));
			break;
		}
	}

	/**
	 * Cria uma saga caso a mesma não exista ou adiciona o HQ
	 * a uma se ela já existir.
	 * @param hq - HQ a ser cadastrado
	 * @param numero - número desse HQ na saga
	 * */
	public static void criarSaga(Hq hq, int numero) {
		Saga saga = null;
		
		String nome = recuperarString("Informe o nome da Saga a ser adicionada....: ");
		saga = new Saga(nome);
		
		if (sagas.pertence(saga)) {		// existe uma saga com esse nome
			System.out.println("Existe uma saga cadastrada com esse nome!");
			if (recuperarBoolean("Desejar adicionar o HQ a uma saga já existente (1-sim/2-não)?")) {
				saga = sagas.getSagaPorNome(nome);
				
				if (saga.adicionarItemNaSaga(hq, numero)) {
					System.out.println("Item adicionado a Saga...");
				}
				return;
			}
		}
		
		int quantidade; 	
		do {
			quantidade = recuperarInt("Informe a quantidade de itens dessa saga.....: ");
		} while (quantidade <= 0); 
		
		saga = new Saga(nome, quantidade);
		if (saga.adicionarItemNaSaga(hq, numero)) {
			if (sagas.adicionarSaga(saga))
				System.out.println("Nova Saga adicionada...");
		}
	}
	
	/**
	 * Método para que o usuário escolha um HQ de sua coleção.
	 * @return O Hq que o usuário escolher
	 * @throws ItemNotFoundException caso o usuário não escolha nenhum dos HQ's
	 * */
	private Hq recuperarHq() throws ItemNotFoundException {
		List<Item> itens = Acervo.getInstancia().hqList();
		int escolha;
		String titulo = recuperarString("Título do HQ: ");
		
		List<Hq> hqs = new ArrayList<>();
		for (Item item : itens) {
			if (titulo.equalsIgnoreCase(item.getTitulo())) {
				hqs.add((Hq) item);
			}
		}
		if (hqs.isEmpty()) {
			throw new ItemNotFoundException("Nenhum HQ encontrado com esse nome!");
		}
		
		System.out.println("\n ID");
		for (int i = 0; i < hqs.size(); i++) {
			System.out.println("[" +(i+1)+ "] - " + hqs.get(i).toString());
		}
		System.out.println("-----------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do item(0 - sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > hqs.size());
		
		Hq item = hqs.get(escolha - 1);
		return item;
	}
	
	/**
	 * Método para que o usuário escolha uma saga dentre as cadastradas em sua coleção
	 * @return A Saga escolhida
	 * @throws SagaNotFoundException caso o usuário não escolha nenhuma saga
	 * */
	public static Saga recuperarSaga() throws SagaNotFoundException {
		int escolha;
		String nome = recuperarString("Informe a Saga: ");
		
		List<Saga> sagasAux = sagas.getSaga(nome);
		
		System.out.println("\n ID");
		for (int i = 0; i < sagasAux.size(); i++) {
			System.out.println("[" + (i+1) + "] - " + sagasAux.get(i).toString() + "\n");
		}
		System.out.println("---------------------------\n");
		
		do {
			escolha = recuperarInt("Qual o ID da Saga(0-sair)? ");
			if (escolha == 0) {
				throw new SagaNotFoundException("Nenhuma saga escolhida!");
			}
		} while (escolha < 1 || escolha > sagasAux.size());
		
		Saga saga = sagasAux.get(escolha - 1);
		return saga;
	}
	
	/**
	 * Método para que o usuário escolha um HQ cadastrado em determinada saga
	 * @param saga - Saga escolhida
	 * @return O HQ escolhido
	 * @throws ItemNotFoundException caso o usuário não escolha nenhum item
	 * */
	private Hq recuperarItemSaga(Saga saga) throws ItemNotFoundException {
		int escolha = 0;
		List<Hq> itens = saga.getItens();
		
		System.out.println("\n ID");
		for (int i = 0; i < itens.size(); i++) {
			if (itens.get(i) != null) {
				System.out.println("[" + (i+1) + "] - " + itens.get(i).getTitulo());
			}
		}	
		System.out.println("-------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do Item da Saga(0-sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > itens.size());
		
		Hq hq = saga.getItens().get(escolha - 1);
		return hq;
	}

	/**
	 * Método para gerenciar a remoção da(s) Saga(s)
	 * @param escolha - método que o usuário escolher (1 || 2)
	 * */
	private void removerSaga(int escolha) {
		
		switch(escolha) {
		case 1:
			try {
				Saga saga = recuperarSaga();
				if (sagas.removerSaga(saga)) {
					System.out.println("Saga removida...");
					// Perigoso ...
					if (recuperarBoolean("Deseja remover os itens da saga também da coleção(1-sim/2-não)?")) {
						Acervo acervo = Acervo.getInstancia();
						List<Hq> itens = saga.getItens();
						for (Item i : itens) {
							acervo.removerItem(i);
						}
					}
				}
			} catch (SagaNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
				
		case 2:
			List<Saga> sagasAux = sagas.getSagas();
			
			if (sagas.removerTodas()) {
				System.out.println("Todas Sagas removidas...");
				// Perigoso ...
				if (recuperarBoolean("Deseja remover os itens das sagas também da coleção(1-sim/2-não)?")) {
					Acervo acervo = Acervo.getInstancia();
										
					for (Saga sagaAux : sagasAux) {
						List<Hq> itens = sagaAux.getItens();
							
						for (Hq aux : itens) {
							acervo.removerItem(aux);
						}
					}
				}
			}
			break;
		}
	}
}