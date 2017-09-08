package br.com.gerenciador.desejo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.colecao.Item;
import br.com.gerenciador.colecao.itemColecionavel.*;

public class ItemDesejadoTest {

	ItemDesejado item;
	
	@Before
	public void setUp() {
		Item aux = new JogoDeTabuleiro("dama", "novo", 9.99, "nenhuma", 5);
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DAY_OF_YEAR, 10);
		
		item = new ItemDesejado(aux, data, "www.google.com");
	}

	@Test
	public void testGets() {
		assertEquals("dama", item.getItem().getTitulo());
		assertEquals(9.99, item.getItem().getPrecoDeCompra(), 0);
		
		assertEquals("www.google.com", item.getUrl());
		assertFalse(Calendar.getInstance().equals(item.getData()));
	}
	
	@Test
	public void testEquals() {
		
		ItemDesejado temp = new ItemDesejado(
				new JogoDeTabuleiro("dama", "novo", 9.99, "nenhuma", 5), null, null);
		
		assertTrue(item.equals(temp));
		assertEquals(item.hashCode(), temp.hashCode());
		
		temp = new ItemDesejado(
				new DvdCd("Santana", "novo", 10, "n", 5, "experia", "Melodias", false), null, null);
		
		assertFalse(item.equals(temp));
	}
	
	@Test
	public void testImprimirArquivo() {
		StringBuilder str = new StringBuilder();
		
		str.append(item.getItem().imprimirParaArquivo() + "\n");
		str.append(item.getData().getTimeInMillis() + "\n");
		str.append("www.google.com");
		
		assertEquals(str.toString(), item.imprimirParaArquivo());
	}
	
	@Test
	public void testToString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Título: dama, ");
		str.append("Preço de Venda: R$ 9.99");
		
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DAY_OF_YEAR, 10);
		str.append("\nData de lançamento: " + 
				(new SimpleDateFormat("dd/MM/yyyy").format(data.getTime())));
		
		str.append("\nURL: www.google.com");
		
		assertEquals(str.toString(), item.toString());
	}
}