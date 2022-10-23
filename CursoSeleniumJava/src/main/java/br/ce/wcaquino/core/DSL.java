package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFactory.*;

import java.awt.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



// Aula 42 - DSL
// * A DSL é uma biblioteca de métodos, sendo assim serve para reaproveitarmos métodos já utilizados em outras classes. O conceito de reúso será totalmente aplicado

// Update aula 67 - Driver centralizado
// A partir esse ponto, o WebDriver passou a ser centralizado. Por esse motivo, não será mais necessário instancia-lo.
// A seguir, a nova forma:

public class DSL {
	
	/*
	private WebDriver driver;		
	
		// Instancia de Driver para DSL
		// Um construtor foi criado
	
		public DSL(WebDriver driver) {
		super();
		this.driver = driver;
		
		
		// ** Escrita ** //
		
	*/
	
	private JavascriptExecutor scroll;
		
	

		public void escreve(String id_campo, String texto) {
			getDriver().findElement(By.id(id_campo)).clear();
			getDriver().findElement(By.id(id_campo)).sendKeys(texto);
		}
		
		public void escreverBy(By by, String texto) {
			getDriver().findElement(by).sendKeys(texto);
		}
		
		public String ObterValorCampo(String id_campo) {			
			return getDriver().findElement(By.id(id_campo)).getAttribute("value");
		}
		
		// ** Radio Button ** //
		
		public void clicarRadioButton(String id) {
			getDriver().findElement(By.id(id)).click();
			
		}
		
		public void clicarRadio(By by) {
			getDriver().findElement(by).click();
			
		}
		
		public boolean isRadioMarcado(String id) {
			return getDriver().findElement(By.id(id)).isSelected();
			
		}
		
		// ** Checkbox ** //
		
		public void clicarCheckBox(String id) {
			getDriver().findElement(By.id(id)).click();
			
		}
		
		public boolean isCheckBoxMarcado(String id) {
			return getDriver().findElement(By.id(id)).isSelected();
		}
		
		// ** Combos ** //
		
		public void selecionaOpcaoCombo (String id, String text) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			combo.selectByVisibleText(text);
		}
		
		public String obterValorSelecionadoCombo (String id) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			return combo.getFirstSelectedOption().getText();
		}
		
		public void verificaOpcoesCombo (String id) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			@SuppressWarnings("unused")
			List opcoes = (List) combo.getOptions();
		}
		
		public int obterQtdeDispCombo (String id) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			java.util.List<WebElement> opcoes = combo.getOptions();
			return opcoes.size();
		}
		
		public boolean obterOpcoesComboPorLoopFor (String id, String texto) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			java.util.List<WebElement> opcoes = combo.getOptions();
			
			boolean encontrou = false;
			for(WebElement option: opcoes) {
				if(option.getText().equals(texto)) {
					encontrou = true;
					break;
					
				}
			}
			return encontrou;
		}
		
		public void selecionaOpcoesMultiplasCombo (String id, String text) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			combo.selectByVisibleText(text);
		}
		
		public int obterQtdeOpcoesSelecionadasCombo (String id) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			java.util.List<WebElement> todasOpcoesSelecionadas = combo.getAllSelectedOptions();
			return todasOpcoesSelecionadas.size();
		}
		
		public void removerOpcaoSelecionada (String id, String text) {
			WebElement element = getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			combo.deselectByVisibleText(text);
		}
		
		public void selecionarComboPrime(String radical, String valor) {
			clicarRadio(By.xpath("//label[@id='"+radical+"']/..//span"));
			clicarRadioButton(""+valor+"");
		}
		
		public void selecionaOpcaoCombo(By by) {
			getDriver().findElement(by).click();;
		}

			
		
		// ** Botões ** //
		
		public void clicarBotao(String id) {
			getDriver().findElement(By.id(id)).click();
		}
		
		// ** Links ** //
		
		public void clicarLink(String link) {
			getDriver().findElement(By.linkText(link)).click();
		}
		
		// ** Tags ** //
		
		public String obterTextoTag (By by) {
			return getDriver().findElement(by).getText();	
			
		}
		
		public String obterTexto (String id) {
			return getDriver().findElement(By.xpath(id)).getText();

		}
		
		public void clicaAlertSimples (String id) {
			getDriver().findElement(By.id(id)).click();
		}
		
		public void clicaConfirmSimples (String id) {
			getDriver().findElement(By.id(id)).click();
		}
		
		public void clicaPrompt (String id) {
			getDriver().findElement(By.id(id)).click();
		}
		
		public void escrevePrompt(String texto){
			getDriver().switchTo().alert().sendKeys(texto);
		}
		
		public void aceitaAlert() {
			getDriver().switchTo().alert().accept();
		}
		
		public void aceitaConfirm() {
			getDriver().switchTo().alert().accept();
		}
		
		public void aceitaPrompt() {
			getDriver().switchTo().alert().accept();
		}
		
		public void negaAlert() {
			getDriver().switchTo().alert().dismiss();
		}
		
		public void negaConfirm() {
			getDriver().switchTo().alert().dismiss();
		}
		
		public void negaPrompt() {
			getDriver().switchTo().alert().dismiss();
		}
		
		public String obterTextoAlert() {
			return getDriver().switchTo().alert().getText();
		}
		
		public String obterTextoConfirm() {
			return getDriver().switchTo().alert().getText();
		}
		
		public String obterTextoPrompt() {
			return getDriver().switchTo().alert().getText();
		}
		
		public void acessaIframe (String idframe) {
			getDriver().switchTo().frame(idframe);
		}
		
		public void clicaBotaoIframe (String id) {
			getDriver().findElement(By.id(id)).click();
			
		}
		
		public void clicaBotaoBy(By by) {
			getDriver().findElement(by).click();
		}
		
		// ** Alerts ** \\
		
		public String obterTextoFrame() {
			return getDriver().switchTo().alert().getText();
			
		}
		
		public void aceitaIframeAlert() {
			getDriver().switchTo().alert().accept();
		}
		
		public void populaCampoComAlert(String id, String texto) {
			getDriver().switchTo().defaultContent();
			getDriver().findElement(By.id(id)).sendKeys(texto);
		}
		
		// ** Pop Ups ** \\
		
		public void IterageComPopUp(String id, String title, String tagname, String text) {
			getDriver().findElement(By.id(id)).click();
			getDriver().switchTo().window(title);
			getDriver().findElement(By.tagName(tagname)).sendKeys(text);
		}
		
		public String obterTextoPopUp(String tagName) {
			return getDriver().findElement(By.tagName(tagName)).getAttribute("value");
			
		}
		
		public void retornaParaJanelaPrincipal() {
			getDriver().switchTo().window("");
		}
		
		public void IterageComPopUpSemTitulo(String id) {
			getDriver().findElement(By.id(id)).click();
			
		}
		
		public void obterConteudoWindowHandles(int arrayNum) {
			getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[arrayNum]);
		}
		
		public void escreverTextArea(String tagName, String texto) {
			getDriver().findElement(By.tagName(tagName)).sendKeys(texto);
		}
		
		public void webSite(String url) {
			getDriver().get(url);
		}
		
		public String tituloPagina() {
			return getDriver().getTitle();
		}
		
		public void navegadorChrome() {
			
			getDriver();
		}
		
		public void navegadorFireFox() {
			
			getDriver();
		}
		
		public void navegadorInternetExplorer() {
			
			getDriver();
		}
		
		public void resolucaoPositionxPosition(Integer pos1, Integer pos2) {
			getDriver().manage().window().setPosition(new Point(pos1,pos2));
		}
		
		public void resolucaoDimensionxDimension(Integer dim1, Integer dim2) {
			getDriver().manage().window().setSize(new Dimension(dim1, dim2));
		}
		
		public void resolucaoMax() {
			getDriver().manage().window().maximize();
		}
		
		public void fechaNavegador() {
			killDriver();
		}
		
		public void limpaConteudoIdCampo (String idcampo) {
			getDriver().findElement(By.id(idcampo)).clear();
		}
		
		/*********** JS **********/
		
		 public Object executarJS (String cmd, Object... param) {
			 
			 JavascriptExecutor js = (JavascriptExecutor) getDriver();
				return js.executeScript(cmd, param);
				
		 }
		 
		 /*********** TABELA **********/
		 
		 public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
			 // procurar coluna do registro
			 WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
			 int idColuna = obterIndiceColuna(colunaBusca, tabela);				 
			 
			 
			 // encontrar a linha do registro
			 
			 int idLinha =  obterIndiceLinha(valor, tabela, idColuna);
			 
			 // procurar a coluna do botão
			 int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
			 
			 // clicar no botão da célular encontrada
			 WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
			 celula.findElement(By.xpath(".//input")).click();
			 
		 }

		protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
			java.util.List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
			int idLinha = -1;
			 for(int i = 0; i < linhas.size(); i ++) {
				 if(linhas.get(i).getText().equals(valor)) {
					 idLinha = i+1;
					 break;
					 
				 }
				 
			 }
			 return idLinha;
		}

		protected int obterIndiceColuna(String coluna, WebElement tabela) {
			java.util.List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
			 int idColuna = -1;
			 for(int i = 0; i < colunas.size(); i ++) {
				 if(colunas.get(i).getText().equals(coluna)) {
					 idColuna = i+1;
					 break;
					 
				 }
				 
			 }
			 return idColuna;
		}
		
		//*****************ESPERAS************//
		
		public void aguardaTextoVisivel(By by, String texto) {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.textToBe(by, texto));
		}
		
		public void aguardaCampoClicavel(By by) {
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		}
		
		public void executaScroll() {
			scroll = (JavascriptExecutor) getDriver();
			scroll.executeScript("javascript:window.scrollBy(0,250)");
		}


}

		
			

