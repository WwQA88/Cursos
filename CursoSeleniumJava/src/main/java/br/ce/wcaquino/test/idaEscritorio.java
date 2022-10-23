package br.ce.wcaquino.test;

import org.junit.Test;
import org.openqa.selenium.By;


import br.ce.wcaquino.core.DSL;

public class idaEscritorio {
	
	private String nomeFunc = "Washington Willian da Silva Almeida";
	private String emailFunc = "washington.almeida@audatex.com.br";
	private String emailGestor = "aurora.hernandez@audatex.com";
	
	
	private DSL dsl;
	
	
		@Test
		public void preencheEnviaFormulario() throws InterruptedException {
		
			
			dsl = new DSL();
			
			dsl.webSite("https://forms.office.com/FormsPro/Pages/ResponsePage.aspx?id=80hbxLsTi0STVrp7hjwhiTF5jxHjQF9BkCW5gUJrJiRUNTlCQzJGNlUzTDM0TjZXWE85VlNKQjREUC4u&wdLOR=c0B5A82CD-C436-4D1D-930B-05B212D1CDA3");
			
			dsl.aguardaCampoClicavel(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question2-title question2-image question2-required question2-questiontype']"));
			dsl.escreverBy(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question2-title question2-image question2-required question2-questiontype']"), ""+nomeFunc+"");
			
			dsl.aguardaCampoClicavel(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question3-title question3-image question3-required question3-questiontype']"));
			dsl.escreverBy(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question3-title question3-image question3-required question3-questiontype']"), ""+emailFunc+"");
			
			dsl.aguardaCampoClicavel(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question4-title question4-image question4-required question4-questiontype']"));
			dsl.escreverBy(By.xpath("//*[@class='office-form-textfield']//*[@aria-labelledby='question4-title question4-image question4-required question4-questiontype']"), ""+emailGestor+"");
						
			dsl.aguardaCampoClicavel(By.id("t6_placeholder"));
			dsl.clicarBotao("t6_placeholder");
			
			Thread.sleep(5000);
			
			dsl.executaScroll();
			dsl.selecionaOpcaoCombo(By.xpath("//span[contains(text(),'Sao Paulo (Audatex)')]"));
			
			
			dsl.clicaBotaoBy(By.xpath("//*[@class='button-content']"));
			
			dsl.aguardaCampoClicavel(By.xpath("//div[@aria-labelledby='question7-title question7-image question7-required question7-questiontype']//label//input[@value='No']"));
			dsl.clicarRadio(By.xpath("//div[@aria-labelledby='question7-title question7-image question7-required question7-questiontype']//label//input[@value='No']"));
			
			dsl.aguardaCampoClicavel(By.xpath("//div[@aria-labelledby='question8-title question8-image question8-required question8-questiontype']//label//input[@value='No']"));
			dsl.clicarRadio(By.xpath("//div[@aria-labelledby='question8-title question8-image question8-required question8-questiontype']//label//input[@value='No']"));
			
			dsl.aguardaCampoClicavel(By.xpath("//div[@aria-labelledby='question9-title question9-image question9-required question9-questiontype']//label//input[@value='No']"));
			dsl.clicarRadio(By.xpath("//div[@aria-labelledby='question9-title question9-image question9-required question9-questiontype']//label//input[@value='No']"));
			
			dsl.aguardaCampoClicavel(By.xpath("//div[@aria-labelledby='question10-title question10-image question10-required question10-questiontype']//label//input[@value='No']"));
			dsl.clicarRadio(By.xpath("//div[@aria-labelledby='question10-title question10-image question10-required question10-questiontype']//label//input[@value='No']"));
			
			dsl.aguardaCampoClicavel(By.xpath("//div[@aria-labelledby='question11-title question11-image question11-required question11-questiontype']//label//input[@value='No']"));
			dsl.clicarRadio(By.xpath("//div[@aria-labelledby='question11-title question11-image question11-required question11-questiontype']//label//input[@value='No']"));
			
			dsl.clicaBotaoBy(By.xpath("//button[@class='__submit-button__ office-form-bottom-button office-form-theme-button button-control light-background-button']"));
			
		}

}
