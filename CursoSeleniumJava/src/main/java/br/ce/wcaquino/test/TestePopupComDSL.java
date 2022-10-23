package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

public class TestePopupComDSL {
	
	private DSL dsl;
	
		@Before
		public void Initialize() {
			new ChromeDriver();
			getDriver().manage().window().maximize();
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
		}
		
		/*public void Terminate() {
			killDriver();
		}*/
		
			@Test
			public void deveInteragirComJanelasDSL() {
				
				// Iteração com a janela/popup
				dsl.IterageComPopUp("buttonPopUpEasy", "Popup", "textarea", "Deu certo?");
				Assert.assertEquals("Deu certo?", dsl.obterTextoPopUp("textarea"));
				getDriver().close();
				
				// Iteração com a janela principal
				dsl.retornaParaJanelaPrincipal();
				dsl.escreve("elementosForm:sugestoes", "Parece que deu certo!");
				Assert.assertEquals("Parece que deu certo!", dsl.ObterValorCampo("elementosForm:sugestoes"));
				
			}
			
			@Test
			public void deveInteragirComJanelasSemTituloDSL() {
				
				// Iteração com a janela/popup
				dsl.IterageComPopUpSemTitulo("buttonPopUpHard");
				dsl.obterConteudoWindowHandles(1);
				dsl.escreverTextArea("textarea", "E agora?");
				dsl.obterConteudoWindowHandles(0);
				dsl.escreve("elementosForm:sugestoes", "Parece que deu certo!");
				Assert.assertEquals("Parece que deu certo!", dsl.ObterValorCampo("elementosForm:sugestoes"));
			}
			


}
