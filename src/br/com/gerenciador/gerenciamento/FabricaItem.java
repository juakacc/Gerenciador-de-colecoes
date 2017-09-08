package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;
import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.colecao.itemColecionavel.dlc.*;
import br.com.gerenciador.colecao.itemColecionavel.saga.*;

/**
 * Classe para organizar o recolhimento de informações para a 
 * criação de novos itens.
 * */
public final class FabricaItem {
	
	/**
	 * Cria e retorna um novo item de acordo com o parametro recebido
	 * @param tipo - 1 <= tipo <= 4
	 * @return item
	 * */
	public static Item fabricarItem(int tipo) {
		
		switch (tipo) {
		case Item.DVDCD:	
			return criarDvdCd();
		
		case Item.HQ:
			return criarHq();
			
		case Item.TABULEIRO:	
			return criarJogoDeTabuleiro();
			
		case Item.GAME:	
			return criarJogoDeVideoGame();
		}
		return null;
	}
	
	/**
	 * Recupera os dados e cria um novo jogo de tabuleiro
	 * @return jogo de tabuleiro
	 * */
	private static JogoDeTabuleiro criarJogoDeTabuleiro() {
		String titulo = "", estado = "", obs = "";
		double preco = 0;
		int nivel = 0;
		
		titulo = recuperarString("Informe titulo...................: ");
		estado = recuperarString("Informe estado...................: ");	
		preco = recuperarDouble("Informe preco de compra(ex: 5.50): ");
		obs = recuperarString("Informe alguma observacao........: ");
		
		while (nivel < 1 || nivel > 5) {
			nivel = recuperarInt("Informe qual o nivel de importancia(1, 2, 3, 4 ou 5): ");
		}
		return new JogoDeTabuleiro(titulo, estado, preco, obs, nivel);
	}
	
	/**
	 * Recupera os dados e cria um novo HQ
	 * @return HQ
	 * */
	private static Hq criarHq() {
		String titulo = "", editora = "", universo = "", estado = "", obs = "";
		int numero = 0, nivel = 0;
		double preco = 0;
		boolean lido = false;
		
		titulo = recuperarString("Informe título...................: ");
		numero = recuperarInt("Informe número...................: ");
		editora = recuperarString("Informe editora..................: ");
		universo = recuperarString("Informe universo.................: ");
		estado = recuperarString("Informe estado...................: ");		
		preco = recuperarDouble("Informe preço de compra(ex: 5.50): ");
		obs = recuperarString("Informe alguma observação........: ");
		lido = recuperarBoolean("Já leu o HQ(1-sim/2-nao)? ");
		
		while (nivel < 1 || nivel > 5) {
			nivel = recuperarInt("Informe qual o nivel de importância(1, 2, 3, 4 ou 5): ");
		}
		
		Hq hq = new Hq(titulo, estado, preco, obs, nivel, numero, editora, universo, lido);
				
		if (recuperarBoolean("Informe se pertence a uma saga (1-sim/2-não)? ")) {	
			adicionarSaga(hq);
		}
		
		return hq;
	}
	
	/**
	 * Método para adicionar uma saga a um HQ
	 * @param hq - HQ que terá uma saga cadastrada
	 * */
	public static void adicionarSaga(Hq hq) {
		RepositorioSaga sagas = RepositorioSaga.getInstancia(); 
		Saga saga;
		int numeroNaSaga;
		
		String nomeDaSaga = recuperarString("Informe nome....: ");
		saga = new Saga(nomeDaSaga);
		
		if (sagas.pertence(saga)) {		// essa saga já existe, tento adicionar nela
			saga = sagas.getSagaPorNome(nomeDaSaga);
			do {
				numeroNaSaga = recuperarInt("Informe o numero: ");
			} while (numeroNaSaga > saga.tamanhoDaSaga() || numeroNaSaga < 1);
			saga.adicionarItemNaSaga(hq, numeroNaSaga);
		
		} else {			// tenho que criar uma nova saga
			int quantidadeHq;
			do {
				quantidadeHq  = recuperarInt("Quantos HQ's essa Saga tem? ");
			} while(quantidadeHq < 1);
			
			saga = new Saga(nomeDaSaga, quantidadeHq);
			do {
				numeroNaSaga = recuperarInt("Informe o numero: ");
			} while (numeroNaSaga > saga.tamanhoDaSaga() || numeroNaSaga < 1);
			
			if (saga.adicionarItemNaSaga(hq, numeroNaSaga)) {
				if (sagas.adicionarSaga(saga)) {
					System.out.println("Nova saga cadastrada...");
				}
			}
		}
	}
	
	/**
	 * Recupera os dados e cria um novo DVD/CD
	 * @return DVD/CD
	 * */
	private static DvdCd criarDvdCd() {
		String titulo = "", estado = "", obs = "", marca = "", conteudo = "";
		boolean assistido = false;
		double preco = 0;
		int nivel = 0;
		
		titulo = recuperarString("Informe titulo...................: ");
		estado = recuperarString("Informe estado...................: ");	
		preco = recuperarDouble("Informe preco de compra(ex: 5.50): ");
		obs = recuperarString("Informe alguma observacao........: ");
		marca = recuperarString("Informe marca....................: ");
		conteudo = recuperarString("Informe conteudo.................: ");
		assistido = recuperarBoolean("Ja assistiu/escutou o DVD/CD (1-sim/2-nao)? ");
	
		while (nivel < 1 || nivel > 5) {
			nivel = recuperarInt("Informe qual o nivel de importancia(1, 2, 3, 4 ou 5): ");
		}
		return new DvdCd(titulo, estado, preco, obs, nivel, marca, conteudo, assistido);
	}
	
	/**
	 * Recupera os dados e cria um novo jogo de video game
	 * @return jogo de video game 
	 * */
	private static JogoDeVideoGame criarJogoDeVideoGame() {
		String titulo = "", estado = "", obs = "", console = "";
		double preco = 0;
		int nivel = 0;
		boolean zerado = false;
		
		titulo = recuperarString("Informe titulo...................: ");
		estado = recuperarString("Informe estado...................: ");
		preco = recuperarDouble("Informe preco de compra(ex: 5.50): ");
		
		obs = recuperarString("Informe alguma observacao........: ");
		console = recuperarString("Informe console..................: ");		
		zerado = recuperarBoolean("Esse jogo ja foi zerado(1-sim/2-nao)? ");
		
		while (nivel < 1 || nivel > 5) {
			nivel = recuperarInt("Informe qual o nivel de importancia(1, 2, 3, 4 ou 5): ");
		}
		
		JogoDeVideoGame jogo = new JogoDeVideoGame(titulo, estado, preco, obs, nivel, console, zerado);
		
		if (recuperarBoolean("Esse jogo possui uma DLC(1-sim/2-nao)? ")) {
			adicionaDlc(jogo);
		}
		
		return jogo; 	
	}
	
	/**
	 * Método para adicionar uma DLC a um Jogo de video game
	 * @param jogo - Jogo que terá uma DLC adicionada
	 * */
	public static void adicionaDlc(JogoDeVideoGame jogo) {
		RepositorioDlc minhasDlcs = RepositorioDlc.getInstancia();
		Dlc dlc;
		String tituloDlc = "", localizacao = "";
		do {
			tituloDlc = recuperarString("Informe o titulo da DLC.: ");
			localizacao = recuperarString("Informe a localizacao...: ");
			
			dlc = new Dlc(tituloDlc, localizacao, jogo);
			
			if (minhasDlcs.adicionarDlc(dlc)) {
				System.out.println("Nova DLC adicionada...");
			}
		} while (recuperarBoolean("Deseja adicionar mais alguma DLC (1-sim/2-não)? "));
	}
	
	/**
	 * Edita as caracteristicas de um Jogo de Tabuleiro, caso o usuário opte por isso
	 * @param jogo - Jogo que será editado
	 * */
	public static void editarJogoDeTabuleiro(JogoDeTabuleiro jogo) {
		recolherEmComum(jogo);
	}
	
	/**
	 * Edita as caracteristicas de um DVD/CD, caso o usuário opte por isso
	 * @param midia - DVD/CD que será editado
	 * */
	public static void editarDvdCd(DvdCd midia) {
		recolherEmComum(midia);
		
		String marca = recuperarString("Informe marca....................: ", true);
		if (!marca.trim().equals("")) {
			midia.setMarca(marca);
		}
		String conteudo = recuperarString("Informe conteudo.................: ", true);
		if (!conteudo.trim().equals("")) {
			midia.setConteudo(conteudo);
		}
		try {
			boolean assistido = recuperarBoolean("Ja assistiu/escutou o DVD/CD (1-sim/2-nao)? ", true);
			midia.setAssistido(assistido);
		} catch (Exception e) { }
	}
	
	/**
	 * Edita as caracteristicas de um Jogo de Video Game, caso o usuário opte por isso
	 * @param jogo - Jogo que será editado
	 * */
	public static void editarJogoDeVideoGame(JogoDeVideoGame jogo) {
		recolherEmComum(jogo);
		
		String console = recuperarString("Informe console..................: ", true);
		if (!console.trim().equals("")) {
			jogo.setConsole(console);
		}
		try {
			boolean zerado = recuperarBoolean("Esse jogo ja foi zerado(1-sim/2-nao)? ", true);
			jogo.setZerado(zerado);
		} catch (Exception e) { }
		
		if (recuperarBoolean("Deseja adicionar alguma DLC(1-sim/2-nao)? ")) {
			adicionaDlc(jogo);
		} 	
	}

	/**
	 * Edita as caracteristicas de um HQ, caso o usuário opte por isso
	 * @param hq - HQ que será editado
	 * */
	public static void editarHq(Hq hq) {
		recolherEmComum(hq);
		
		int numero = recuperarInt("Informe número...................: ", true);
		if (numero != -1) {
			hq.setNumero(numero);
		}
		String editora = recuperarString("Informe editora..................: ", true);
		if (!editora.trim().equals("")) {
			hq.setEditora(editora);
		}
		String universo = recuperarString("Informe universo.................: ", true);
		if (!universo.trim().equals("")) {
			hq.setUniverso(universo);
		}
		try {
			boolean lido = recuperarBoolean("Já leu o HQ(1-sim/2-nao)? ", true);
			hq.setLido(lido);
		} catch (Exception e) { }
		
		if (hq.getSaga() == null) {
			if (recuperarBoolean("Informe se pertence a uma saga (1-sim/2-não)? ")) {	
				adicionarSaga(hq);
			}
		}
	}
	
	/**
	 * Edita as informação gerais de um item, caso o usuário opte por isso
	 * @param item - Item a ser editado
	 * */
	public static void recolherEmComum(Item item) {
		String titulo = "", estado = "", obs = "";
		double preco = 0;
		int nivel = 0;
		
		titulo = recuperarString("Informe titulo...................: ", true);
		if (!titulo.trim().equals("")) {
			item.setTitulo(titulo);
		}
		estado = recuperarString("Informe estado...................: ", true);
		if (!estado.trim().equals("")) {
			item.setEstado(estado);
		}
		preco = recuperarDouble("Informe preco de compra(ex: 5.50): ", true);
		if (preco != -1) {
			item.setPreco(preco);
		}
		
		obs = recuperarString("Informe alguma observacao........: ", true);
		if (!obs.trim().equals("")) {
			item.setObservacoes(obs);
		}
		
		while (nivel < 1 || nivel > 5) {
			nivel = recuperarInt("Informe qual o nivel de importancia(1, 2, 3, 4 ou 5): ", true);
			if (nivel != -1 && nivel >= 1 && nivel <= 5) {
				item.setNivel(nivel);
				break;
			} else if (nivel == -1) {
				break;
			}
		}
	}
	
	/**
	 * Determina de qual tipo o item informa é.
	 * 1 - DVD/CD
	 * 2 - HQ
	 * 3 - Jogo de tabuleiro
	 * 4 - Jogo de video game
	 * @param item
	 * @return Número correspondente ao tipo.
	 * */
	public static int tipoItem(Item item) {
		int tipo;
		if (item instanceof DvdCd) {
			tipo = Item.DVDCD;
		} else if (item instanceof Hq) {
			tipo = Item.HQ;
		} else if (item instanceof JogoDeTabuleiro) {
			tipo = Item.TABULEIRO;
		} else {
			tipo = Item.GAME;
		}
		return tipo;
	}
}