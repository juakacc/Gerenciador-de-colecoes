package br.com.gerenciador.colecao.itemColecionavel.dlc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.JogoDeVideoGame;
import br.com.gerenciador.exceptions.DlcNotFoundException;

public class RepositorioDlcTest {

	RepositorioDlc repositorio;
	Dlc dlc1, dlc2, dlc3;
	
	@Before
	public void setUp() {
		repositorio = RepositorioDlc.getInstancia();
		repositorio.removerTodas();		// não interferir nos testes
		
		dlc1 = new Dlc("Close Quarters", "internet", 
				new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true));
		
		JogoDeVideoGame jogo = new JogoDeVideoGame("Mario", "novo", 15, "n", 5, "nitendo", true);
		
		dlc2 = new Dlc("roupas", "disco", jogo);
		
		dlc3 = new Dlc("Mais ação", "live", jogo);
	}

	@Test
	public void adicionarTest() {
		assertNotNull(repositorio);
		assertTrue(repositorio.isVazio());
		
		assertTrue(repositorio.adicionarDlc(dlc1));
		assertEquals(1, repositorio.totalDeDlcs());
		
		assertTrue(repositorio.adicionarDlc(dlc2));
		assertEquals(2, repositorio.totalDeDlcs());
		
		assertFalse(repositorio.adicionarDlc(dlc1));
		assertEquals(2, repositorio.totalDeDlcs());
		
		assertTrue(repositorio.adicionarDlc(dlc3));
		assertEquals(3, repositorio.totalDeDlcs());
	}
	
	@Test
	public void removerTest() {
		repositorio.adicionarDlc(dlc1);
		repositorio.adicionarDlc(dlc2);
		repositorio.adicionarDlc(dlc3);

		assertEquals(3, repositorio.totalDeDlcs());
		
		assertTrue(repositorio.removerDlc(dlc1));
		assertEquals(2, repositorio.totalDeDlcs());
		
		assertFalse(repositorio.removerDlc(dlc1));
		
		assertTrue(repositorio.removerTodas());
		assertTrue(repositorio.isVazio());
	}

	@Test
	public void pertenceTest() {
		repositorio.adicionarDlc(dlc1);
		repositorio.adicionarDlc(dlc3);
		
		assertTrue(repositorio.pertence(dlc1));
		assertFalse(repositorio.pertence(dlc2));
		assertTrue(repositorio.pertence(dlc3));
	}
	
	@Test
	public void getsTest() {
		repositorio.adicionarDlc(dlc1);
		repositorio.adicionarDlc(dlc2);
		repositorio.adicionarDlc(dlc3);
		
		List<Dlc> aux = new ArrayList<>();
		aux.add(dlc2);
		
		try {
			assertEquals(aux, repositorio.getDlc("roupas"));
		} catch (DlcNotFoundException e) {
			fail();
		}
		
		try {
			repositorio.getDlc("Aquela");
			fail();
		} catch (DlcNotFoundException e) {
			assertEquals("A DLC não foi encontrada!", e.getMessage());
		}
		
		aux = new ArrayList<>(Arrays.asList(dlc1, dlc2, dlc3));
		assertTrue(aux.containsAll(repositorio.getDlcs()));
	}
	
	@Test
	public void toStringTest() {
		repositorio.adicionarDlc(dlc1);
		repositorio.adicionarDlc(dlc2);
		repositorio.adicionarDlc(dlc3);
		
		StringBuilder str = new StringBuilder();
		str.append("\n" + dlc2.toString() + "\n");
		str.append("\n" + dlc3.toString() + "\n");
		str.append("\n" + dlc1.toString() + "\n");

		assertEquals(str.toString(), repositorio.toString());
	}
}