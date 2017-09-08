package br.com.gerenciador.allTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.gerenciador.amizade.*;
import br.com.gerenciador.colecao.AcervoTest;
import br.com.gerenciador.colecao.itemColecionavel.*;
import br.com.gerenciador.colecao.itemColecionavel.auxiliar.RepetidoTest;
import br.com.gerenciador.colecao.itemColecionavel.dlc.*;
import br.com.gerenciador.colecao.itemColecionavel.saga.*;
import br.com.gerenciador.colecao.serie.*;
import br.com.gerenciador.colecao.trilogia.*;
import br.com.gerenciador.desejo.*;
import br.com.gerenciador.emprestimo.*;

@RunWith(Suite.class)
@SuiteClasses({ AmigoTest.class, MeusAmigosTest.class, AcervoTest.class, 
	DvdCdTest.class, HqTest.class, JogoDeTabuleiroTest.class, JogoDeVideoGameTest.class,
	SerieTest.class, RepositorioSerieTest.class, 
	TrilogiaTest.class, RepositorioTrilogiaTest.class,
	RepetidoTest.class, DlcTest.class, RepositorioDlcTest.class,
	SagaTest.class, RepositorioSagaTest.class,
	ItemDesejadoTest.class, RepositorioItensDesejadosTest.class,
	ItemEmprestadoTest.class, RepositoriosItensTest.class})
public class AllTests {

}