package br.com.gerenciador.amizade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.gerenciador.amizade.Amigo;
import br.com.gerenciador.colecao.itemColecionavel.JogoDeTabuleiro;

public class AmigoTest {
	
	Amigo amigo;

	@Before
	public void setUp() {
		amigo = new Amigo("Patrick", "Masculino", "Primo", "Rua Treze de maio", 99540871);
	}
	
	@Test
	public void testAmigo() {
		
		assertNotNull(amigo);
		
		assertEquals("Patrick", amigo.getNome());
		assertEquals("Masculino", amigo.getSexo());
		assertEquals("Primo", amigo.getParentesco());
		assertEquals("Rua Treze de maio", amigo.getEndereco());
		assertEquals(99540871, amigo.getTelefone());
		
		amigo.setNome("Patrick1");
		assertEquals("Patrick1", amigo.getNome());
	}
	
	@Test
	public void testEquals(){
		
		Amigo amigo2 = new Amigo("Patrick", "Masculino", "Primo", "Rua Treze de maio", 99540871);
		
		assertTrue(amigo.equals(amigo2));
		assertEquals(amigo.hashCode(), amigo2.hashCode());
		
		assertFalse(amigo.equals(null));
		
		amigo2 = new Amigo("Patrick", "masculino", "primo", "rua quartoze de maio", 99540871);
		assertFalse(amigo.equals(amigo2));
	}
	
	@Test
	public void testImprimir() {
		StringBuilder str = new StringBuilder();
		
		str.append("Patrick;");
		str.append("Masculino;");
		str.append("Primo;");
		str.append("Rua Treze de maio;");
		str.append("99540871");
		
		assertEquals(str.toString(), amigo.imprimirParaArquivo());
	}
			
	@Test
	public void testToString() {
		
		assertEquals("Nome = Patrick, Sexo = Masculino, Parentesco = Primo"
				+ "\nEndereco = Rua Treze de maio, "
				+ "Telefone = 99540871", amigo.toString());
	}
	
	@Test
	public void testItens() {
		assertEquals(0, amigo.getItensEmprestados().size());
		
		JogoDeTabuleiro jogo = new JogoDeTabuleiro("Dama", "novo", 9.99, "nenhuma", 5);
		
		amigo.adicionarItem(jogo);
		assertEquals(1, amigo.getItensEmprestados().size());
		
		assertTrue(!jogo.isDisponivel());
		
		amigo.removerItem(jogo);
		assertEquals(0, amigo.getItensEmprestados().size());
	}
}