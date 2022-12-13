package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;


// Aula 36 - Frames
public class TesteIframe {
	
	@Test
	public void deveInteragirComFrames_minhaForma() {		
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		
		// Iteração com Iframe
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		
		// Iteração com o alert
		Alert alert = getDriver().switchTo().alert();
		String msg_frame = alert.getText();
		Assert.assertEquals(msg_frame, alert.getText());
		alert.accept();
		
		// Populando o alert no campo nome
		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg_frame);
		
	}
	
	@Test
	public void deveInteragirComFrames() {		
		
		new ChromeDriver();
		
		//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
		//getDriver().manage().window().maximize();
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		
		// Iteração com Iframe
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		
		// Iteração com o alert
		Alert alert = getDriver().switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();		
		
		// Populando o alert no campo nome
		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);;
		//getDriver().findElement(By.xpath("//*[@id='wrapper']/header//span[@id='navigation-photo']"));
		
		
	}

}
