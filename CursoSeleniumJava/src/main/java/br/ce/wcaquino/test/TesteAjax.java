package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import br.ce.wcaquino.core.DSL;

public class TesteAjax {
	
	
	private DSL dsl;
	
	
	
				@Before
				public void inicializa() {
					
					dsl = new DSL();
					
										
					getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=bfeb4");
					//getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
					getDriver().manage().window().maximize();
					
					
				}
				
				@After
				public void encerra() {
					killDriver();
				}
				
				@Test
				public void testeAjax () {
					
					dsl.escreve("j_idt311:name", "Texto Ajax");
					dsl.clicarBotao("j_idt311:j_idt315");
					dsl.aguardaTextoVisivel(By.id("j_idt311:display"), "Texto Ajax");					
					assertEquals("Texto Ajax", getDriver().findElement(By.id("j_idt311:display")).getText());
				}

}
