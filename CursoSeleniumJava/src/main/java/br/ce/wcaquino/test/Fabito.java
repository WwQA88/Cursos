package br.ce.wcaquino.test;
	
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import static br.ce.wcaquino.core.DriverFactory.*;


public class Fabito {




	 // private String numeroSinistro;

	 @Before
	public void Initializer() throws Exception {
	new ChromeDriver();
	getDriver().manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

	 // numeroSinistro = TestbrazilQA_001;

	 // TEMPO DE ESPERA - Para aparecer a tela inicial do QAPTER
	Thread.sleep(3000);

	 // REALIZA A AUTENTICAÇÃO

	getDriver().get("https://www-i4ref.audatex.net/breclient");
	getDriver().manage().window().maximize();
	getDriver().findElement(By.id("ssousername")).clear();
	getDriver().findElement(By.id("ssousername")).sendKeys("fabio.ferreira.insurance");
	/*
	* try { takeSnapShot("TelaLogin.png", driver); } catch (Exception e) { // TODO
	* Auto-generated catch block e.printStackTrace(); }
	*/
	getDriver().findElement(By.id("password")).clear();
	getDriver().findElement(By.id("password")).sendKeys("Brasil");
	getDriver().findElement(By.className("btn-submit")).click();
	getDriver().findElement(By.id("home")).click();
	getDriver().findElement(By.id("view-link-worklistgrid_open")).click();
	Thread.sleep(3000);
	getDriver().findElement(By.id("WorklistGridSearchPopupComponent")).click();
	getDriver().findElement(By.id("root.wfGrid.workList.search.claimNumber")).clear();
	getDriver().findElement(By.id("root.wfGrid.workList.search.claimNumber")).sendKeys("Test000002");
	getDriver().findElement(By.xpath("//div[@id='GridSearch']/div/div/div[4]/button[3]/span")).click();
	getDriver().findElement(By.xpath("//div[@id='more-row-icon-0']/div/div/div")).click();
	getDriver().findElement(By.xpath("//a[@id='openTask']/span")).click();

	 }

	 @After
	public void Finalizer() throws Exception {

	 killDriver();
	}


	 @Test
	public void Step_Validate_photo_in_Damage_Capture() throws Exception {
	// Validate photo in Damage Capture QAPTER
	Thread.sleep(2000);
	//Clicar no Menu Damage Capturing
	getDriver().findElement(By.id("toDoListItem_Damage_capturing")).click();
	//Aguardea tela aparecer
	Thread.sleep(30000);
	getDriver().switchTo().frame(getDriver().findElement(By.id("iframe_root.task.damageCapture.inlineWebPad")));
	getDriver().findElement(By.xpath("//*[@id='navigation-photo']")).click();
	//Entrar no iframe
	
	//Aguarde
	//Thread.sleep(30000);
	//clique no menu camera
	//getDriver().findElement(By.id("navigation-photo")).click();
	//getDriver().switchTo().defaultContent(); // Retornar ao conteúdo principal ou primário

	}
	}


