package br.com.gerenciador.colecao.itemColecionavel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.Hq;
import br.com.gerenciador.colecao.itemColecionavel.saga.Saga;

public class HqTest {

	Hq hq;
	Saga saga;
	
	@Before
	public void setUp() throws Exception {
		hq = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso",   
				true);
		
		saga = new Saga("Super herois", 3);
	}

	@Test
	public void testHq() {
		assertEquals("Action Comics", hq.getTitulo());
		assertEquals(1, hq.getNumero());
		assertEquals("Comics", hq.getEditora());
		assertEquals("fantasioso", hq.getUniverso());
		assertEquals("usado", hq.getEstado());
		assertEquals(1000000, hq.getPrecoDeCompra(), 0);
		assertEquals("muito valioso", hq.getObservacoes());
		assertTrue(hq.isLido());
		assertEquals(5, hq.getNivelDeImportancia());
		
		hq.setUniverso("aquele");
		assertEquals("aquele", hq.getUniverso());

		hq.setSaga(saga);
		
		assertEquals(new Saga("super herois"), hq.getSaga());
	}
	
	@Test
	public void testEqualsObject() {
		saga.adicionarItemNaSaga(hq, 2);
		
		Hq novoHq = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso",
				true);
		
		assertFalse(hq.equals(novoHq));
		
		Saga saga = new Saga("Super herois", 3);
		saga.adicionarItemNaSaga(novoHq, 2);
		
		assertTrue(hq.equals(novoHq));	
		
		novoHq.setEstado("novo");
		assertFalse(hq.equals(novoHq));
	}

	@Test
	public void testToString() {
		saga.adicionarItemNaSaga(hq, 2);
		
		assertEquals("Titulo = Action Comics, Estado = usado, Preco De Compra = R$ 1000000.0, "
				+ "Observacoes = muito valioso\nDISPONIVEL, Estrelas: *****;"
				+ "\nNumero = 1, Editora = Comics, Universo = fantasioso, já foi lido, "
				+ "Saga: Super herois, Numero Na Saga: 2", hq.toString());
	}
	
	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("2;Action Comics;");
		str.append("usado;");
		str.append("1000000.0;");
		str.append("muito valioso;");
		str.append("5;1;");
		str.append("Comics;");
		str.append("fantasioso;");
		str.append("true;");
		str.append("NULL;");
		str.append("0;NULL");
		
		assertEquals(str.toString(), hq.imprimirParaArquivo());
	}
}