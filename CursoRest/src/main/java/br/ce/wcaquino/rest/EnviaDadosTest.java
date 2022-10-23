package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;

public class EnviaDadosTest {
	
	@Test
	public void devoEnviarValorViaQuery() {
		
		given()
			.log().all()
		.when()
			.get("http://restapi.wcaquino.me/v2/users?format=xml")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.XML)
		;
		
	}

	
	@Test
	public void devoEnviarValorViaQueryViaParam() {
		
		given()
			.log().all()
			.queryParam("format", "xml") // Send attributes
		.when()
			.get("http://restapi.wcaquino.me/v2/users")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.XML)
			.contentType(containsString("utf-8"))
		;
		
	}
	
	@Test
	public void devoEnviarValorViaHeader() {
		
		given()
			.log().all()
			.accept(ContentType.XML) //Just to confirm if the data send is the request. Attention, do not make confuse with ContentType
		.when()
			.get("http://restapi.wcaquino.me/v2/users")
		.then()
			.log().all()
			.statusCode(200)
			.contentType(ContentType.XML)
		;
		
	}
}
