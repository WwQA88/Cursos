package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.Select;
import static br.ce.wcaquino.core.DriverFactory.*;

// Aula 39- Desafio Obrigatoriedade e Cadastro com sucesso.

public class TesteObrigatoriedade {
	
		@Test
		public void deveValidarObrigatoriedades() {
			
			//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
			//getDriver().manage().window().maximize();
			getDriver().manage().window().setSize(new Dimension(1024, 765));
			
			// Iteração e validar da obrigatoridade do Nome
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert_nome = getDriver().switchTo().alert();
			Assert.assertEquals("Nome eh obrigatorio", alert_nome.getText());
			alert_nome.accept();
			
			// Preenchimento do nome e iteração de validação da obrigatoridade do Sobrenome
			getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Washington");
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert_sobrenome = getDriver().switchTo().alert();
			Assert.assertEquals("Sobrenome eh obrigatorio", alert_sobrenome.getText());
			alert_sobrenome.accept();
			
			// Preenchimento do Sobrenome e iteração de validação da obrigatoridade do Sexo
			getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("Almeida");
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert_sexo = getDriver().switchTo().alert();
			Assert.assertEquals("Sexo eh obrigatorio", alert_sexo.getText());
			alert_sexo.accept();
			
			// Preenchimento do sexo e iteração de validação da obrigatoridade do Comida Favorita
			getDriver().findElement(By.id("elementosForm:sexo")).click();
			getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
			getDriver().findElement(By.id("elementosForm:comidaFavorita:3")).click();
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert_comida = getDriver().switchTo().alert();
			Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert_comida.getText());
			alert_comida.accept();
			getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
			
			// Preenchimento da Comida Favorita e iteração de validação da obrigatoridade do Esporte
			new Select(getDriver().findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
			new Select(getDriver().findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
			Alert alert_esporte = getDriver().switchTo().alert();
			Assert.assertEquals("Voce faz esporte ou nao?", alert_esporte.getText());
			alert_esporte.accept();			
			new Select(getDriver().findElement(By.id("elementosForm:esportes"))).deselectByVisibleText("Futebol");
			getDriver().findElement(By.id("elementosForm:cadastrar")).click();
				
			
		}

}
