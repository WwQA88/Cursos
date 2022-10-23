package br.ce.wcaquino.rest;

	
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="CiaSeguros")
@XmlAccessorType(XmlAccessType.FIELD)

	public class SerealizaXML2 {
		
		@XmlAttribute
		private String CGCCIA;
		@XmlAttribute
		private String CODCIA;
		
		public SerealizaXML2() {
			// TODO Auto-generated constructor stub
		}

		public SerealizaXML2(String cGCCIA, String cODCIA) {
			super();
			CGCCIA = cGCCIA;
			CODCIA = cODCIA;
		}

		public String getCGCCIA() {
			return CGCCIA;
		}

		public void setCGCCIA(String cGCCIA) {
			CGCCIA = cGCCIA;
		}

		public String getCODCIA() {
			return CODCIA;
		}

		public void setCODCIA(String cODCIA) {
			CODCIA = cODCIA;
		}

	
}
