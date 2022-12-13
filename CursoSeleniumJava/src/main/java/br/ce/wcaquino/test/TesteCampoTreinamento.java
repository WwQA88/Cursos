package br.ce.wcaquino.test;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static br.ce.wcaquino.core.DriverFactory.*;
//import org.openqa.selenium.firefox.FirefoxDriver;

// Aula 26 Text Area e TextField

public class TesteCampoTreinamento {

	@Test
	public void testeTextField() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		killDriver();		
		
	}
	
	@Test
	public void deveIntegarirComTextArea() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");	
		Assert.assertEquals("Teste", getDriver().findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		killDriver();		
		
	}
	
	// Aula 27 Radio e Check
	
	@Test
	public void deveIntegarirComRadioButton() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		killDriver();		
		
	}
	
	@Test
	public void deveIntegarirComCheckBox() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		killDriver();		
		
	}
	
	// Aula 28 Iteração com Combobox
	
	@Test
	public void deveIntegarirComCombo() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(2); // Seleciona conforme a ordem no html correspondente
		//combo.selectByValue("superior"); // Seleciona conforme o conteúdo do value no html correspondente
		combo.selectByVisibleText("2o grau completo"); // Seleciona conforme o conteúdo do texto visível no html correspondente
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		
		
		killDriver();
	}
	
	@Test
	public void deveVerificarValoresDisponiveisCombo() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		Assert.assertEquals(8, options.size());
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
				
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	// Aula 28 - Combo de múltipla escolha
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element); // Criação da instância
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		// Verificação
		List<WebElement> allSelectedElements =  combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedElements.size());
		
		// Removendo a seleção caso já esteja selecionado		
		combo.deselectByVisibleText("Corrida");
		
		// Verificação
		allSelectedElements =  combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedElements.size());
		
		killDriver();

	}
	
	// Aula 29 - Botão
	
	@Test
	public void deveInteragirComBotoes() {
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		killDriver();
	}
	
	// Aula 30 - Link
	
		@Test
		@Ignore // Não executar o teste
		public void deveInteragirComLinks() {
			
			new ChromeDriver();
			
			//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
			//getDriver().manage().window().maximize();
			getDriver().manage().window().setSize(new Dimension(1024, 765));
			WebElement link = getDriver().findElement(By.linkText("Voltar"));
			link.click();
			//Assert.fail(); // Apenas como lembrete para não esquecermos que esse bloco precisa ser validado.
			Assert.assertEquals("Voltou!", getDriver().findElement(By.id("resultado")).getText());
			
		}
		
		// Aula 31 - Div e Span
		
			@Test
			public void deveBuscarTextosNaPagina() {
				
				new ChromeDriver();
				
				//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
				getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
				//getDriver().manage().window().maximize();
				getDriver().manage().window().setSize(new Dimension(1024, 765));
				getDriver().findElement(By.tagName("body")); // Localizar pelo nome da tag
				
				//Assert.assertTrue(getDriver().findElement(By.tagName("body")) //
						//.getText().contains("Campo de Treinamento")); // Validar o texto na página
				
				Assert.assertEquals("Campo de Treinamento",
						getDriver().findElement(By.tagName("h3")).getText());
				
				Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
						getDriver().findElement(By.className("facilAchar")).getText());
				
				killDriver();
			}
			
	
}

