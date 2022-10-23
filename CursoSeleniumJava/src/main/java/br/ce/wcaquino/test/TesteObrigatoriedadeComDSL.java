package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

public class TesteObrigatoriedadeComDSL {

	private DSL dsl;
	
		@Before
		public void inicializa() {
			new ChromeDriver();
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
			getDriver().manage().window().maximize();
		}
		
		@After
		public void encerra() {
			killDriver();
		}
		
			@Test
			public void deveValidarObrigatoriedadesDSL() {
				
				// Iteração e validar da obrigatoridade do Nome
				
				dsl.clicarBotao("elementosForm:cadastrar");
				dsl.obterTextoAlert();
				Assert.assertEquals("Nome eh obrigatorio", dsl.obterTextoAlert());
				dsl.aceitaAlert();
				
				// Preenchimento do nome e iteração de validação da obrigatoridade do Sobrenome
				
				dsl.escreve("elementosForm:nome", "Washington");
				dsl.clicarBotao("elementosForm:cadastrar");
				dsl.obterTextoAlert();
				Assert.assertEquals("Sobrenome eh obrigatorio", dsl.obterTextoAlert());
				dsl.aceitaAlert();
				
				// Preenchimento do Sobrenome e iteração de validação da obrigatoridade do Sexo
				
				dsl.escreve("elementosForm:sobrenome", "Almeida");
				dsl.clicarBotao("elementosForm:cadastrar");
				dsl.obterTextoAlert();
				Assert.assertEquals("Sexo eh obrigatorio", dsl.obterTextoAlert());
				dsl.aceitaAlert();
				
				// Preenchimento do sexo e iteração de validação da obrigatoridade do Comida Favorita
				
				dsl.clicarRadioButton("elementosForm:sexo");
				dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
				dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
				dsl.clicarBotao("elementosForm:cadastrar");
				dsl.obterTextoAlert();
				Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterTextoAlert());
				dsl.aceitaAlert();
				dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
				
				// Preenchimento da Comida Favorita e iteração de validação da obrigatoridade do Esporte
				
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "Futebol");
				dsl.selecionaOpcaoCombo("elementosForm:esportes", "O que eh esporte?");
				dsl.clicarBotao("elementosForm:cadastrar");
				dsl.obterTextoAlert();
				Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterTextoAlert());
				dsl.aceitaAlert();
				dsl.removerOpcaoSelecionada("elementosForm:esportes", "Futebol");
				dsl.clicarBotao("elementosForm:cadastrar");
			}
		
		
}
