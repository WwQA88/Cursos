package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

// Aula 56 - Framework Prime Faces

public class TestePrime {
	
	private DSL dsl;
	
	
				@Before
				public void inicializa() {
					
					new ChromeDriver();
					
					
				}
				
				@After
				public void encerra() {
				//killDriver();
			}
	
					@Test
					public void deveInteragirComRadioPrime() {
						
						dsl.clicarRadio(By.xpath("//*[@id='j_idt305:console:1']/../..//div//span"));
						assertTrue(dsl.isRadioMarcado("j_idt305:console:1"));
						
						dsl.clicarRadio(By.xpath("//label[.='Option3']/..//span"));
						assertTrue(dsl.isRadioMarcado("j_idt305:console:2"));

					}
					
					// Aula 59 - Desafio Combo Prime
					
					@Test
					public void deveInteragirComComboPrime() {
						
						getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=bfeb4");
						getDriver().manage().window().maximize();
						
						// Com método DSL
						dsl.selecionarComboPrime("j_idt304:option_label", "j_idt304:option_1");
						
						// Sem método DSL 
						// getDriver().findElement(By.xpath("//label[@id='j_idt304:option_label']/..//span")).click();	// ou por ID getDriver().findElement(By.id("j_idt304:option")).click();	
						
						
						//	getDriver().findElement(By.id("j_idt304:option_1")).click();
						
						// Assert sem DSL
						assertEquals("Option1", getDriver().findElement(By.xpath("//label[.='Option1']")).getText());
						
						// Assert com DSL
						assertEquals("Option1", dsl.obterTexto("//label[.='Option1']"));
						
						
						
						
					}

}
