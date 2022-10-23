package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static br.ce.wcaquino.core.DriverFactory.*;

// Aula 35 - Desafio Cadastro com Sucesso

public class TesteCadastroComSucesso {
	
	
	
	
	@Test
	public void CadastroComSucesso() {
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Washington");
		getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("Almeida");
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		WebElement escolaridade = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo_escolaridade = new Select(escolaridade);
		combo_escolaridade.selectByVisibleText("Superior");
		WebElement combo_multiplo = getDriver().findElement(By.id("elementosForm:esportes"));
		Select sel_cm_esportes = new Select(combo_multiplo);
		sel_cm_esportes.selectByVisibleText("Karate");
		sel_cm_esportes.selectByVisibleText("Corrida");
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Estude mais, você ainda está fraco!");
		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		
		//Validando da minha forma
		Assert.assertEquals("Cadastrado!", getDriver().findElement(By.xpath("//*[@id='resultado']/span")).getText());
		Assert.assertEquals("Washington", getDriver().findElement(By.xpath("//*[@id='descNome']/span")).getText());
		Assert.assertEquals("Almeida", getDriver().findElement(By.xpath("//*[@id='descSobrenome']/span")).getText());
		Assert.assertEquals("Masculino", getDriver().findElement(By.xpath("//*[@id='descSexo']/span")).getText());
		Assert.assertEquals("Pizza", getDriver().findElement(By.xpath("//*[@id='descComida']/span")).getText());
		Assert.assertEquals("superior", getDriver().findElement(By.xpath("//*[@id='descEscolaridade']/span")).getText());
		Assert.assertEquals("Corrida Karate", getDriver().findElement(By.xpath("//*[@id='descEsportes']/span")).getText());
		Assert.assertEquals("Estude mais, você ainda está fraco!", getDriver().findElement(By.xpath("//*[@id='descSugestoes']/span")).getText());
		
		// Outra forma de validar
		Assert.assertEquals(true, getDriver().findElement(By.id("resultado"))
				.getText().contains("Cadastrado!"));
		
		// Conforme ensinado no curso
		Assert.assertTrue(getDriver().findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(getDriver().findElement(By.id("descNome")).getText().endsWith("Washington"));
		Assert.assertEquals("Sobrenome: Almeida", getDriver().findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", getDriver().findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", getDriver().findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", getDriver().findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Corrida Karate", getDriver().findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Estude mais, você ainda está fraco!", getDriver().findElement(By.id("descSugestoes")).getText());
		
		killDriver();
		
	}
	
	

}

