package br.ce.wcaquino.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Segurado")
@XmlAccessorType(XmlAccessType.FIELD)

public class SerealizaXML4 {
	
	private String TelComercial;
	
	public SerealizaXML4() {
		// TODO Auto-generated constructor stub
	}

	public SerealizaXML4(String telComercial) {
		super();
		TelComercial = telComercial;
	}

	public String getTelComercial() {
		return TelComercial;
	}

	public void setTelComercial(String telComercial) {
		TelComercial = telComercial;
	}
	
	


	

}
