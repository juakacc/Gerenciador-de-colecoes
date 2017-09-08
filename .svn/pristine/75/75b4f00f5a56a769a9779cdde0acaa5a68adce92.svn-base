package br.com.gerenciador.colecao.trilogia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.JogoDeTabuleiro;
import br.com.gerenciador.colecao.itemColecionavel.JogoDeVideoGame;

public class TrilogiaTest {

	Trilogia trilogia;
	JogoDeTabuleiro jogo1, jogo2, jogo3;
	
	@Before
	public void setUp() throws Exception {
		trilogia = new Trilogia("Tabula", Item.TABULEIRO);
		jogo1 = new JogoDeTabuleiro("dama", "novo", 9, null, 5);
		jogo2 = new JogoDeTabuleiro("xadrez", "novo", 15.99, null, 3);
		jogo3 = new JogoDeTabuleiro("sudoku", "usado", 3, "rabiscado", 4);
	}
	
	@Test
	public void testAdicionarItem() {
		assertNotNull(trilogia);
		assertEquals(0, trilogia.totalCadastrados());
		
		trilogia.adicionarItem(jogo1);
		assertEquals(1, trilogia.totalCadastrados());
		assertEquals(trilogia, jogo1.getSeriado());
		
		/* tipo diferente não adiciona */
		JogoDeVideoGame novoJogo = new JogoDeVideoGame("Need For Speed", "novo", 50, null, 4, "ps3", false);
		assertFalse(trilogia.adicionarItem(novoJogo));
		assertEquals(1, trilogia.totalCadastrados());
		
		trilogia.adicionarItem(jogo2);
		assertEquals(2, trilogia.totalCadastrados());
		assertEquals(trilogia, jogo2.getSeriado());
		
		/* Item repetido não adiciona */
		assertFalse(trilogia.adicionarItem(jogo2));
		assertEquals(2, trilogia.totalCadastrados());
		
		assertTrue(trilogia.adicionarItem(jogo3));
		assertEquals(3, trilogia.totalCadastrados());
		assertEquals(trilogia, jogo3.getSeriado());
	}
	
	@Test
	public void testRemoverItem() {
		trilogia.adicionarItem(jogo1);
		trilogia.adicionarItem(jogo2);
		trilogia.adicionarItem(jogo3);
		
		assertEquals(3, trilogia.totalCadastrados());
		
		trilogia.removerItem(jogo1);
		assertEquals(2, trilogia.totalCadastrados());
		assertEquals(null, jogo1.getSeriado());
		
		trilogia.removerItem(jogo2);
		assertEquals(1, trilogia.totalCadastrados());
		assertEquals(null, jogo2.getSeriado());
		
		assertFalse(trilogia.removerItem(jogo1));	// não existe...
		assertEquals(1, trilogia.totalCadastrados());
		
		trilogia.removerItem(jogo3);
		assertEquals(0, trilogia.totalCadastrados());
		assertEquals(null, jogo3.getSeriado());
	}
	
	@Test
	public void testEditarItem() {
		trilogia.adicionarItem(jogo1);
		trilogia.adicionarItem(jogo2);
		
		assertEquals(2, trilogia.totalCadastrados());
		
		assertTrue(trilogia.editarItem(jogo3, jogo1));
		assertFalse(trilogia.editarItem(jogo2, jogo3));
		
		JogoDeVideoGame novoJogo = new JogoDeVideoGame("Need For Speed", "novo", 50, null, 4, "ps3", false);
		assertFalse(trilogia.editarItem(novoJogo, jogo3));
	}
	
	@Test
	public void testGets() {
		trilogia.adicionarItem(jogo1);
		trilogia.adicionarItem(jogo2);
		trilogia.adicionarItem(jogo3);
		
		assertEquals(3, trilogia.totalCadastrados());
		assertEquals(Item.TABULEIRO, trilogia.getTipo());
		assertEquals("Tabula", trilogia.getNome());
		
		List<Item> itens = new ArrayList<>();
		itens.add(jogo1);
		itens.add(jogo2);
		itens.add(jogo3);
		
		assertTrue(itens.containsAll(trilogia.getItens()));
	}
	
	@Test
	public void testEquals() {
		Trilogia outra = new Trilogia("Tabula", Item.TABULEIRO);
		
		assertTrue(trilogia.equals(outra));
		assertEquals(outra.hashCode(), trilogia.hashCode());
		
		outra = new Trilogia("Tabula", Item.HQ);
		assertFalse(trilogia.equals(outra));
	}
	
	@Test
	public void testToString() {
		trilogia.adicionarItem(jogo1);
		trilogia.adicionarItem(jogo2);
		trilogia.adicionarItem(jogo3);
		StringBuilder str = new StringBuilder();
		
		str.append("-> Trilogia: Tabula\n");
		str.append(jogo3.toString() + "\n");
		str.append(jogo2.toString() + "\n");
		str.append(jogo1.toString() + "\n\n");
		str.append("Trilogia completa! :D");
		
		assertEquals(str.toString(), trilogia.toString());
	}
	
	@Test
	public void testImprimirArquivo() {
		StringBuilder str = new StringBuilder();
		
		str.append("3;");
		str.append("Tabula");
		
		assertEquals(str.toString(), trilogia.imprimirParaArquivo());
	}
	
	@Test
	public void testPertence() {
		trilogia.adicionarItem(jogo1);
		trilogia.adicionarItem(jogo3);
		
		assertTrue(trilogia.pertence(jogo1));
		assertTrue(trilogia.pertence(jogo3));

		assertFalse(trilogia.pertence(jogo2));
	}
}