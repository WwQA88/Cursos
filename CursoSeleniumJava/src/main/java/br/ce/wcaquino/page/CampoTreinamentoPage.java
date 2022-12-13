package br.ce.wcaquino.page;


import br.ce.wcaquino.core.BasePage;


//** Aula 45 - Page Objects **\\

public class CampoTreinamentoPage extends BasePage {
	
	
	
		public void setNome(String nome) {
			
			dsl.escreve("elementosForm:nome", nome);
		}
		
		public void setSobreNome(String sobrenome) {
			dsl.escreve("elementosForm:sobrenome", sobrenome);
		}
		
		public void setGeneroMasculino() {
			dsl.clicarRadioButton("elementosForm:sexo:0");
		}
		
		public void setGeneroFeminino() {
			dsl.clicarRadioButton("elementosForm:sexo:1");
		}
		
		public void setComidaFavoritaPizza() {
			dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		}
		
		public void setEscolaridade(String valor) {
			dsl.selecionaOpcaoCombo("elementosForm:escolaridade",valor);
		}
		
		public void setEsporte(String valor) {
			dsl.selecionaOpcaoCombo("elementosForm:esportes", valor);
		}
		
		public void setEsporteMultiplo(String... valores) {
			for(String valor: valores)
			dsl.selecionaOpcaoCombo("elementosForm:esportes", valor);
		}
		
		public void setSugestoes(String sugestoes) {
			dsl.escreve("elementosForm:sugestoes", sugestoes);
		}
		
		public void cadastrar() {
			dsl.clicarBotao("elementosForm:cadastrar");
		}
		
		public void aceitaAlertaNomeObrigatorio() {
			dsl.aceitaAlert();
		}
		
		public void setComidaFavoritaCarne() {
			dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		}
		
		public void setComidaFavoritaVegetariano() {
			dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
		}
		
		public String obterResultadoCadastro() {
			return dsl.obterTexto("//*[@id='resultado']/span");
		}
		
		public String obterNomeCadastro() {
			return dsl.obterTexto("//*[@id='descNome']/span");			
		}
		
		public String obterSobreNomeCadastro() {
			return dsl.obterTexto("//*[@id='descSobrenome']/span");
		}
		
		public String obterComidaFavoritaCadastro() {
			return dsl.obterTexto("//*[@id='descComida']/span");
			
		}
		
		public String obterGeneroMasculinoCadadstro() {
			return dsl.obterTexto("//*[@id='descSexo']/span");			
		}
		
		public String obterEscolaridadeCadastro() {
			return dsl.obterTexto("//*[@id='descEscolaridade']/span");
		}
		
		public String obterEsporteCadastro() {
			return dsl.obterTexto("//*[@id='descEsportes']/span");
		}
		
		public String obterSugestoesCadastro() {
			return dsl.obterTexto("//*[@id='descSugestoes']/span");
		}
		
		public String obterAlertaNome() {
			return dsl.obterTextoAlert();
		}

}
