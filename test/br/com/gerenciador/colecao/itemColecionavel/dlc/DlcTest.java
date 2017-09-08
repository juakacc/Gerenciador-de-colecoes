package br.com.gerenciador.colecao.itemColecionavel.dlc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.JogoDeVideoGame;
import br.com.gerenciador.colecao.itemColecionavel.dlc.Dlc;

public class DlcTest {
	
	Dlc dlc;
	
	@Before
	public void setUp() throws Exception {
		dlc = new Dlc("Close Quarters", "internet", 
				new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true));
	}

	@Test
	public void testDlc() {
		assertNotNull(dlc);
		
		assertEquals("nfsu2", dlc.getJogo().getTitulo());
		assertEquals("Close Quarters", dlc.getTitulo());
		assertEquals("internet", dlc.getLocalizacao());
	}

	@Test
	public void testToString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Título: Close Quarters, ");
		str.append("Localização: internet\n");
		str.append("Jogo que possui: nfsu2");
		
		assertEquals(str.toString(), dlc.toString());
	}

	@Test
	public void testEqualsObject() {
		JogoDeVideoGame jogo = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true);
		Dlc nova = new Dlc("Close Quarters", "internet", jogo);
		
		assertTrue(dlc.equals(nova));
		assertEquals(dlc.hashCode(), nova.hashCode());
		
		nova = new Dlc("Close Quarters", "spn", jogo);
		assertFalse(dlc.equals(nova));
	}

	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("Close Quarters;");
		str.append("internet;");
		
		assertEquals(str.toString(), dlc.imprimirEmArquivo());
	}
}