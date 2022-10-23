package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.page.CampoTreinamentoPage;

//** Aula 47 - Testes parametrizáveis / TDD **\\

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {
			
	protected DSL dsl;			
	private CampoTreinamentoPage page;
		
			
			
			@Parameter
			public String nome;
			@Parameter(value = 1)
			public String sobreNome;
			@Parameter(value = 2)
			public String sexo;
			@Parameter(value = 3)
			public List<String> comidas;
			@Parameter(value = 4)
			public String[] esportes;
			@Parameter(value = 5)
			public String msg;
	
				@Before
				public void inicializa() {
					
					
					getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html" );
					getDriver().manage().window().maximize();
					dsl = new DSL();
					page = new CampoTreinamentoPage();
					
					
					
				}
				
				
				
				@Parameters
				public static Collection<Object[]> getCollection(){
					return Arrays.asList(new Object[][] {
						
					{"","","", Arrays.asList(), new String[]{},"Nome eh obrigatorio"},
					{"Washington","","", Arrays.asList(), new String[]{},"Sobrenome eh obrigatorio"},
					{"Washington","Almeida","", Arrays.asList(), new String[]{},"Sexo eh obrigatorio"},
					{"Washington","Almeida","Masculino", Arrays.asList("Carne","Vegetariano"), new String[]{},"Tem certeza que voce eh vegetariano?"},
					{"Washington","Almeida","Feminino", Arrays.asList("Carne"), new String[]{"Futebol","O que eh esporte?"},"Voce faz esporte ou nao?"},
					
					
				});
			}
	

	
				@Test
				public void deveValidarRegras() {
		
							// value 0		
							page.setNome(nome);
							
							// value 1
							page.setSobreNome(sobreNome);
							
							// value 2
							if(sexo.equals("Masculino")) {
								page.setGeneroMasculino();
							}
								if(sexo.equals("Feminino")) {
									page.setGeneroFeminino();
								}
								
							// value 3
							if(comidas.contains("Carne"))
								page.setComidaFavoritaCarne();
									if(comidas.contains("Pizza"))
										page.setComidaFavoritaPizza();
											if(comidas.contains("Vegetariano"))
												page.setComidaFavoritaVegetariano();
											
							
							// value 4
							page.setEsporteMultiplo(esportes);
									page.cadastrar();			
																	
									System.out.println(msg);
									
							// value 5
							Assert.assertEquals(msg, page.obterAlertaNome());
							page.aceitaAlertaNomeObrigatorio();
	}

}
