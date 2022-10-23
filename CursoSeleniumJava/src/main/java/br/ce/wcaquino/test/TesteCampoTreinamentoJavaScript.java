package br.ce.wcaquino.test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static br.ce.wcaquino.core.DriverFactory.*;

import br.ce.wcaquino.core.DSL;

public class TesteCampoTreinamentoJavaScript {
	

	// Atualização aula 40 e 41 - Reuso de código com @Before e @After
		// Atualização aula 42 - Implementando DSL


			
			private WebDriver driver; // Variável Global
			private DSL dsl; // Deixando a biblioteca como Global
			
			@Before	
			public void inicializa() {
					driver = new ChromeDriver();
					
					//getDriver().get("file:///C:/JavaSeleniumWebDriver/Files/componentes.html"); // Aponta para um diretório específico
					getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); // Aponta para diretório padrão do Java/Eclipse
					//getDriver().manage().window().maximize();
					getDriver().manage().window().setSize(new Dimension(1024, 765));
					
			
			}
			
			
			@After
				public void encerra() {
				//killDriver();
			}
				
				

			@Test
			public void testeTextField() {
				

				dsl.escreve("elementosForm:nome", "Teste de escrita");					
				Assert.assertEquals("Teste de escrita", dsl.ObterValorCampo("elementosForm:nome"));
				
			}
			
			@Test
			public void deveIntegarirComTextArea() {
				
				
				dsl.escreve("elementosForm:nome", "Teste");
				Assert.assertEquals("Teste", dsl.ObterValorCampo("elementosForm:nome"));
				
				killDriver();		
				
			}
			
			// Aula 27 Radio e Check
			
			@Test
			public void deveIntegarirComRadioButton() {
				
				
				dsl.clicarRadioButton("elementosForm:sexo:0");					
				Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
				
				killDriver();		
				
			}
			
			@Test
			public void deveIntegarirComCheckBox() {
				
				
				dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
				Assert.assertTrue(dsl.isCheckBoxMarcado("elementosForm:comidaFavorita:2"));
				
				killDriver();		
				
			}
			
			// Aula 28 Iteração com Combobox
			
			@Test
			public void deveIntegarirComCombo() {					
				
				dsl.selecionaOpcaoCombo("elementosForm:escolaridade", "2o grau completo");					
				Assert.assertEquals("2o grau completo", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));

			}
			
			@Test
			public void deveVerificarValoresDisponiveisCombo() {
				
				
				
				
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
				
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "Natacao");
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "Corrida");
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "O que eh esporte?");
				
				
				
				// Verificação			
				
				Assert.assertEquals(3, dsl.obterQtdeOpcoesSelecionadasCombo("elementosForm:esportes"));
				
				// Removendo a seleção caso já esteja selecionado
				
				dsl.removerOpcaoSelecionada("elementosForm:esportes", "Corrida");
				
				// Verificação
				
				Assert.assertEquals(2, dsl.obterQtdeOpcoesSelecionadasCombo("elementosForm:esportes"));
				
			}
			
			// Aula 29 - Botão
			
			@Test
			public void deveInteragirComBotoes() {
				
				
				dsl.clicarBotao("buttonSimple");					
				WebElement botao = getDriver().findElement(By.id("buttonSimple"));
				Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
				
			}
			
			// Aula 30 - Link
			
				@Test
				// @Ignore // Não executar o teste
				public void deveInteragirComLinks() {
					
					
					dsl.clicarLink("Voltar");
					//Assert.fail(); // Apenas como lembrete para não esquecermos que esse bloco precisa ser validado.
					Assert.assertEquals("Voltou!", dsl.obterTexto("//*[@id='resultado']/span"));
					
				}
				
				// Aula 31 - Div e Span
				
					@Test
					public void deveBuscarTextosNaPagina() {							

						getDriver().findElement(By.tagName("body")); // Localizar pelo nome da tag
						
						//Assert.assertTrue(getDriver().findElement(By.tagName("body")) //
								//.getText().contains("Campo de Treinamento")); // Validar o texto na página
						
						Assert.assertEquals("Campo de Treinamento", dsl.obterTextoTag(By.tagName("h3")));
						
						Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTextoTag(By.className("facilAchar")));
						
					}
					
					// Aula 43 - Resultado
					
					@Test
					public void textFieldDuplo() {
						
						dsl.escreve("elementosForm:nome", "Washington");
						assertEquals("Washington", dsl.ObterValorCampo("elementosForm:nome"));
						
						dsl.limpaConteudoIdCampo("elementosForm:nome");
						
						dsl.escreve("elementosForm:nome", "Almeida");
						assertEquals("Almeida", dsl.ObterValorCampo("elementosForm:nome"));
						
					}
					
					@Test
					public void testJavaScript() {
						
						// Um cast foi adicionado ao "driver" para que fosse reaproveitado no uso do JS
						
						// Exibindo alerta via JavaScript
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("alert('Teste JS via selenium')");
						dsl.aceitaAlert();
						
						// Escrevendo via JavaScript						
						js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JavaScript'");
						
						// Mudando o elemento na tela via JavaScript
						js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
						
						// Colorindo o contorno do campo "Nome" em vermelho
						WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
						js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
					}

	
		
			

}
