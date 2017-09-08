package br.com.gerenciador.gerenciamento.auxiliares;

import java.util.Scanner;

/**
 * Classe para armazenamento dos menus para interacao com o usuario
 * */
public final class Menu {

	private static final int LINHAS = 1;
	private static Scanner in = new Scanner(System.in);

	/**
	 * Menu geral de gerenciamento
	 * */
	public static void menuPrincipal() {
		pularLinhas();
		System.out.println("----------------------------------");
		System.out.println("| 1 - Gerenciar Itens            |");
		System.out.println("| 2 - Gerenciar Amigos           |");
		System.out.println("| 3 - Gerenciar Emprestimos      |");
		System.out.println("| 4 - Pesquisa Avançada de itens |");
		System.out.println("| 5 - Gerenciar WhishList        |");
		System.out.println("| 6 - Gerenciar Seriados         |");
		System.out.println("| 0 - Sair...                    |");
		System.out.println("----------------------------------");
	}

	/**
	 * Menu de gerenciamento de seriados
	 * */
	public static void menuSeriado() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Gerenciar Trilogias    |");
		System.out.println("| 2 - Gerenciar Séries       |");
		System.out.println("| 3 - Gerenciar Sagas        |");
		System.out.println("| 4 - Gerenciar DLC's        |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de operações com trilogias
	 * */
	public static void menuTrilogia() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Adicionar Trilogia     |");
		System.out.println("| 2 - Editar Trilogia        |");
		System.out.println("| 3 - Remover Trilogia       |");
		System.out.println("| 4 - Exibir Trilogia        |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de edição de trilogias
	 * */
	public static void menuEditarTrilogia() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Editar nome da trilogia|");
		System.out.println("| 2 - Adicionar item         |");
		System.out.println("| 3 - Editar item            |");
		System.out.println("| 4 - Remover item           |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de remoção de trilogias
	 * */
	public static void menuRemoverTrilogia() {
		pularLinhas();
		System.out.println("-----------------------------------");
		System.out.println("| 1 - Remover Trilogia especifica |");
		System.out.println("| 2 - Remover todas trilogias     |");
		System.out.println("| 0 - Voltar...                   |");
		System.out.println("-----------------------------------");
	}

	/**
	 * Menu de exibição de trilogias
	 * */
	public static void menuExibirTrilogia() {
		pularLinhas();
		System.out.println("-------------------------------");
		System.out.println("| 1 - Exibir Tril. especifica |");
		System.out.println("| 2 - Exibir todas trilogias  |");
		System.out.println("| 0 - Voltar...               |");
		System.out.println("-------------------------------");
	}

	/**
	 * Menu de operações com séries
	 * */
	public static void menuSerie() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Adicionar Serie        |");
		System.out.println("| 2 - Editar Serie           |");
		System.out.println("| 3 - Remover Serie          |");
		System.out.println("| 4 - Exibir Serie           |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de Edição de series
	 * */
	public static void menuEditarSerie() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Editar nome da Serie   |");
		System.out.println("| 2 - Editar quant. de itens |");
		System.out.println("| 3 - Adicionar item         |");
		System.out.println("| 4 - Editar item            |");
		System.out.println("| 5 - Remover item           |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de remoção de series
	 * */
	public static void menuRemoverSerie() {
		pularLinhas();
		System.out.println("--------------------------------");
		System.out.println("| 1 - Remover Serie especifica |");
		System.out.println("| 2 - Remover todas series     |");
		System.out.println("| 0 - Voltar...                |");
		System.out.println("--------------------------------");
	}

	/**
	 * Menu de exibição de series
	 * */
	public static void menuExibirSerie() {
		pularLinhas();
		System.out.println("-------------------------------");
		System.out.println("| 1 - Exibir serie especifica |");
		System.out.println("| 2 - Exibir todas series     |");
		System.out.println("| 0 - Voltar...               |");
		System.out.println("-------------------------------");
	}

	/**
	 * Menu geral de saga
	 * */
	public static void menuSaga() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Adicionar Saga         |");
		System.out.println("| 2 - Editar Saga            |");
		System.out.println("| 3 - Remover Saga           |");
		System.out.println("| 4 - Exibir Saga            |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de edição de saga
	 * */
	public static void menuEditarSaga() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Editar nome da Saga    |");
		System.out.println("| 2 - Editar quant. de itens |");
		System.out.println("| 3 - Adicionar item         |");
		System.out.println("| 4 - Editar item            |");
		System.out.println("| 5 - Remover item           |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu de remoção de saga
	 * */
	public static void menuRemoverSaga() {
		pularLinhas();
		System.out.println("-------------------------------");
		System.out.println("| 1 - Remover Saga especifica |");
		System.out.println("| 2 - Remover todas sagas     |");
		System.out.println("| 0 - Voltar...               |");
		System.out.println("-------------------------------");
	}

	/**
	 * Menu de exibição de saga
	 * */
	public static void menuExibirSaga() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Exibir saga especifica |");
		System.out.println("| 2 - Exibir todas sagas     |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu geral de DLC
	 * */
	public static void menuDlc() {
		pularLinhas();
		System.out.println("------------------------");
		System.out.println("| 1 - Adicionar DLC    |");
		System.out.println("| 2 - Remover DLC      |");
		System.out.println("| 3 - Exibir DLC's     |");
		System.out.println("| 0 - Voltar...        |");
		System.out.println("------------------------");
	}

	/**
	 * Menu de remoção de DLC
	 * */
	public static void menuRemoverDlc() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Remover DLC especifica |");
		System.out.println("| 2 - Remover Todas DLC's    |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}
	
	/**
	 * Menu de exibição de DLC
	 * */
	public static void menuExibirDlc() {
		pularLinhas();
		System.out.println("-----------------------------");
		System.out.println("| 1 - Exibir DLC especifica |");
		System.out.println("| 2 - Exibir Todas DLC's    |");
		System.out.println("| 0 - Voltar...             |");
		System.out.println("-----------------------------");
	}

	/**
	 * Menu de operacoes suportadas para amigos
	 * */
	public static void menuAmigo() {
		pularLinhas();
		System.out.println("-----------------------");
		System.out.println("| 1 - Adicionar Amigo |");
		System.out.println("| 2 - Editar Amigo    |");
		System.out.println("| 3 - Remover Amigo   |");
		System.out.println("| 4 - Exibir Amigos   |");
		System.out.println("| 0 - Voltar...       |");
		System.out.println("-----------------------");
	}

	/**
	 * Menu para impressao de amigos
	 * */
	public static void menuExibirAmigos() {
		pularLinhas();
		System.out.println("------------------------");
		System.out.println("| 1 - Amigo particular |");
		System.out.println("| 2 - Todos os amigos  |");
		System.out.println("| 0 - Voltar...        |");
		System.out.println("------------------------");
	}

	/**
	 * Menu de remoção de amigo
	 * */
	public static void menuRemoverAmigos() {
		pularLinhas();
		System.out.println("--------------------------------");
		System.out.println("| 1 - Remover amigo especifico |");
		System.out.println("| 2 - Remover todos amigos     |");
		System.out.println("--------------------------------");
	}

	/**
	 * Menu de operacoes suportadas para itens
	 * */
	public static void menuItem() {
		pularLinhas();
		System.out.println("------------------------------------");
		System.out.println("| 1 - Adicionar Item               |");
		System.out.println("| 2 - Editar Item                  |");
		System.out.println("| 3 - Remover Item                 |");
		System.out.println("| 4 - Exibir Itens                 |");
		System.out.println("| 5 - Relatório de Itens repetidos |");
		System.out.println("| 0 - Voltar...                    |");
		System.out.println("------------------------------------");
	}

	/**
	 * Menu para listar a opcao de itens que o acervo pode conter
	 * */
	public static void menuTipoItem() {
		pularLinhas();
		System.out.println("--------------------------");
		System.out.println("| 1 - CD/DVD             |");
		System.out.println("| 2 - HQ                 |");
		System.out.println("| 3 - JOGO DE TABULEIRO  |");
		System.out.println("| 4 - JOGO DE VIDEO GAME |");
		System.out.println("| 0 - Voltar...          |");
		System.out.println("--------------------------");
	}

	/**
	 * Menu para impressao de Itens
	 * */
	public static void menuExibirItens() {
		pularLinhas();
		System.out.println("-------------------------------------------");
		System.out.println("| 1 - Imprimir um item em particular      |");
		System.out.println("| 2 - Imprimir itens por categoria        |");
		System.out.println("| 3 - Imprimir itens por mais emprestados |");
		System.out.println("| 4 - Imprimir itens por mais importantes |");
		System.out.println("| 0 - Voltar...                           |");
		System.out.println("-------------------------------------------");
	}

	/**
	 * Menu de remoção de Item
	 * */
	public static void menuRemoverItens() {
		pularLinhas();
		System.out.println("--------------------------------");
		System.out.println("| 1 - Remover item especifico  |");
		System.out.println("| 2 - Remover todos itens      |");
		System.out.println("--------------------------------");
	}

	/**
	 * Menu geral da WishList
	 * */
	public static void menuWish() {
		pularLinhas();
		System.out.println("------------------------------");
		System.out.println("| 1 - Cadastrar Item         |");
		System.out.println("| 2 - Adquirir Item          |");
		System.out.println("| 3 - Remover Item           |");
		System.out.println("| 4 - Visualizar WhishList   |");
		System.out.println("| 0 - Voltar                 |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu geral de pesquisa
	 * */
	public static void menuPesquisa() {
		pularLinhas();
		System.out.println("-----------------------------------------");
		System.out.println("| 1 - Listagem completa de itens        |");
		System.out.println("| 2 - Pesquisar por um determinado item |");
		System.out.println("| 0 - Voltar...                         |");
		System.out.println("-----------------------------------------");
	}

	/**
	 * Menu para emprestimos de Itens
	 * */
	public static void menuEmprestimo() {
		pularLinhas();
		System.out.println("--------------------------------------");
		System.out.println("| 1 - Emprestar                      |");
		System.out.println("| 2 - Receber                        |");
		System.out.println("| 3 - Relatório de itens emprestados |");
		System.out.println("| 0 - Voltar...                      |");
		System.out.println("--------------------------------------");
	}

	/**
	 * Filtros que podem ser utilizados na pesquisa de um jogo de tabuleiro
	 * */
	public static void jogoDeTabuleiroFiltros() {
		System.out.println("----------------");
		System.out.println("| 1 - Título   |");
		System.out.println("| 2 - Estado   |");
		System.out.println("| 3 - Preço    |");
		System.out.println("| 0 - Voltar...|");
		System.out.println("----------------");
	}

	/**
	 * Filtros que podem ser utilizados na pesquisa de um DVD/CD
	 * */
	public static void dvdCdFiltros() {
		System.out.println("----------------");
		System.out.println("| 1 - Título   |");
		System.out.println("| 2 - Estado   |");
		System.out.println("| 3 - Preço    |");
		System.out.println("| 4 - Marca    |");
		System.out.println("| 5 - Conteúdo |");
		System.out.println("| 0 - Voltar...|");
		System.out.println("----------------");
	}

	/**
	 * Filtros que podem ser utilizados na pesquisa de um JogoDeVideoGame
	 * */
	public static void jogoDeVideoGameFiltros() {
		System.out.println("------------------------------");
		System.out.println("| 1 - Título                 |");
		System.out.println("| 2 - Estado                 |");
		System.out.println("| 3 - Preço                  |");
		System.out.println("| 4 - Console                |");
		System.out.println("| - - DLC                    |");
		System.out.println("| 5 - Título da DLC          |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Filtros que podem ser utilizados na pesquisa de um Hq
	 * */
	public static void hqFiltros() {
		System.out.println("------------------------------");
		System.out.println("| 1 - Título                 |");
		System.out.println("| 2 - Estado                 |");
		System.out.println("| 3 - Preço                  |");
		System.out.println("| 4 - Número                 |");
		System.out.println("| 5 - Editora                |");
		System.out.println("| 6 - Universo               |");
		System.out.println("| - - Saga                   |");
		System.out.println("| 7 - Nome da saga           |");
		System.out.println("| 0 - Voltar...              |");
		System.out.println("------------------------------");
	}

	/**
	 * Menu para orientação antes da utilização do sistema
	 * */
	public static void menuInformativo() {
		System.out.println("--------------------------------------------------");
		System.out.println("|Para melhor peformance no uso do sistema procure|");
		System.out.println("|utilizar (,) no lugar do (;).                   |");
		System.out.println("|Evite sair do sistema bruscamente, utilize os   |");
		System.out.println("|comandos corretos para saida, para garantir que |");
		System.out.println("|as informações sejam gravadas corretamente...   |");
		System.out.println("|Bom uso!!! tecle enter para continuar!          |");
		System.out.println("--------------------------------------------------");
		in.nextLine();
	}

	/**
	 * Metodo apenas para saltar linhas quando for preciso
	 * */
	private static void pularLinhas() {
		for (int i = 0; i < LINHAS; ++i)
			System.out.println();
	}
}