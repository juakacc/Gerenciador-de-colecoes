package br.com.gerenciador.colecao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.exceptions.ItemNotFoundException;

public class AcervoTest {

	Acervo acervo;
	Item item1, item2, item3, item4;
	
	@Before
	public void setUp() throws Exception {
		acervo = Acervo.getInstancia();
		acervo.removerTodos();		// limpa o acervo para o proximo teste
		item1 = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
		item2 = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true);
		item3 = new DvdCd("Racionais", "novo", 59.9, "nenhuma", 5, "somlivre", "Musicas exclusivas do Racionais", true);
		item4 = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso",   
				true);
	}

	@Test
	public void testAdicionarItem() {
		assertNotNull(acervo);
		assertTrue(acervo.disponiveisIsVazio());
		
		acervo.adicionarItem(item1);
		assertEquals(1, acervo.totalDeItens());
		
		acervo.adicionarItem(item2);
		acervo.adicionarItem(item3);
		assertEquals(3, acervo.totalDeItens());
		
		acervo.adicionarItem(item4);
		assertEquals(4, acervo.totalDeItens());	
		
		assertFalse(acervo.adicionarItem(null));
	}

	@Test
	public void testRemoverItem() {
		acervo.adicionarItem(item1);
		acervo.adicionarItem(item2);
		acervo.adicionarItem(item4);
		
		assertEquals(3, acervo.totalDeItens());
		assertTrue(acervo.removerItem(item1));
		
		assertEquals(2, acervo.totalDeItens());
		
		assertFalse(acervo.removerItem(item3));
		assertEquals(2, acervo.totalDeItens());
		
		assertTrue(acervo.removerItem(item2));
		assertEquals(1, acervo.totalDeItens());
		
		acervo.removerTodos();
		assertTrue(acervo.disponiveisIsVazio());
	}

	@Test
	public void testGetItem() {
		acervo.adicionarItem(item1);
		acervo.adicionarItem(item2);
		acervo.adicionarItem(item3);
		acervo.adicionarItem(item4);
		
		List<Item> aux = new ArrayList<>();
		aux.add(item3);
		try {			// item existente
			assertEquals(aux, acervo.getItemDisponivel("racionais"));
		} catch (ItemNotFoundException e) {
			fail();
		}
		
		try {			// item inexistente
			aux = acervo.getItemDisponivel("xadrez");
			fail();
		} catch (ItemNotFoundException e) {
			assertEquals("O item não foi encontrado!", e.getMessage());
		}
		
		aux = new ArrayList<>(Arrays.asList(item1, item2, item3, item4));
		assertEquals(aux, acervo.getTodosOsItens());
		
		assertTrue(aux.containsAll(acervo.getItensDisponiveis()));
		
		aux = new ArrayList<>(Arrays.asList(item1));
		assertEquals(aux, acervo.jogoDeTabuleiroList());
		
		aux = new ArrayList<>(Arrays.asList(item2));
		assertEquals(aux, acervo.jogoDeVideoGameList());
		
		aux = new ArrayList<>(Arrays.asList(item3));
		assertEquals(aux, acervo.dvdCdList());
		
		aux = new ArrayList<>(Arrays.asList(item4));
		assertEquals(aux, acervo.hqList());
	}

	@Test
	public void testToString() {
		acervo.adicionarItem(item1);
		acervo.adicionarItem(item2);
		acervo.adicionarItem(item3);
		acervo.adicionarItem(item4);
		
		StringBuilder s = new StringBuilder();
		s.append(item1.toString() + "\n");
		s.append(item2.toString() + "\n");
		s.append(item3.toString() + "\n");
		s.append(item4.toString() + "\n");
		
		assertEquals(s.toString(), acervo.toString());
	}
	
	@Test
	public void testPertence() {
		acervo.adicionarItem(item1);
		acervo.adicionarItem(item4);
		assertEquals(2, acervo.totalDeItens());
		
		assertTrue(acervo.pertence(item1));
		assertFalse(acervo.pertence(item2));
	}
}