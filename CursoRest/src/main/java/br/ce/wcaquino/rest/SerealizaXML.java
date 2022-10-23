package br.ce.wcaquino.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="IntegracaoEntradaCommon")
@XmlAccessorType(XmlAccessType.FIELD)
public class SerealizaXML{	
	
	@XmlElement(name="CiaSeguros")
	private SerealizaXML2 CiaSeguros;
	
	
	public SerealizaXML2 getCiaSeguros() {
		return CiaSeguros;
	}


	public void setCiaSeguros(SerealizaXML2 ciaSeguros) {
		CiaSeguros = ciaSeguros;
	}


	@XmlElement(name="Apolice")
	private SerealizaXML3 Apolice;


	public SerealizaXML3 getApolice() {
		return Apolice;
	}


	public void setApolice(SerealizaXML3 apolice) {
		Apolice = apolice;
	}
	
	@XmlElement(name="Segurado")
	private SerealizaXML4 Segurado;
	

	public SerealizaXML() {
		// TODO Auto-generated constructor stub
	}


	public SerealizaXML(String ciaSeguros, String cODCIA, String apolice, String nUMPROCESSO, String telComercial) {
		super();
		CiaSeguros = new SerealizaXML2(ciaSeguros, cODCIA);
		Apolice = new SerealizaXML3(apolice, nUMPROCESSO);
		Segurado = new SerealizaXML4(telComercial);
	}
	
	
	
	
}