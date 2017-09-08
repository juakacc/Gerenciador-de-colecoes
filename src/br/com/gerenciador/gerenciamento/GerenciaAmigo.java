package br.com.gerenciador.gerenciamento;

import static br.com.gerenciador.gerenciamento.auxiliares.Menu.*;
import static br.com.gerenciador.gerenciamento.auxiliares.Valida.*;

import java.util.List;

import br.com.gerenciador.amizade.*;
import br.com.gerenciador.exceptions.AmigoNotFoundException;
import br.com.gerenciador.interfaces.GerenciamentoIF;

/**
 * Classe para interar com o usuario do sistema com relacao a atividades 
 * que sejam relacionadas diretamente com o gerenciamento de amigos
 * */
public class GerenciaAmigo implements GerenciamentoIF {

	private static MeusAmigos meusAmigos;
	
	/**
	 * Constrói a lista de amigos do usuario
	 * */
	public GerenciaAmigo() {
		meusAmigos = MeusAmigos.getInstancia();
	}
	
	/**
	 * Método para delegar o que deve ser feito a partir da escolha do usuário
	 * @param opcao - opção escolhida pelo usuario
	 * */
	public void selecionar(int opcao)  {
		Amigo amigo;
		
		switch (opcao){
		case 1:		// adicionar amigo
			amigo = criarAmigo();			
			if (meusAmigos.adicionarAmigo(amigo))
				System.out.println("Amigo adicionado...");
			break;
			
		case 2:		//editar amigo
			if (meusAmigos.isVazio()) {
				System.out.println("Nenhum amigo cadastrado... Realize um cadastro!");
			} else {
				try {
					amigo = recuperarAmigo();
					System.out.println("Se desejar manter a característica antiga press enter no campo"
							+ "\nNovas informações: ");
					editarAmigo(amigo);	
				} catch (AmigoNotFoundException e) {
					System.err.println(e.getMessage());
				}
			}
			break;
			
		case 3:		// remover amigo
			if (meusAmigos.isVazio()) {
				System.out.println("Nenhum amigo cadastrado... Realize um cadastro!");
			} else {
				menuRemoverAmigos();
				do {
					opcao = recuperarInt("O que deseja fazer (0,1,2)? ");
					if (opcao == 0) {
						break;
					} else if (opcao == 1 || opcao == 2) {
						removerAmigos(opcao);
						break;
					} else {
						System.out.println(ENTRADA_INVALIDA);
					}
				} while (true);
			}
			break;
			
		case 4:		// exibir lista de amigos
			int escolha;
			do {
				menuExibirAmigos();
				escolha = recuperarInt("O que deseja fazer(0,1,2)? ");
				
				if (escolha == 0) {
					break;
				} else if (escolha == 1 || escolha == 2) {
					imprimirAmigos(escolha);
					break;
				} else {
					System.out.println(ENTRADA_INVALIDA);
				}
			} while (true);
			
			break;
		case 0:		//voltar
			break;
		}
	}
	
	/**
	 * Gerencia a remoção de amigo(s) 
	 * @param opcao - método de remoção (1||2)
	 * */
	private void removerAmigos(int opcao) {
		
		if (opcao == 1) {
			Amigo amigo;
			try {
				amigo = recuperarAmigo();
				
				if (!amigo.getItensEmprestados().isEmpty()) {
					if (!recuperarBoolean("Esse amigos está com itens emprestados! "
							+ "Deseja recolher os itens e removê-lo (1-sim/2-não)? ")) {
						return;
					}
				} 
				if (meusAmigos.removerAmigo(amigo))
					System.out.println("Amigo removido...");
			
			} catch (AmigoNotFoundException e) {
				System.err.println(e.getMessage());
			}
		} else {
			if (meusAmigos.removerTodos()) {
				System.out.println("Amigos removidos...");
			}
		}
	}
	
	/**
	 * Auxiliar na impressão de amigos
	 * @param opcao - opção escolhida pelo usuario (1||2)
	 * */
	private void imprimirAmigos(int opcao) {
		if (opcao == 1) {
			String nome;
			nome = recuperarString("Nome do amigo: ");
			meusAmigos.exibirAmigo(nome);
		} else {
			meusAmigos.exibirAmigos();
		}
	}
	
	/**
	 * Cria e retorna um novo amigo
	 * @return Amigo
	 * */
	private Amigo criarAmigo() {
		String nome = "", endereco = "", parentesco = "", sexo = "";
		long telefone;
		
		nome = recuperarString("Informar nome....................: ");
		sexo = recuperarString("Informar sexo....................: ");		
		parentesco = recuperarString("Informar parentesco..............: ");
		endereco = recuperarString("Informar endereço................: ");
		telefone = recuperarLong("Informar telefone(Apenas números): ");
		
		return new Amigo(nome, sexo, parentesco, endereco, telefone);
	}
	
	/**
	 * Edita as informações de um amigo cadastrado
	 * @param amigo - amigo a ser editado
	 * */
	private void editarAmigo(Amigo amigo) {
		
		String nome = recuperarString("Informar nome....................: ", true);
		if (!nome.trim().equals("")) {
			amigo.setNome(nome);
		}
		String sexo = recuperarString("Informar sexo....................: ", true);
		if (!sexo.trim().equals("")) {
			amigo.setSexo(sexo);
		}
		String parentesco = recuperarString("Informar parentesco..............: ", true);
		if (!parentesco.trim().equals("")) {
			amigo.setParentesco(parentesco);
		}
		String endereco = recuperarString("Informar endereço................: ", true);
		if (!endereco.trim().equals("")) {
			amigo.setEndereco(endereco);
		}
		long telefone = recuperarLong("Informar telefone(Apenas números): ", true);
		if (telefone != -1) {
			amigo.setTelefone(telefone);
		}
	}
	
	/**
	 * Recupera um amigo da lista de amigos do usuário.
	 * @return Amigo escolhido pelo usuário
	 * @throws AmigoNotFoundException caso o usuário não escolha nenhum amigo 
	 * dentre os exibidos
	 * */
	protected static Amigo recuperarAmigo() throws AmigoNotFoundException {
		String nome;
		List<Amigo> listaAmigos;
		int escolha;
		
		nome = recuperarString("Nome do amigo: ");
		listaAmigos = meusAmigos.getAmigo(nome);
		System.out.println("\n ID");
		for (int i = 0; i < listaAmigos.size(); i++) {
			System.out.println("[" +(i+1)+ "] - " + listaAmigos.get(i).toString());
		}
		System.out.println("-----------------------------");
		do {
			escolha = recuperarInt("Qual ID do amigo(0 - sair)? ");
			if (escolha == 0) {
				throw new AmigoNotFoundException("Nenhum amigo escolhido!");
			}
		} while (escolha < 1 || escolha > listaAmigos.size());
		
		Amigo amigo = listaAmigos.get(escolha - 1);
		return amigo;
	}
	
}