package br.com.gerenciador.colecao.itemColecionavel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.amizade.Amigo;
import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.JogoDeTabuleiro;
import br.com.gerenciador.colecao.trilogia.Trilogia;

public class JogoDeTabuleiroTest {

	JogoDeTabuleiro jogo;
	
	@Before
	public void setUp() throws Exception {
		jogo = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
	}

	@Test
	public void testToString() {
		assertEquals("Titulo = Dama, Estado = novo, Preco De Compra = R$ 9.99, "
				+ "Observacoes = nenhuma\nDISPONIVEL, "
				+ "Estrelas: *****;", jogo.toString());
	}

	@Test
	public void testGetsESets() {
		assertEquals("Dama", jogo.getTitulo());
		assertEquals("novo", jogo.getEstado());
		assertEquals(9.99, jogo.getPrecoDeCompra(), 0);
		assertEquals("nenhuma", jogo.getObservacoes());
		assertEquals(5, jogo.getNivelDeImportancia());
		
		jogo.setTitulo("DamaNova");
		assertEquals("DamaNova", jogo.getTitulo());
		
		jogo.setAmigo(new Amigo("Patrick", "Masculino", "Primo", "Rua Treze de maio", 99540871));
		assertFalse(jogo.isDisponivel());
		
		Trilogia trilogia = new Trilogia("tabuleiros", Item.TABULEIRO);
		jogo.setSeriado(trilogia);
		assertFalse(jogo.getSeriado().equals(null));
		assertEquals(trilogia, jogo.getSeriado());
	}

	@Test
	public void testEqualsObject() {
		JogoDeTabuleiro jogo2 = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
		assertTrue(jogo.equals(jogo2));	
		
		jogo2.setTitulo("DamaModificada");
		assertFalse(jogo.equals(jogo2));	
	}

	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("3;Dama;");
		str.append("novo;");
		str.append("9.99;");
		str.append("nenhuma;");
		str.append("5;");
		str.append("0;NULL");
		
		assertEquals(str.toString(), jogo.imprimirParaArquivo());
	}
}