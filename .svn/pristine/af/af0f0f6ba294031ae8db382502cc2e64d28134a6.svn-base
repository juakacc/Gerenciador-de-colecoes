package br.com.gerenciador.colecao.itemColecionavel.saga;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.itemColecionavel.Hq;
import br.com.gerenciador.exceptions.SagaNotFoundException;

public class RepositorioSagaTest {

	RepositorioSaga repositorio;
	Saga saga1, saga2, saga3;
	
	@Before
	public void setUp() {
		repositorio = RepositorioSaga.getInstancia();
		repositorio.removerTodas();
		
		
		saga1 = new Saga("Invasão Secreta", 3);	
		
		saga1.adicionarItemNaSaga(
				new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso", true), 1);
		saga1.adicionarItemNaSaga(
				new Hq("Superman", "novo", 5.99, "nenhuma", 4, 1, "Nova", "marvel", true), 2);
		saga1.adicionarItemNaSaga(
				new Hq("Batman", "novo", 5.99, "nenhuma", 5, 2, "Nova", "marvel", true), 3);
		
		
		saga2 = new Saga("Crepusculo", 4);
		
		saga2.adicionarItemNaSaga(
				new Hq("Crepusculo1", "novo", 10, "n", 5, 1, "Nancy", "fantasia", true), 1);
		saga2.adicionarItemNaSaga(
				new Hq("Crepusculo2", "novo", 15, "n", 5, 2, "Nancy", "fantasia", true), 2);
		
		
		saga3 = new Saga("Senhor dos aneis", 3);
		
		saga3.adicionarItemNaSaga(
				new Hq("Senhor dos aneis1", "novo", 9, "n", 5, 1, "nova", "nectex", true), 1);
		saga3.adicionarItemNaSaga(
				new Hq("Senhor dos aneis2", "novo", 9, "n", 5, 1, "nova", "nectex", true), 2);
	}

	@Test
	public void testAdicionar() {
		assertNotNull(repositorio);
		
		assertTrue(repositorio.isVazio());
		assertFalse(repositorio.adicionarSaga(null));
		
		assertTrue(repositorio.adicionarSaga(saga1));
		assertEquals(1, repositorio.totalSagas());
		
		/* saga já cadastrada */
		assertFalse(repositorio.adicionarSaga(saga1));
		
		assertTrue(repositorio.adicionarSaga(saga2));
		assertEquals(2, repositorio.totalSagas());
		
		assertTrue(repositorio.adicionarSaga(saga3));
		assertEquals(3, repositorio.totalSagas());
	}

	@Test
	public void testRemover() {
		repositorio.adicionarSaga(saga1);
		repositorio.adicionarSaga(saga2);
		repositorio.adicionarSaga(saga3);
		
		assertEquals(3, repositorio.totalSagas());
		
		assertTrue(repositorio.removerSaga(saga1));
		assertEquals(2, repositorio.totalSagas());
		
		assertTrue(repositorio.removerSaga(saga2));
		assertEquals(1, repositorio.totalSagas());
		
		repositorio.removerTodas();
		assertEquals(0, repositorio.totalSagas());
	}
	
	@Test
	public void testGets() {
		repositorio.adicionarSaga(saga1);
		repositorio.adicionarSaga(saga2);
		repositorio.adicionarSaga(saga3);
		
		List<Saga> aux = new ArrayList<>(Arrays.asList(saga1, saga2, saga3));
		
		assertTrue(aux.containsAll(repositorio.getSagas()));
		
		aux = new ArrayList<>();
		aux.add(saga1);
		
		try {
			assertEquals(aux, repositorio.getSaga("Invasão Secreta"));
		} catch (SagaNotFoundException e) {
			fail();
		}
		
		try {
			repositorio.getSaga("Poter");
			fail();
		} catch (SagaNotFoundException e) {
			assertEquals("Saga não encontrada!", e.getMessage());
		}
	}
	
	@Test
	public void testPertence() {
		repositorio.adicionarSaga(saga1);
		repositorio.adicionarSaga(saga3);
		
		assertTrue(repositorio.pertence(saga1));
		assertFalse(repositorio.pertence(saga2));
		assertTrue(repositorio.pertence(saga3));
	}
	
	@Test
	public void testToString() {
		repositorio.adicionarSaga(saga1);
		repositorio.adicionarSaga(saga2);
		repositorio.adicionarSaga(saga3);
		
		StringBuilder str = new StringBuilder();
		
		str.append("\n" + saga2.toString() + "\n");
		str.append("\n" + saga1.toString() + "\n");
		str.append("\n" + saga3.toString() + "\n");
		
		assertEquals(str.toString(), repositorio.toString());
	}
}