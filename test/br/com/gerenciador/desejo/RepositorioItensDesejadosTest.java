package br.com.gerenciador.desejo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.exceptions.ItemNotFoundException;

public class RepositorioItensDesejadosTest {

	RepositorioItensDesejados itens;
	ItemDesejado i1, i2, i3;
	
	@Before
	public void setUp() {
		itens = RepositorioItensDesejados.getIntancia();
		itens.removerTodos();
		
		i1 = new ItemDesejado(
				new JogoDeTabuleiro("dama", "novo", 9.99, "nenhuma", 5), Calendar.getInstance(), "extra.com");
		
		Calendar data = Calendar.getInstance(); data.add(Calendar.MONTH, 2);
		i2 = new ItemDesejado(
				new DvdCd("Santana", "novo", 10, "n", 5, "experia", "Melodias", false), data, null);
		
		i3 = new ItemDesejado(
				new JogoDeVideoGame("Need For Speed", "novo", 50, null, 4, "ps3", false), null, "www.aliexpress.com");
	}
	
	@Test
	public void testAdicionar() {
		assertNotNull(itens);
		assertEquals(0, itens.total());
		
		assertFalse(itens.adicionarItem(null));
		
		assertTrue(itens.adicionarItem(i1));
		assertEquals(1, itens.total());
		
		assertTrue(itens.adicionarItem(i2));
		assertEquals(2, itens.total());
		
		assertTrue(itens.adicionarItem(i3));
		assertEquals(3, itens.total());
		
		assertFalse(itens.adicionarItem(i1));		// item repetido
		assertEquals(3, itens.total());
	}
	
	@Test
	public void testRemover() {
		itens.adicionarItem(i1);
		itens.adicionarItem(i2);
		itens.adicionarItem(i3);
		
		assertEquals(3, itens.total());
		
		assertTrue(itens.removerItem(i1.getItem()));
		assertEquals(2, itens.total());
		
		assertTrue(itens.removerItem(i2.getItem()));
		assertEquals(1, itens.total());
		
		assertFalse(itens.removerItem(i1.getItem()));	// não existe
		assertEquals(1, itens.total());
		
		assertTrue(itens.removerTodos());
		assertEquals(0, itens.total());
	}
	
	@Test
	public void testGets() {
		itens.adicionarItem(i1);
		itens.adicionarItem(i2);
		itens.adicionarItem(i3);
		
		List<ItemDesejado> iAux = new ArrayList<>();
		iAux.add(i1);
		iAux.add(i2);
		iAux.add(i3);
		
		assertTrue(iAux.containsAll(itens.getDesejos()));
		
		try {
			assertTrue(i1.equals(itens.getItemDesejado(i1.getItem())));
		} catch (ItemNotFoundException e) {
			fail();
		}
		
		try {
			assertFalse(i1.equals(itens.getItemDesejado(i2.getItem())));
		}catch(ItemNotFoundException e) {
			fail();
		}
		
		List<ItemDesejado> lista = null;
		try {
			lista = itens.getItem("dama");
		} catch (ItemNotFoundException e) {
			fail();
		}
		List<ItemDesejado> listaComDama = new ArrayList<>();
		listaComDama.add(i1);
		
		assertEquals(listaComDama, lista);
	}
	
	@Test
	public void testToString() {
		itens.adicionarItem(i1);
		itens.adicionarItem(i2);
		itens.adicionarItem(i3);
		
		StringBuilder str = new StringBuilder();
		
		str.append("\n" + i2.toString() + "\n");
		str.append("\n" + i3.toString() + "\n");
		str.append("\n" + i1.toString() + "\n");
		
		assertEquals(str.toString(), itens.toString());
	}

}