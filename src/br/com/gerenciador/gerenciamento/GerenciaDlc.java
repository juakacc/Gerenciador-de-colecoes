package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;

import java.util.ArrayList;
import java.util.List;

import br.com.gerenciador.colecao.*;
import br.com.gerenciador.colecao.itemColecionavel.JogoDeVideoGame;
import br.com.gerenciador.colecao.itemColecionavel.dlc.*;
import br.com.gerenciador.exceptions.DlcNotFoundException;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuário do sistema com relação a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de DLC's
 * */
public class GerenciaDlc implements GerenciamentoIF {

	RepositorioDlc minhasDlcs;
	
	/**
	 * Constrói o gerenciador e inicializa
	 * o repositório de DLC's para gerenciamento
	 * */
	public GerenciaDlc() {
		this.minhasDlcs = RepositorioDlc.getInstancia();
	}
	
	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	@Override
	public void selecionar(int opcao) {
		int escolha;
		
		switch (opcao) {
		case 1:		// adicionar DLC
			
			System.out.println("\nEscolha o jogo!");
			try {
				JogoDeVideoGame jogo = recuperarJogoDeVideoGame();
				Dlc dlc = criarDlc(jogo);
				if (minhasDlcs.adicionarDlc(dlc)) {
					System.out.println("Dlc adicionada com sucesso...");
				}
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 2:		// remover DLC
			if (minhasDlcs.isVazio()) {
				System.out.println("Nenhuma DLC está sendo usada...");
			} else {
				do {
					menuRemoverDlc();
					escolha = recuperarInt("O que deseja fazer(0,1,2)? ");
					if (escolha == 0) {
						break;
					} else if (escolha == 1 || escolha == 2) {
						removerDlc(escolha);
						break;
					} else {
						System.out.println(ENTRADA_INVALIDA);
					}
				} while (true);
			}
			break;
			
		case 3:		// exibir DLC
			do {
				menuExibirDlc();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");
				if (escolha == 0) {
					break;
				} else if (escolha == 1) {
					String titulo = recuperarString("Qual o título da DLC? ");
					minhasDlcs.mostrarDlc(titulo);
					break;
				} else if (escolha == 2) {
					minhasDlcs.mostrarDlcs();
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			break;
		}
	}
	
	/**
	 * Adquire uma nova DLC para um determinado jogo
	 * @param jogo - jogo que receberá a DLC
	 * @return a nova DLC
	 * */
	private Dlc criarDlc(JogoDeVideoGame jogo) {
	
		String titulo = recuperarString("Informe o titulo da DLC.: ");
		String localizacao = recuperarString("Informe a localizacao...: ");
		
		Dlc dlc = new Dlc(titulo, localizacao, jogo);
		return dlc;
	}
	
	/**
	 * Gerencia a remoção de DLC(s)
	 * @param escolha - opção escolhida pelo usuário (1||2)
	 * */
	private void removerDlc(int escolha) {
		switch (escolha) {
		case 1:
			System.out.println("\nEscolha a DLC!");
			try {
				Dlc dlc = recuperarDlc();
				if (minhasDlcs.removerDlc(dlc)) {
					System.out.println("DLC removida com sucesso...");
				}
			} catch (DlcNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
			
		case 2:
			if (minhasDlcs.removerTodas()) {
				System.out.println("Todas as DLC's foram removidas...");
			}
		}
	}

	/**
	 * Método para que o usuário escolha um Jogo de video game dentre os que estão
	 * cadastrados em seu acervo.
	 * @return o Jogo escolhido pelo usuário
	 * @throws ItemNotFoundException caso o usuário não escolha nenhum
	 * */
	private JogoDeVideoGame recuperarJogoDeVideoGame() throws ItemNotFoundException {
		List<Item> jogosDoAcervo = Acervo.getInstancia().jogoDeVideoGameList();
		List<JogoDeVideoGame> gamesComMesmoNome = new ArrayList<>();
		int escolha;
		String titulo = recuperarString("Título do item: ");
	
		for (Item jogoAux : jogosDoAcervo) {
			if (titulo.equalsIgnoreCase(jogoAux.getTitulo())) {
				gamesComMesmoNome.add((JogoDeVideoGame) jogoAux);
			}
		}
		if (gamesComMesmoNome.isEmpty()) {
			throw new ItemNotFoundException("Nenhum jogo com esse título!");
		}
		
		System.out.println("\n ID");
		for (int i = 0; i < gamesComMesmoNome.size(); i++) {
			System.out.println("[" +(i+1)+ "] - " + gamesComMesmoNome.get(i).toString());
		}
		System.out.println("-----------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do item(0 - sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > gamesComMesmoNome.size());
		
		JogoDeVideoGame item = gamesComMesmoNome.get(escolha - 1);
		return item;
	}
	
	/**
	 * Método para o usuário escolher uma DLC dentre as cadastradas por ele
	 * @return A DLC escolhida
	 * @throws DlcNotFoundException caso o usuário não escolha nenhuma 
	 * */
	private Dlc recuperarDlc() throws DlcNotFoundException {
		List<Dlc> lista = minhasDlcs.getDlcs();
		int escolha;
		
		System.out.println("\n ID");
		for (int i = 0; i < lista.size(); i++) {
			Dlc dlc = lista.get(i);
			System.out.println("["+(i+1)+"]" + dlc.getTitulo() + 
					" -> " + dlc.getJogo().getTitulo());
		}
		System.out.println("-----------------------------");
		
		do {
			escolha = recuperarInt("Qual o ID do item(0 - sair)? ");
			if (escolha == 0) {
				// criar uma Exception para DLC
				throw new DlcNotFoundException("Nenhuma DLC escolhida!");
			}
		} while (escolha < 1 || escolha > lista.size());
		
		Dlc dlc = lista.get(escolha - 1);
		return dlc;
	}

}