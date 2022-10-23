package br.ce.wcaquino.test;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static br.ce.wcaquino.core.DriverFactory.*;

import br.ce.wcaquino.core.DSL;

// Aula 60 - Problema de sincronismo

public class TesteSincronismo {
	
	private WebDriver driver;
	private DSL dsl;
	
	
				@Before
				public void inicializa() {
					
					driver = new ChromeDriver(); 
					getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
					getDriver().manage().window().maximize();
					
					
				}
				
				@After
				public void encerra() {
				//killDriver();
			}
				
					@Test
					public void deveUtilizarEsperaFixa () throws InterruptedException {
						
						// Aula 61 - Espera fixa
						dsl.clicarBotao("buttonDelay");
						Thread.sleep(5000);
						dsl.escreve("novoCampo", "delay aqui");
					}
					
						@Test
						public void deveUtilizarEsperaImplicita () throws InterruptedException {
							
							// Aula 62 - Espera Implicita
							getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							dsl.clicarBotao("buttonDelay");							
							dsl.escreve("novoCampo", "delay aqui");
							getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
						}
						
							@Test
							public void deveUtilizarEsperaExplicita () throws InterruptedException {
								
								// Aula 63 - Espera Explicita								
								dsl.clicarBotao("buttonDelay");	
								WebDriverWait wait = new WebDriverWait(driver, 30);
								wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
								dsl.escreve("novoCampo", "delay aqui");
								
							}

}
