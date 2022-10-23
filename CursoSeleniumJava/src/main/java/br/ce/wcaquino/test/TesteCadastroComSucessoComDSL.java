package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

public class TesteCadastroComSucessoComDSL {
	
	private DSL dsl;
			
			
			@Before
			public void inicializa() {
				
				new ChromeDriver();
				getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); 
				getDriver().manage().window().setSize(new Dimension(1024, 765));
				
				
			}
			
			@After
			public void encerra() {
			killDriver();
		}
			
			
			
			@Test
			public void CadastroComSucesso_updated() {
				
				
				dsl.escreve("elementosForm:nome", "Washington");			
				dsl.escreve("elementosForm:sobrenome", "Almeida");
				dsl.clicarRadioButton("elementosForm:sexo:0");
				dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
				dsl.selecionaOpcaoCombo("elementosForm:escolaridade", "Superior");
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "Karate");
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "Corrida");
				dsl.escreve("elementosForm:sugestoes", "Estude mais, você ainda está fraco!");
				dsl.clicarBotao("elementosForm:cadastrar");			
				
				Assert.assertEquals("Cadastrado!",dsl.obterTexto("//*[@id='resultado']/span"));
				//Assert.assertTrue(getDriver().findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
				Assert.assertEquals("Washington", dsl.obterTexto("//*[@id='descNome']/span"));
				//Assert.assertTrue(getDriver().findElement(By.id("descNome")).getText().endsWith("Washington"));
				Assert.assertEquals("Sobrenome: Almeida", dsl.obterTexto("//*[@id='descSobrenome']/span"));
				//Assert.assertEquals("Sobrenome: Almeida", getDriver().findElement(By.id("descSobrenome")).getText());						
				Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("//*[@id='descSexo']/span"));			
				//Assert.assertEquals("Sexo: Masculino", getDriver().findElement(By.id("descSexo")).getText());
				Assert.assertEquals("Comida: Pizza", dsl.obterTexto("//*[@id='descComida']/span"));
				//Assert.assertEquals("Comida: Pizza", getDriver().findElement(By.id("descComida")).getText());			
				Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("//*[@id='descEscolaridade']/span"));
				//Assert.assertEquals("Escolaridade: superior", getDriver().findElement(By.id("descEscolaridade")).getText());
				Assert.assertEquals("Esportes: Corrida Karate", dsl.obterTexto("//*[@id='descEsportes']/span"));
				//Assert.assertEquals("Esportes: Corrida Karate", getDriver().findElement(By.id("descEsportes")).getText());
				Assert.assertEquals("Sugestoes: Estude mais, você ainda está fraco!", dsl.obterTexto("//*[@id='descSugestoes']/span"));
				//Assert.assertEquals("Sugestoes: Estude mais, você ainda está fraco!", getDriver().findElement(By.id("descSugestoes")).getText());
				
				
			}
			

		}
