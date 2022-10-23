package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.page.CampoTreinamentoPage;

//** Aula 45 - Page Objects **\\

public class TesteCadastroComSucessoPageObject extends BaseTest {
	
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" ); 
		getDriver().manage().window().setSize(new Dimension(1024, 765));
		page = new CampoTreinamentoPage();
		
	}
	
	
	
	
	
	@Test
	public void CadastroComSucesso_PageObject() {
		
		
		
		page.setNome("Washington");
		//dsl.escreve("elementosForm:nome", "Washington");	
		
		page.setSobreNome("Almeida");
		//dsl.escreve("elementosForm:sobrenome", "Almeida");		
		
		page.setGeneroMasculino();
		//dsl.clicarRadioButton("elementosForm:sexo:0");
		
		page.setComidaFavoritaPizza();
		//dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		
		page.setEscolaridade("Mestrado");
		//dsl.selecionaOpcaoCombo("elementosForm:escolaridade", "Superior");
		
		page.setEsporte("Karate");
		//dsl.selecionaOpcaoCombo("elementosForm:esportes", "Karate");
		
		page.setEsporte("Corrida");
		//dsl.selecionaOpcaoCombo("elementosForm:esportes", "Corrida");
		
		page.setSugestoes("Estude mais, você ainda está fraco!");
		//dsl.escreve("elementosForm:sugestoes", "Estude mais, você ainda está fraco!");
		
		page.cadastrar();
		//dsl.clicarBotao("elementosForm:cadastrar");			
		
		//** Assertivas **\\
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		//Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		
		Assert.assertEquals("Washington", page.obterNomeCadastro());
		//Assert.assertTrue(dsl.obterTextoId("descNome").endsWith("Washington"));
		
		Assert.assertEquals("Almeida", page.obterSobreNomeCadastro());
		//Assert.assertEquals("Sobrenome: Almeida", dsl.obterTextoId("descSobrenome"));
								
		Assert.assertEquals("Masculino", page.obterGeneroMasculinoCadadstro());
		//Assert.assertEquals("Sexo: Masculino", dsl.obterTextoId("descSexo"));			
		
		Assert.assertEquals("Pizza", page.obterComidaFavoritaCadastro());
		//Assert.assertEquals("Comida: Pizza", dsl.obterTextoId("descComida"));
					
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		//Assert.assertEquals("Escolaridade: superior", dsl.obterTextoId("descEscolaridade"));
		
		Assert.assertEquals("Corrida Karate", page.obterEsporteCadastro());
		//Assert.assertEquals("Esportes: Corrida Karate", dsl.obterTextoId("descEsportes"));
		
		Assert.assertEquals("Estude mais, você ainda está fraco!", page.obterSugestoesCadastro());
		//Assert.assertEquals("Sugestoes: Estude mais, você ainda está fraco!", dsl.obterTextoId("descSugestoes"));
		
		
		
	}
	
	
	

}





