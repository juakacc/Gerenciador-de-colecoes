package br.com.gerenciador.colecao.itemColecionavel.auxiliar;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.colecao.itemColecionavel.auxiliares.Repetido;

public class RepetidoTest {

	Repetido repetidor;
	Item item1, item2, item3, item4;
	
	@Before
	public void setUp() throws Exception {
	
		repetidor = new Repetido();
		
		item1 = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
		item2 = new JogoDeVideoGame("nfsu2", "novo", 30, "lancamento", 5, "ps3", true);
		item3 = new DvdCd("Racionais", "novo", 59.9, "nenhuma", 5, "somlivre", "Musicas exclusivas do Racionais", true);
		item4 = new Hq("Action Comics", "usado", 1_000_000, "muito valioso", 5, 1, "Comics", "fantasioso",   
				true);
	}

	@Test
	public void testgetRepetidos() {
		
		List<Item> itens = new ArrayList<>(Arrays.asList(item1, item2, item3, item4));
		itens.add(item1);
		itens.add(item1);
		itens.add(item3);
		
		Map<Item, Integer> repetidos = repetidor.getRepetidos(itens);
		
		Map<Item, Integer> aux = new HashMap<>();
		aux.put(item1, 3);
		aux.put(item3, 2);
		
		assertEquals(aux, repetidos);
	}

}