package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;

import java.util.List;

import br.com.gerenciador.amizade.*;
import br.com.gerenciador.colecao.*;
import br.com.gerenciador.emprestimo.ItemEmprestado;
import br.com.gerenciador.emprestimo.RepositorioEmprestimo;
import br.com.gerenciador.exceptions.AmigoNotFoundException;
import br.com.gerenciador.exceptions.ItemNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuario do sistema com relacao a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de emprestimo de itens
 * */
public class GerenciaEmprestimo implements GerenciamentoIF {
	
	private RepositorioEmprestimo emprestados;
	
	/**
	 * Constroi o gerenciamento de emprestimos, recupera as 
	 * instancias para gerenciar os emprestimos
	 * */
	public GerenciaEmprestimo() {
		this.emprestados = RepositorioEmprestimo.getInstancia();
	}
	
	/**
	 * Metodo para delegar o que deve ser feito a partir da escolha do usuario
	 * @param opcao - opcao escolhida pelo usuario
	 * */
	public void selecionar(int opcao) {
		
		switch (opcao) {
		case 1:		// Emprestar item
			try {
				Item item = GerenciaItem.recuperarItemDisponivel();
				Amigo amigo = GerenciaAmigo.recuperarAmigo();
										
				if (!emprestados.pertence(item, amigo)) {		
					
					int prazo = recuperarInt("Prazo de dias para a devolucao do item"
							+ "(0-default = 15 dias)? ");
					emprestar(item, amigo, prazo);	
				} else {
					System.out.println("Esse amigo já está com esse item...");
				}
			} catch (ItemNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (AmigoNotFoundException e1) {
				System.err.println(e1.getMessage());
			}
			break;	

		case 2:		// rebecer item
			if (emprestados.isVazio()) {
				System.out.println("Nenhum item emprestado...");
			} else {
				try {
					Item item = recuperarItemEmprestado();	 
					recolher(item);			
				} catch (ItemNotFoundException e) {
					System.err.println(e.getMessage());
				}
			}
			break;
			
		case 3:		// relatorio de itens emprestados
			emprestados.relatorioDeItens();
			break;
		case 0:		
			break;
		}
	}
	
	/**
	 * Método para recuperar um item da lista de emprestados do usuário
	 * @return O item de acordo com a escolha do usuário 
	 * */
	private Item recuperarItemEmprestado() throws ItemNotFoundException {
		String titulo;
		List<Item> itens;
		int escolha;
		
		titulo = recuperarString("Titulo do item: ");
		itens = emprestados.getItem(titulo);
		System.out.println("ID");
		
		for (int i = 0; i < itens.size(); i++) {
			System.out.println("[" +(i+1)+ "] - " + itens.get(i).toString());
		}
		System.out.println();
		
		do {
			escolha = recuperarInt("Qual o ID do item(0 - sair)? ");
			if (escolha == 0) {
				throw new ItemNotFoundException("Nenhum item escolhido!");
			}
		} while (escolha < 1 || escolha > itens.size());
		
		Item item = itens.get(escolha - 1);
		return item;
	}
	
	/**
	 * Método para emprestar um item a um determinado amigo.
	 * @param item - item que vai ser emprestado
	 * @param amigo - amigo que irá ficar com o item
	 * @param prazo - prazo de dias que o amigo tem para devolver.
	 * */
	private void emprestar(Item item, Amigo amigo, int prazo) {	
		ItemEmprestado emprestado = new ItemEmprestado(item, amigo, Math.abs(prazo));
		
		if (emprestados.adicionarItem(emprestado)) {
			System.out.println("Item emprestado...");
			System.out.println("Data prevista para entrenga: " 
						+ emprestado.getDataDaEntrega().getTime());
		}
	}
	
	/**
	 * Método para recolher um item de um determinado amigo.
	 * @param item - item a ser recolhido para o acervo
	 * */
	private void recolher(Item item) {
		Amigo amigo = item.getAmigo();
		ItemEmprestado emprestado = new ItemEmprestado(item, amigo);
		
		if (emprestados.removerItem(emprestado)) {
			System.out.println("Item recolhido...");
		}
	}

}