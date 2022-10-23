package br.ce.wcaquino.suites;
import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.test.TesteCadastroComSucessoPageObject;
import br.ce.wcaquino.test.TesteRegrasCadastro;

// ** Aula 47 - Suite de Testes ** \\

// Aprender a executar as classes de uma só vez assim como na ordem desejada \\


// * Definindo e configurando os imports * \\
@RunWith(Suite.class)
@SuiteClasses({
	
	// Chamando as classes que serão executadas
	
	TesteCadastroComSucessoPageObject.class,
	TesteRegrasCadastro.class,
		
	
})
public class SuiteTeste {

	// * Atualização na aula 69
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}
	
}
