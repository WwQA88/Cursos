package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import org.junit.Test;
import io.restassured.http.ContentType;

//import io.restassured.http.ContentType;

public class EnviaT1QapterClaims {
	
	@Test
	public void EnviaT1QC() {
		
	

		
		given()
			.header("SOAPAction","")
			.log().all()
			.contentType(ContentType.XML)
			.body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
					+ "   <Body>\r\n"
					+ "      <createTaskRequest xmlns=\"http://serviceinterface_v1.b2b.audatex.com\">\r\n"
					+ "         <parameter>\r\n"
					+ "            <name>loginId</name>\r\n"
					+ "            <value>washington.almeida.insurance</value>\r\n"
					+ "         </parameter>\r\n"
					+ "         <parameter>\r\n"
					+ "            <name>password</name>\r\n"
					+ "            <value>Brasil</value>\r\n"
					+ "         </parameter>\r\n"
					+ "         <parameter>\r\n"
					+ "            <name>markForDownload</name>\r\n"
					+ "            <value>true</value>\r\n"
					+ "         </parameter>\r\n"
					+ "         <payload>\r\n"
					+ "            <Task xmlns=\"http://www.audatex.com/SAXIF\">\r\n"
					+ "               <Versions>\r\n"
					+ "                  <Generator>AXN</Generator>\r\n"
					+ "               </Versions>\r\n"
					+ "               <ClaimNumber>WAS111120201102</ClaimNumber>\r\n"
					+ "            </Task>\r\n"
					+ "         </payload>\r\n"
					+ "      </createTaskRequest>\r\n"
					+ "   </Body>\r\n"
					+ "</Envelope>")
		.when()
			.post("https://www-i4ref.audatex.net/b2b/services/TaskService_v1?WSDL")
		.then()
			.log().all()
			.statusCode(200)
			;

	}
}





	
	
	
