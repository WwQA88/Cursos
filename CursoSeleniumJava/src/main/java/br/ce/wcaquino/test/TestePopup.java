package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;


// Aula 37 - Janelas
public class TestePopup {
	
	@Test
	public void deveInteragirComJanelas() {		
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		
		// Iteração com a janela/popup
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();

		
		// Iteração com a janela principal
		getDriver().switchTo().window("");
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Parece que deu certo!");
		
		
	}
	
	// Aula 38 - WindowHandler
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {		
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		
		// Iteração com a janela/popup
		getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Parece que deu certo!");
		
		killDriver();
	}

}
