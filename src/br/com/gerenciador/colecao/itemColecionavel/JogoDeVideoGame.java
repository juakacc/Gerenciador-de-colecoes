package br.com.gerenciador.colecao.itemColecionavel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.auxiliares.Seriado;
import br.com.gerenciador.colecao.itemColecionavel.dlc.Dlc;
import br.com.gerenciador.colecao.serie.Serie;

/**
 * Esta classe é usada para representar um jogo de video game
 * */
public class JogoDeVideoGame extends Item {

	private String console;
	private boolean zerado;	
	private List<Dlc> listaDeDlc;
	
	/**
	 * Constroi um jogo de video game e atribui os valores informados como parametro
	 * @param titulo - titulo do jogo
	 * @param estado - estado em que o jogo se encontra
	 * @param preco - preco de compra do jogo
	 * @param observacoes - alguma observação sobre o jogo caso exista
	 * @param nivel - nivel de importancia para o usuario
	 * @param console - console do jogo
	 * @param zerado - se o jogo ja foi finalizado
	 * */
	public JogoDeVideoGame(String titulo, String estado, double preco, 
			String observacoes, int nivel, String console, boolean zerado) {
		
		super(titulo, estado, preco, observacoes, nivel);
		this.console = console;
		this.zerado = zerado;
		this.listaDeDlc = new ArrayList<>();
	}

	/**
	 * @return o código de hash de um jogo de video game
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((console == null) ? 0 : console.hashCode());
		result = prime * result
				+ ((listaDeDlc == null) ? 0 : listaDeDlc.hashCode());
		result = prime * result + (zerado ? 1231 : 1237);
		return result;
	}

	/**
	* Testa a igualdade de um objeto com este Jogo de video game. 
	* @param obj - O objeto a comparar com este itme.
	* @return true se o objeto for igual ou false caso contrário.
	*/	
	@Override
	public boolean equals(Object obj) {
		
		if (getClass() != obj.getClass())
			return false;
		
		JogoDeVideoGame other = (JogoDeVideoGame) obj;
		
		return super.equals(other) &&
				this.getConsole().equalsIgnoreCase(other.getConsole()) &&
				(this.isZerado() == other.isZerado()) &&
				this.getDlcs().equals(other.getDlcs());
	}

	/**
	 * Representa um Jogo de Video Game como String
	 * @return String de representação
	 * */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append(super.toString());
		s.append("\nConsole = " + getConsole());
		s.append(isZerado() ? ", já foi finalizado" : ", ainda não finalizado");
		
		if (listaDeDlc.isEmpty()) {
			s.append(", não utiliza DLC.");
		} else {
			s.append(", utiliza a(s) seguinte(s) DLC(s):\n");
			Iterator<Dlc> dlcI = listaDeDlc.iterator();
			
			while (dlcI.hasNext()) {
				Dlc next = dlcI.next();
				s.append("Título: " + next.getTitulo() + ", Localização: " + next.getLocalizacao());
				if (dlcI.hasNext()) {
					s.append("\n");
				}
			}
		}
		return s.toString();
	}
	
	/**
	 * Método que retorna uma String para auxiliar na persistencia
	 * de dados.
	 * @return A string para gravação
	 * */
	@Override
	public String imprimirParaArquivo() {
		
		StringBuilder s = new StringBuilder();
		s.append("4;");
		s.append(super.imprimirParaArquivo() + ";");
		s.append(getConsole() + ";");
		s.append(isZerado() + ";");
		
		s.append(listaDeDlc.size() + ";");			// se for 0 ele não ler mais nada...
		
		for (int i = 0; i < listaDeDlc.size(); i++) {
			s.append(listaDeDlc.get(i).imprimirEmArquivo());
		}
		
		s.append(getVezesEmprestado() + ";");
		Seriado serie = getSeriado();
		s.append(serie == null ? "NULL" : serie.getNome() + ";" + ((serie instanceof Serie) ? 1 : 2));
		
		return s.toString();
	}
	
	/**
	 * Retorna o console ao que o jogo pertence
	 * @return String console
	 * */
	public String getConsole() {
		return console;
	}
	
	/**
	 * @param console the console to set
	 */
	public void setConsole(String console) {
		this.console = console;
	}
	
	/**
	 * Retorna o estado de finalizacao do jogo
	 * @return true se o jogo ja foi zerado ou false caso contrario
	 * */
	public boolean isZerado() {
		return zerado;
	}
	
	/**
	 * @param zerado the zerado to set
	 */
	public void setZerado(boolean zerado) {
		this.zerado = zerado;
	}
	
	/**
	 * Adiciona uma nova DLC ao jogo do usuário.
	 * @param dlc - nova DLC
	 * */
	public void adicionarDlc(Dlc dlc) {
		if (dlc != null) {
			listaDeDlc.add(dlc);
		}
	}
	
	/**
	 * Remove uma DLC do jogo do usuário.
	 * @param dlc - DLC a ser removida
	 * */
	public void removerDlc(Dlc dlc) {
		if (dlc != null) {
			listaDeDlc.remove(dlc);
		}
	}
	
	/**
	 * Recupera a lista de DLC's que esse jogo utiliza.
	 * @return A lista com as DLC's
	 * */
	public List<Dlc> getDlcs() {
		return listaDeDlc;
	}
}