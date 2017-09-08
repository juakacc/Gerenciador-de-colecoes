package br.com.gerenciador.colecao.itemColecionavel.saga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.Hq;
import br.com.gerenciador.colecao.itemColecionavel.saga.Saga;

public class SagaTest {

	Saga saga;
	Hq hq1, hq2, hq3; 
	
	@Before
	public void setUp() throws Exception {
		saga = new Saga("Invasão Secreta", 3);	// saga com 3 HQs
		
		hq1 = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso",   
				true);
		hq2 = new Hq("Superman", "novo", 5.99, "nenhuma", 4, 1, "Nova", "marvel", true);
		
		hq3 = new Hq("Batman", "novo", 5.99, "nenhuma", 5, 2, "Nova", "marvel", true);
	}

	@Test
	public void testSaga() {
		assertNotNull(saga);
		
		assertEquals("Invasão Secreta", saga.getNome());
		assertEquals(3, saga.tamanhoDaSaga());
		
		/* altera pra tamanho maior */
		saga.setQuantItens(7);
		assertEquals(7, saga.tamanhoDaSaga());
		
		/* não altera pra tamanho menor */
		saga.setQuantItens(2);
		assertEquals(7, saga.tamanhoDaSaga());
	}

	@Test
	public void testEqualsObject() {
		Saga aux = new Saga("Um Dia a Mais", 3);
		
		assertFalse(saga.equals(aux));
		
		aux.setNome("Invasão secreta");
		assertTrue(saga.equals(aux));
	}

	@Test
	public void testToString() {
		StringBuilder str = new StringBuilder();
		
		str.append("-> Saga: Invasão Secreta\n");
		for (int i = 0; i < saga.tamanhoDaSaga(); i++) {
			str.append((i+1)+" => Faltando...\n");
		}
		str.append("\nFalta 3 itens para completar a saga!");
		
		assertEquals(str.toString(), saga.toString());
		
		str = new StringBuilder();
		
		saga.adicionarItemNaSaga(hq1, 1);
		saga.adicionarItemNaSaga(hq2, 2);
		saga.adicionarItemNaSaga(hq3, 3);
		
		str.append("-> Saga: Invasão Secreta\n");
		str.append("1 => " + hq1.toString() + "\n");
		str.append("2 => " + hq2.toString() + "\n");
		str.append("3 => " + hq3.toString() + "\n");
		str.append("\nA saga está completa! :D");
		
		assertEquals(str.toString(), saga.toString());
	}

	@Test
	public void testAdicionarHq() {
		assertNotNull(saga);
		
		assertTrue(saga.adicionarItemNaSaga(hq1, 1));
		assertEquals(1, saga.totalCadastrado());
		assertEquals(hq1.getSaga(), saga);
		
		/* não cadastra com número menor do que 1 */
		assertFalse(saga.adicionarItemNaSaga(hq2, 0));
		assertFalse(saga.adicionarItemNaSaga(null, 2));
		
		/* não cadastra com número já cadastrado */
		assertFalse(saga.adicionarItemNaSaga(hq2, 1));
		
		/* HQ já pertencente */
		assertFalse(saga.adicionarItemNaSaga(hq1, 2));
		
		assertTrue(saga.adicionarItemNaSaga(hq2, 2));
		assertEquals(2, saga.totalCadastrado());
		assertEquals(hq2.getSaga(), saga);
		
		assertTrue(saga.adicionarItemNaSaga(hq3, 3));
		assertEquals(3, saga.totalCadastrado());
		assertEquals(hq3.getSaga(), saga);
	}
	
	@Test
	public void testRemoverHq() {
		saga.adicionarItemNaSaga(hq1, 1);
		saga.adicionarItemNaSaga(hq2, 2);
		saga.adicionarItemNaSaga(hq3, 3);
		
		assertEquals(3, saga.totalCadastrado());
		
		assertTrue(saga.removerItemSaga(hq1));
		assertEquals(2, saga.totalCadastrado());
		assertEquals(hq1.getSaga(), null);
		
		/* não existente */
		assertFalse(saga.removerItemSaga(null));
		assertFalse(saga.removerItemSaga(hq1));
		
		assertTrue(saga.removerItemSaga(hq2));
		assertEquals(1, saga.totalCadastrado());
		assertEquals(hq2.getSaga(), null);
		
		assertTrue(saga.removerItemSaga(hq3));
		assertEquals(0, saga.totalCadastrado());
		assertEquals(hq3.getSaga(), null);
	}
	
	@Test
	public void testEditarHq() {
		saga.adicionarItemNaSaga(hq1, 1);
		saga.adicionarItemNaSaga(hq3, 3);
		assertEquals(2, saga.totalCadastrado());
		
		assertTrue(saga.editarItemSaga(hq2, hq1));
		assertEquals(2, saga.totalCadastrado());
		
		assertEquals(saga, hq2.getSaga());
		assertEquals(null, hq1.getSaga());
		
		//2, 3
		assertFalse(saga.editarItemSaga(hq3, hq2));
		assertEquals(2, saga.totalCadastrado());
		
		assertEquals(saga, hq3.getSaga());
		assertEquals(saga, hq2.getSaga());
		assertEquals(null, hq1.getSaga());
	}
	
	@Test
	public void testPertence() {
		saga.adicionarItemNaSaga(hq1, 1);

		saga.adicionarItemNaSaga(hq3, 3);
		
		assertTrue(saga.pertence(hq1));
		assertFalse(saga.pertence(hq2));
		assertTrue(saga.pertence(hq3));
	}
	
	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("Invasão Secreta;");
		str.append("3");
		
		assertEquals(str.toString(), saga.imprimirParaArquivo());
	}
	
	@Test
	public void testGetNumero() {
		saga.adicionarItemNaSaga(hq1, 1);
		saga.adicionarItemNaSaga(hq2, 2);
		saga.adicionarItemNaSaga(hq3, 3);
		
		assertEquals(3, saga.getNumero(hq3));
		assertEquals(2, saga.getNumero(hq2));
		assertEquals(1, saga.getNumero(hq1));
	}
}