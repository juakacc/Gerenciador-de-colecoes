package br.com.gerenciador.emprestimo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.amizade.Amigo;
import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.colecao.itemColecionavel.saga.Saga;
import br.com.gerenciador.emprestimo.RepositorioEmprestimo;
import br.com.gerenciador.exceptions.ItemNotFoundException;

public class RepositoriosItensTest {

	RepositorioEmprestimo repositorio;
	ItemEmprestado i1, i2, i3, i4;
	
	Amigo amigo1, amigo2, amigo3, amigo4;
	Item item1, item2, item3, item4;
	
	@Before
	public void setUp() {
		repositorio = RepositorioEmprestimo.getInstancia();
		repositorio.removerTodos();
	
		amigo1 = new Amigo("João", "masculino", "primo", "rua 25 de março", 33319991);
		amigo2 = new Amigo("Maria", "feminino", "irma", "Frei Ibiapina", 1190987654);
		amigo3 = new Amigo("José", "masculino", "tio", "Sitio Aroeiras", 30028922);
		amigo4 = new Amigo("Lucia", "feminino", "neta", "Rua de cima", 50507685);
		
		item1 = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
		item2 = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true);
		item3 = new DvdCd("Racionais", "novo", 59.9, "nenhuma", 5, "somlivre", "Musicas exclusivas do Racionais", true);
		item4 = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso", true);
		
		Saga saga = new Saga("Super herois", 2);
		saga.adicionarItemNaSaga((Hq) item4, 2);
		
		i1 = new ItemEmprestado(item1, amigo1, 2);
		i2 = new ItemEmprestado(item2, amigo2, 3);
		i3 = new ItemEmprestado(item3, amigo3, 4);
		i4 = new ItemEmprestado(item4, amigo4, 5);
		
	}

	@Test
	public void testAdicionarItem() {
		assertNotNull(repositorio);
		assertTrue(repositorio.isVazio());
		
		assertTrue(repositorio.adicionarItem(i1));
		assertEquals(1, repositorio.totalDeItens());
		
		assertTrue(repositorio.adicionarItem(i2));
		assertTrue(repositorio.adicionarItem(i3));
		assertEquals(3, repositorio.totalDeItens());
		
		assertTrue(repositorio.adicionarItem(i4));
		assertEquals(4, repositorio.totalDeItens());
	}

	@Test
	public void testRemoverItem() {		
		repositorio.adicionarItem(i1);
		repositorio.adicionarItem(i2);
		repositorio.adicionarItem(i3);
		repositorio.adicionarItem(i4);
		
		assertFalse(repositorio.isVazio());
		
		i1.getItem().setEmprestado(0);
		assertTrue(repositorio.removerItem(i1));
		assertEquals(3, repositorio.totalDeItens());
		
		repositorio.removerItem(i1);		// item que não existe nos emprestados
		assertEquals(3, repositorio.totalDeItens());
		
		i2.getItem().setEmprestado(0);
		assertTrue(repositorio.removerItem(i2));
		assertEquals(2, repositorio.totalDeItens());
		
		i3.getItem().setEmprestado(0);
		assertTrue(repositorio.removerItem(i3));
		assertEquals(1, repositorio.totalDeItens());
		
		i4.getItem().setEmprestado(0);
		assertTrue(repositorio.removerItem(i4));
		assertEquals(0, repositorio.totalDeItens());
	}

	@Test
	public void testToString() {
		repositorio.adicionarItem(i1);
		repositorio.adicionarItem(i2);
		
		StringBuilder str = new StringBuilder();
		
		str.append("\n" + amigo2.getNome() + " - pegou - ");
		str.append(item2.getTitulo() + " - emprestado e tem - 3 - dias para devolver.\n");
		
		str.append("\n" + amigo1.getNome() + " - pegou - ");
		str.append(item1.getTitulo() + " - emprestado e tem - 2 - dias para devolver.\n");
		
		assertEquals(str.toString(), repositorio.toString());
	}
	
	@Test
	public void testGets() {
		repositorio.adicionarItem(i1);
		repositorio.adicionarItem(i2);
		repositorio.adicionarItem(i3);
		repositorio.adicionarItem(i4);
		
		List<ItemEmprestado> aux = new ArrayList<>(Arrays.asList(i1, i2, i3, i4));	
		assertTrue(aux.containsAll(repositorio.getEmprestados()));
		
		List<Item> aux2 = new ArrayList<>(Arrays.asList(item1, item2, item3, item4));
		assertTrue(aux2.containsAll(repositorio.getItensEmprestados()));
		
		aux2 = new ArrayList<>(Arrays.asList(item1));
		
		try {
			assertEquals(aux2, repositorio.getItem("dama"));
		} catch (ItemNotFoundException e) {
			fail();
		}
	}

	@Test
	public void testPertence() {
		repositorio.adicionarItem(i1);
		repositorio.adicionarItem(i2);
		repositorio.adicionarItem(i3);
		repositorio.adicionarItem(i4);
		
		item1.setEmprestado(0);
		item2.setEmprestado(0);
		assertTrue(repositorio.pertence(item1, amigo1));
		assertTrue(repositorio.pertence(item2, amigo2));
	}
}