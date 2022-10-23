package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import org.junit.Test;
import io.restassured.http.ContentType;

public class T1BradescoViaXML {
	
	@Test
	public void EnviaT1() {
	
		SerealizaXML var1 = new SerealizaXML("111","222","456","555","98455671");
		
	    given()
			.log().all()
			.contentType(ContentType.XML)
			.body(var1)
		.when()
			.post("http://audabridgehm.audatex.com.br/AudaBridgeServiceBradesco/IntegracaoAudaBridgeBradesco.svc")
		.then()
			.statusCode(201)
			;
			

	}
}