package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.page.CampoTreinamentoPage;

import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.Assert;
import org.junit.Test;

public class TesteObrigatoriedadePageObject {
	
	private CampoTreinamentoPage page;
	
		@Before
		public void inicializa() {
			
			new ChromeDriver();
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
			getDriver().manage().window().maximize();
			
			
		}
		
		@After
		public void encerra() {
			
			killDriver();
		}
		
		@Test
		public void deveValidarObrigatoriedadesPageObject() {
			
			// Iteração e validar da obrigatoridade do Nome
			
			page.cadastrar();
			page.obterAlertaNome();
			
			Assert.assertEquals("Nome eh obrigatorio", page.obterAlertaNome());
			page.aceitaAlertaNomeObrigatorio();
			
			// Preenchimento do nome e iteração de validação da obrigatoridade do Sobrenome
			
			page.setNome("Washington");
			page.cadastrar();
			page.obterAlertaNome();
			
			Assert.assertEquals("Sobrenome eh obrigatorio", page.obterAlertaNome());
			page.aceitaAlertaNomeObrigatorio();
						
			// Preenchimento do Sobrenome e iteração de validação da obrigatoridade do Sexo
			
			page.setSobreNome("Almeida");
			page.cadastrar();
						
			page.obterAlertaNome();
			Assert.assertEquals("Sexo eh obrigatorio", page.obterAlertaNome());
			page.aceitaAlertaNomeObrigatorio();
						
			// Preenchimento do sexo e iteração de validação da obrigatoridade do Comida Favorita
			
			page.setGeneroMasculino();			
			page.setComidaFavoritaCarne();			
			page.setComidaFavoritaVegetariano();			
			page.cadastrar();			
			page.obterAlertaNome();
			
			Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.obterAlertaNome());
			page.aceitaAlertaNomeObrigatorio();			
			page.setComidaFavoritaCarne();
						
			// Preenchimento da Comida Favorita e iteração de validação da obrigatoridade do Esporte
			
			page.setEsporteMultiplo("Futebol","O que eh esporte?");
			page.cadastrar();			
			page.obterAlertaNome();
			
			Assert.assertEquals("Voce faz esporte ou nao?", page.obterAlertaNome());
			page.aceitaAlertaNomeObrigatorio();
			page.setEsporte("Futebol");			
			page.cadastrar();
			
		}
		

}
