package br.com.gerenciador.colecao.itemColecionavel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.JogoDeVideoGame;
import br.com.gerenciador.colecao.itemColecionavel.dlc.Dlc;

public class JogoDeVideoGameTest {

	JogoDeVideoGame jogo;
	
	@Before
	public void setUp() throws Exception {
		jogo = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true);
	}

	@Test
	public void testEqualsObject() {
		JogoDeVideoGame jogo2 = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", 
				true);
		
		Dlc dlc = new Dlc("multiplayer", "disco", jogo2);
		
		/* Com DLC e sem DLC */
		assertFalse(jogo.equals(jogo2));
		
		/* Os dois sem DLC */
		jogo2.removerDlc(dlc);
		assertTrue(jogo.equals(jogo2));
	}

	@Test
	public void testToString() {
		assertEquals("Titulo = nfsu2, Estado = novo, Preco De Compra = R$ 30.0, "
				+ "Observacoes = lancamento\nDISPONIVEL, Estrelas: *****;\n"
				+ "Console = ps3, já foi finalizado, não utiliza DLC.", jogo.toString());
		
		new Dlc("multiplayer", "spin", jogo);
		
		assertEquals("Titulo = nfsu2, Estado = novo, Preco De Compra = R$ 30.0, "
				+ "Observacoes = lancamento\nDISPONIVEL, Estrelas: *****;\n"
				+ "Console = ps3, já foi finalizado, utiliza a(s) seguinte(s) DLC(s):\n"
				+ "Título: multiplayer, Localização: spin", jogo.toString());
		
	}

	@Test
	public void testJogoDeVideoGame() {
		assertNotNull(jogo);
		
		assertEquals("nfsu2", jogo.getTitulo());
		assertEquals("novo", jogo.getEstado());
		assertEquals(30, jogo.getPrecoDeCompra(), 0);
		assertEquals("lancamento", jogo.getObservacoes());
		assertEquals("ps3", jogo.getConsole());
		assertTrue(jogo.isZerado());
		assertEquals(5, jogo.getNivelDeImportancia());
		
		jogo.setConsole("xbox360");
		assertEquals("xbox360", jogo.getConsole());
		
		Dlc dlc = new Dlc("multiplayer", "spin", jogo);
		
		List<Dlc> dlcs = new ArrayList<>(Arrays.asList(dlc));
		
		assertEquals(dlcs, jogo.getDlcs());
	}
	
	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("4;");
		str.append("nfsu2;");
		str.append("novo;");
		str.append("30.0;");
		str.append("lancamento;");
		str.append("5;");
		str.append("ps3;");
		str.append("true;");
		str.append("0;0;");
		str.append("NULL");
		
		assertEquals(str.toString(), jogo.imprimirParaArquivo());
	}

}