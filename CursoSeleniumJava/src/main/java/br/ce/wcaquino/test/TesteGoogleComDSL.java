package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.ce.wcaquino.core.DSL;

public class TesteGoogleComDSL {
	
	
	private DSL dsl;
	
		@Before
		public void inicializa() {
		
			
			
		}
		
		@After
		public void encerra() {
			dsl.fechaNavegador();
		}
		
		
			@Test
			public void testeFireFoxDSL() {
				
				dsl.navegadorFireFox();
				dsl.resolucaoPositionxPosition(100, 100);				
				dsl.webSite("http://www.google.com");
				Assert.assertEquals("Google", dsl.tituloPagina());
				
				
			}
			
			@Test
			public void testeChromeDSL() {
				
				dsl.navegadorChrome();
				dsl.resolucaoDimensionxDimension(1200, 765);				
				dsl.webSite("http://www.google.com");
				Assert.assertEquals("Google", dsl.tituloPagina());				
				
				
			}
			
			@Test
			public void testeInternetExplorerDSL() {
				
				dsl.navegadorInternetExplorer();
				dsl.resolucaoMax();
				dsl.webSite("http://www.google.com");
				Assert.assertEquals("Google", dsl.tituloPagina());
				
				
				
			}
			

}
