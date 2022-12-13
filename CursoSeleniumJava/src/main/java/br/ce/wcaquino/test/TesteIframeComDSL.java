package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

public class TesteIframeComDSL {
	
	private DSL dsl;
	
	@Before
	public void initialize() {
	
		new ChromeDriver();
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); 
		//getDriver().manage().window().maximize();
		dsl.resolucaoDimensionxDimension(1024, 768);
		dsl.resolucaoPositionxPosition(100, 100);
	}
	
	
	@After
	public void terminate() {
		
		killDriver();
		
	}
	
	@Test
	public void deveInteragircomFramesDSL() {
		
		// Interagindo com o frame
		dsl.acessaIframe("frame1");
		dsl.clicaBotaoIframe("frameButton");
		String texto = dsl.obterTextoFrame();
		dsl.aceitaIframeAlert();
		dsl.populaCampoComAlert("elementosForm:nome", texto);
		
	}
	
	// ** Aula 49 - Scroll ** \\
	
	@Test
	public void deveInteragirComFrameEscondido () {
		
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.acessaIframe("frame2");		
		dsl.clicaBotaoIframe("frameButton");
		dsl.obterTextoFrame();
		assertEquals("Frame OK!", dsl.obterTextoFrame());
		dsl.aceitaIframeAlert();
		
		
	}
	
}