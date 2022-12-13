package br.ce.wcaquino.test;


import static br.ce.wcaquino.core.DriverFactory.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;

public class TesteIteracaoComboDSL {
	
	private DSL dsl;
	
		@Before
		public void initialize() {
			new ChromeDriver();
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
			getDriver().manage().window().maximize();
		}
		
		@After
		public void terminate() {
			killDriver();
		}
		
			@Test
			public void deveIntegarirComComboDSL() {
				dsl.selecionaOpcaoCombo("elementosForm:escolaridade", "2o grau completo");
				Assert.assertEquals("1o grau completo", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));
			}
			
			@Test
			public void deveVerificarValoresDisponiveisComboDSL () {
				dsl.verificaOpcoesCombo("elementosForm:escolaridade");
				Assert.assertEquals(8, dsl.obterQtdeDispCombo("elementosForm:escolaridade"));
				Assert.assertTrue(dsl.obterOpcoesComboPorLoopFor("elementosForm:escolaridade", "Mestrado"));
				Assert.assertFalse(dsl.obterOpcoesComboPorLoopFor("elementosForm:escolaridade", "Pós Doutorado"));
			}
			
			@Test
			public void deveVerificarValoresComboMultiploDSL () {
				dsl.selecionaOpcoesMultiplasCombo("elementosForm:esportes", "Natacao");
				dsl.selecionaOpcoesMultiplasCombo("elementosForm:esportes", "Corrida");
				dsl.selecionaOpcoesMultiplasCombo("elementosForm:esportes", "O que eh esporte?");
				Assert.assertEquals(3, dsl.obterQtdeOpcoesSelecionadasCombo("elementosForm:esportes"));
				
				dsl.removerOpcaoSelecionada("elementosForm:esportes", "Corrida");
				Assert.assertEquals(2, dsl.obterQtdeOpcoesSelecionadasCombo("elementosForm:esportes"));
				
			}

}
