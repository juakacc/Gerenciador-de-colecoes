package br.com.gerenciador.colecao.itemColecionavel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.amizade.Amigo;
import br.com.gerenciador.colecao.itemColecionavel.DvdCd;

public class DvdCdTest {

	DvdCd cd;
	
	@Before
	public void setUp() throws Exception {
		cd = new DvdCd("Racionais", "novo", 59.9, "nenhuma", 5, "somlivre", "Musicas exclusivas do Racionais", true);
	}

	@Test
	public void testEqualsObject() {
		DvdCd novo = new DvdCd("Racionais", "novo", 59.9, "nenhuma", 5, "somlivre", "Musicas exclusivas do Racionais", true);
		
		assertTrue(cd.equals(novo));
		
		novo.setConteudo("Lançamentos");
		assertFalse(cd.equals(novo));
	}

	@Test
	public void testToString() {
		assertEquals("Titulo = Racionais, Estado = novo, Preco De Compra = R$ 59.9, "
				+ "Observacoes = nenhuma"
				+ "\nDISPONIVEL, Estrelas: *****;\nMarca = somlivre, Conteudo = Musicas exclusivas do Racionais, "
				+ "já foi assistido.", cd.toString());
		
		cd.setAmigo(new Amigo("Bina", "masculino", "avô", "Rua de cima", 99556644));
		
		assertEquals("Titulo = Racionais, Estado = novo, Preco De Compra = R$ 59.9, Observacoes = nenhuma\n"
				+ "INDISPONIVEL, Estrelas: *****, Amigo = Bina;"
				+ "\nMarca = somlivre, Conteudo = Musicas exclusivas do Racionais, "
				+ "já foi assistido.", cd.toString());
	}

	@Test
	public void testDvdCd() {
		assertNotNull(cd);
		
		assertEquals("Racionais", cd.getTitulo());
		assertEquals("novo", cd.getEstado());
		assertEquals(59.9, cd.getPrecoDeCompra(), 0);
		assertEquals("nenhuma", cd.getObservacoes());
		assertEquals("somlivre", cd.getMarca());
		assertEquals("Musicas exclusivas do Racionais", cd.getConteudo());
		assertTrue(cd.isAssistido());
		assertEquals(5, cd.getNivelDeImportancia());
		
		cd.setAssistido(false);
		assertFalse(cd.isAssistido());
		
		cd.setMarca("multilaser");
		assertEquals("multilaser", cd.getMarca());
	}
	
	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("1;Racionais;");
		str.append("novo;");
		str.append("59.9;");
		str.append("nenhuma;");
		str.append("5;");
		str.append("somlivre;");
		str.append("Musicas exclusivas do Racionais;");
		str.append("true;");
		str.append("0;NULL");
		
		assertEquals(str.toString(), cd.imprimirParaArquivo());
	}
}