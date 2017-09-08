package br.com.gerenciador.amizade;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.amizade.MeusAmigos;
import br.com.gerenciador.exceptions.AmigoNotFoundException;

public class MeusAmigosTest {

	MeusAmigos amigos;
	Amigo amigo1, amigo2, amigo3, amigo4, amigo5;
	
	@Before
	public void setUp() throws Exception {
		amigos = MeusAmigos.getInstancia();
		amigos.removerTodos();		// reseta a lista de amigos do usuário
		amigo1 = new Amigo("João", "masculino", "primo", "rua 25 de março", 33319991);
		amigo2 = new Amigo("Maria", "feminino", "irma", "Frei Ibiapina", 1190987654);
		amigo3 = new Amigo("José", "masculino", "tio", "Sitio Aroeiras", 30028922);
		amigo4 = new Amigo("Lucia", "feminino", "neta", "Rua de cima", 50507685);
		amigo5 = new Amigo("Severino", "masculino", "sobrinho", "Campina Grande", 98887654);
	}

	@Test
	public void testAdicionarAmigo() {
		assertNotNull(amigos);
		assertTrue(amigos.isVazio());
		
		assertTrue(amigos.adicionarAmigo(amigo1));
		assertEquals(1, amigos.totalDeAmigos());
		
		amigos.adicionarAmigo(amigo2);
		amigos.adicionarAmigo(amigo3);
		assertEquals(3, amigos.totalDeAmigos());
		
		assertFalse(amigos.adicionarAmigo(amigo1));		// amigo repetido, nao adiciona
		assertEquals(3, amigos.totalDeAmigos());
		
		amigos.adicionarAmigo(amigo4);
		amigos.adicionarAmigo(amigo5);
		assertEquals(5, amigos.totalDeAmigos());
	}

	@Test
	public void testRemoverAmigo() {
		amigos.adicionarAmigo(amigo1);
		amigos.adicionarAmigo(amigo2);
		
		amigos.adicionarAmigo(amigo4);
		amigos.adicionarAmigo(amigo5);

		assertEquals(4, amigos.totalDeAmigos());
		
		assertTrue(amigos.removerAmigo(amigo1));		
		assertEquals(3, amigos.totalDeAmigos());
		
		assertFalse(amigos.removerAmigo(amigo3));		// amigo inexistente
		assertEquals(3, amigos.totalDeAmigos());
		
		assertTrue(amigos.removerAmigo(amigo4));		// existente		
		assertEquals(2, amigos.totalDeAmigos());
		
		amigos.removerTodos();
		assertTrue(amigos.isVazio());
	}

	@Test
	public void testPertence() {
		amigos.adicionarAmigo(amigo1);
		amigos.adicionarAmigo(amigo2);
		
		amigos.adicionarAmigo(amigo4);
		amigos.adicionarAmigo(amigo5);
		
		assertTrue(amigos.pertence(amigo1));
		
		amigos.removerAmigo(amigo2);
		assertFalse(amigos.pertence(amigo2));
		assertTrue(amigos.pertence(amigo4));
		
		assertFalse(amigos.pertence(amigo3));
	}

	@Test
	public void testGetAmigo() {
		amigos.adicionarAmigo(amigo1);
		amigos.adicionarAmigo(amigo2);
		amigos.adicionarAmigo(amigo3);
		amigos.adicionarAmigo(amigo4);
		amigos.adicionarAmigo(amigo5);
		
		List<Amigo> a = null;
		try {
			a = amigos.getAmigo("João");		// amigo cadastrado
		} catch (AmigoNotFoundException e) {
			fail();
		}
		List<Amigo> aux = new ArrayList<>();
		aux.add(amigo1);
		
		assertEquals(aux, a);
		
		try {
			a = amigos.getAmigo("Bina");		// amigo inexistente
			fail();
		} catch (AmigoNotFoundException e) {
			assertEquals("Amigo não foi encontrado!", e.getMessage());
		}
		
		try {
			a = amigos.getAmigo("Severino");		// amigo cadastrado
		} catch (AmigoNotFoundException e) {
			fail();
		}
		
		aux.clear();
		aux.add(amigo5);
		
		assertEquals(aux, a);
		
		aux = new ArrayList<>(Arrays.asList(amigo1, amigo2, amigo3, amigo4, amigo5));
		assertTrue(aux.containsAll(amigos.getAmigos()));
	}

	@Test
	public void testToString() {
		amigos.adicionarAmigo(amigo5);
		amigos.adicionarAmigo(amigo4);
		amigos.adicionarAmigo(amigo3);
		amigos.adicionarAmigo(amigo2);
		amigos.adicionarAmigo(amigo1);
	
		StringBuilder s = new StringBuilder();
		s.append("\n" + amigo1.toString() + "\n");
		s.append("\n" + amigo3.toString() + "\n");
		s.append("\n" + amigo4.toString() + "\n");
		s.append("\n" + amigo5.toString() + "\n");
		s.append("\n" + amigo2.toString() + "\n");
		
		assertEquals(s.toString(), amigos.toString());
	}
}
