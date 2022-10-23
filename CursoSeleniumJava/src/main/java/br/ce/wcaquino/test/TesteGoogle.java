package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {

	//public static void main(String[] args) { -- Não é mais necessário o  método main quando utilizado o JUnit
	
	@Test
	public void testeFireFox  () {
		new FirefoxDriver();
		getDriver().get("http://www.google.com");
		getDriver().manage().window().setPosition(new Point(100, 100)); // Definindo a posição da janela
		getDriver().manage().window().setSize(new Dimension(1200, 765)); // Definindo o tamanho da janela
		getDriver().manage().window().maximize(); // Definindo a janela para Maximizado
		assertEquals("Google", getDriver().getTitle());
		killDriver(); // Fecha o browser por completo
		//getDriver().close(); // Fecha apenas uma aba do browser
	}
	
	@Test
	public void testeGoogleChrome  () {
		new ChromeDriver();
		getDriver().manage().window().setPosition(new Point(100, 100)); // Definindo a posição da janela
		getDriver().manage().window().setSize(new Dimension(1200, 765)); // Definindo o tamanho da janela
		getDriver().manage().window().maximize(); // Definindo a janela para Maximizado
		getDriver().get("http://www.google.com");
		assertEquals("Google", getDriver().getTitle());
		killDriver(); // Fecha o browser por completo
		//getDriver().close(); // Fecha apenas uma aba do browser
	}
	
	@Test
	public void testeInternetExplorer  () {
		new InternetExplorerDriver();
		getDriver().manage().window().setPosition(new Point(100, 100)); // Definindo a posição da janela
		getDriver().manage().window().setSize(new Dimension(1200, 765)); // Definindo o tamanho da janela
		getDriver().manage().window().maximize(); // Definindo a janela para Maximizado
		getDriver().get("http://www.google.com");
		assertEquals("Google", getDriver().getTitle());
		killDriver(); // Fecha o browser por completo
		//getDriver().close(); // Fecha apenas uma aba do browser
	}
}


