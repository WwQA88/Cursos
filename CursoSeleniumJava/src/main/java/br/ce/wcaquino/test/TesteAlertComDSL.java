package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import static br.ce.wcaquino.core.DriverFactory.*;

import br.ce.wcaquino.core.DSL;

public class TesteAlertComDSL {

	private DSL dsl;
	
			@Before
			public void inicializa() {
				
				new ChromeDriver();
				getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); 
				getDriver().manage().window().setSize(new Dimension(1024, 765));
				
			}
			
			@After
			public void encerra() {
			killDriver();
			}
			
			@Test
			public void deveInteragirComAlertSimples() {				
			
				dsl.clicaAlertSimples("alert");
				Assert.assertEquals("Alert Simples", dsl.obterTextoAlert());
				String safeAlert = dsl.obterTextoAlert();
				dsl.aceitaAlert();
				dsl.escreve("elementosForm:nome", safeAlert);	
				Assert.assertEquals(safeAlert, getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
				
			}
	
			@Test
			public void deveInteragirComAlertConfirmNegado() {
				
					dsl.clicaConfirmSimples("confirm");
					Assert.assertEquals("Confirm Simples", dsl.obterTextoConfirm());
					dsl.negaConfirm();
					Assert.assertEquals("Negado", dsl.obterTextoConfirm());
					String safeAlert = dsl.obterTextoConfirm();
					dsl.aceitaConfirm();
					dsl.escreve("elementosForm:nome", safeAlert);
					Assert.assertEquals(safeAlert, getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));		
					
			}
			
			@Test
			public void deveInteragirComAlertPrompt() {
				
					dsl.clicaPrompt("prompt");
					Assert.assertEquals("Digite um numero", dsl.obterTextoPrompt());
					dsl.escrevePrompt("7");
					dsl.aceitaPrompt();
					String texto1 = dsl.obterTextoPrompt();
					Assert.assertEquals(texto1, dsl.obterTextoPrompt());
					dsl.aceitaPrompt();
					String texto2 = dsl.obterTextoPrompt();
					Assert.assertEquals(texto2, dsl.obterTextoPrompt());
					dsl.aceitaPrompt();
					
			
			}
	
}
