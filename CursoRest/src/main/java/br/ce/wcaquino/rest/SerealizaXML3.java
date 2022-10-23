package br.ce.wcaquino.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Apolice")
@XmlAccessorType(XmlAccessType.FIELD)

public class SerealizaXML3 {

	@XmlAttribute
	private String NUMAPOLICE;
	@XmlAttribute
	private String NUMPROCESSO;
	
	public SerealizaXML3() {
		// TODO Auto-generated constructor stub
	}

	public SerealizaXML3(String nUMAPOLICE, String nUMPROCESSO) {
		super();
		NUMAPOLICE = nUMAPOLICE;
		NUMPROCESSO = nUMPROCESSO;
	}

	public String getNUMAPOLICE() {
		return NUMAPOLICE;
	}

	public void setNUMAPOLICE(String nUMAPOLICE) {
		NUMAPOLICE = nUMAPOLICE;
	}

	public String getNUMPROCESSO() {
		return NUMPROCESSO;
	}

	public void setNUMPROCESSO(String nUMPROCESSO) {
		NUMPROCESSO = nUMPROCESSO;
	}
	
	
	
}
