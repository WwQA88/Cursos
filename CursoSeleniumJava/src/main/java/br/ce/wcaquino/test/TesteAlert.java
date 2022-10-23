package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import static br.ce.wcaquino.core.DriverFactory.*;

public class TesteAlert {
	
	// Aula 32 - Alert Simples
	
			@Test
			public void deveInteragirComAlertSimples() {
				
				//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
				getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
				//getDriver().manage().window().maximize();
				getDriver().manage().window().setSize(new Dimension(1024, 765));
				
				getDriver().findElement(By.id("alert")).click();
				Alert alert =  getDriver().switchTo().alert();
				Assert.assertEquals("Alert Simples", alert.getText());
				
				String texto = alert.getText();
				alert.accept();
				getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
				Assert.assertEquals("Alert Simples", getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
				killDriver();
			}
			
			// Aula 33 - Alert Confirm/Dismiss
			
					@Test
					public void deveInteragirComAlertConfirmOK() {
						
						//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
						getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
						//getDriver().manage().window().maximize();
						getDriver().manage().window().setSize(new Dimension(1024, 765));
						
						getDriver().findElement(By.id("confirm")).click();
						Alert confirm =  getDriver().switchTo().alert();
						Assert.assertEquals("Confirm Simples", confirm.getText());
						
						String texto = confirm.getText();
						confirm.accept();
						Assert.assertEquals("Confirmado", confirm.getText());
						confirm.accept();
						getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
						Assert.assertEquals("Confirm Simples", getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
						killDriver();
					}
					
					@Test
					public void deveInteragirComAlertConfirmNegado() {
						
						//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
						getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
						//getDriver().manage().window().maximize();
						getDriver().manage().window().setSize(new Dimension(1024, 765));
						
						getDriver().findElement(By.id("confirm")).click();
						Alert confirm = getDriver().switchTo().alert();
						Assert.assertEquals("Confirm Simples", confirm.getText());
						
						
						confirm.dismiss();
						Assert.assertEquals("Negado", confirm.getText());
						String texto = confirm.getText();
						confirm.accept();
						getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
						Assert.assertEquals("Negado", getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
						killDriver();
					}
					
					// Aula 34 - Alert Prompt
					
					@Test
					public void deveInteragirComAlertPrompt() {
						
						//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
						getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
						//getDriver().manage().window().maximize();
						getDriver().manage().window().setSize(new Dimension(1024, 765));
						getDriver().findElement(By.id("prompt")).click();
						Alert prompt = getDriver().switchTo().alert();
						Assert.assertEquals("Digite um numero", prompt.getText());
						prompt.sendKeys("7");
						prompt.accept();
						String texto = prompt.getText();
						Assert.assertEquals(texto, prompt.getText());
						prompt.accept();
						String texto2 = prompt.getText();
						Assert.assertEquals(texto2, prompt.getText());
						prompt.accept();
						

					}

}
