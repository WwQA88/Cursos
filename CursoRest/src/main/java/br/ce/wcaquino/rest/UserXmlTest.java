package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserXmlTest {
	
	public static RequestSpecification reqSpec;
	public static ResponseSpecification respSpec;
	

	@BeforeClass
	public static void setup() {
		
		// Defining static attributes for baseURI, port and basePath

		RestAssured.baseURI = "https://restapi.wcaquino.me";
		// or
		RestAssured.port = 443; // 443 for https or 80 for http
		// or
		RestAssured.basePath = ""; // Example: /Api / V2
		
		// Defining static attributes for Response and Request
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL); // Can log other things, example: BODY, COOKIE, HEADERS, METHODS, etc...
		reqSpec = reqBuilder.build();
		
		ResponseSpecBuilder respBuilder = new ResponseSpecBuilder();
		respBuilder.expectStatusCode(200);
		respSpec = respBuilder.build();
		
		// When we define using RestAssured, is not necessary put ".spec"
		
		RestAssured.requestSpecification = reqSpec;
		RestAssured.responseSpecification = respSpec;
		
		
		
		

		
	}
	
	@Test
	public void devoTrabalharcomXML() {
		
		// For Xml is necessary navigate from object to object, ex: user.filhos.name...
		
		given()
		.when()
			.get("usersXML/3")
		.then()
			.statusCode(200)
			.body("user.name", is("Ana Julia"))
			.body("user.@id", is("3")) // When has attribute, ex: id="3", the usage is @ before attribute
			.body("user.filhos.name.size()", is(2))
			.body("user.filhos.name[0]", is("Zezinho"))
			.body("user.filhos.name[1]", is("Luizinho"))
			.body("user.filhos.name", hasItem("Luizinho"))
			.body("user.filhos.name", hasItems("Luizinho","Zezinho"))
			;
	}
	
	@Test
	public void devoConhercerNohRaiz() {
		
		
		// For Xml when we define a "rootPath" we can cut path.
		//Instead of uses user.name... We define "user" in root and continue, ex: user...
		
		given()
		.when()
			.get("/usersXML/3")
		.then()
			.statusCode(200)
			
			.rootPath("user")
			.body("name", is("Ana Julia"))
			.body("@id", is("3")) // When has attribute, ex: id="3", the usage is @ before attribute
			
			.rootPath("user.filhos") // The same occurs when we define a second rootPath, ex: user.filhos...
			.body("name.size()", is(2))
			
			.detachRootPath("filhos") // If I want to removed a value, ex: "filhos", I have to use the "detachRootPath" and now I'm must redefine and use filhos.name...
			.body("filhos.name[0]", is("Zezinho"))
			.body("filhos.name[1]", is("Luizinho"))
			
			.appendRootPath("filhos") // If I want to add a value again, ex: "filhos", I have to use the "appendRootPath" and now I'm must redefine and use name...
			.body("name", hasItem("Luizinho"))
			.body("name", hasItems("Luizinho","Zezinho"))
			;
		
		
	}
	
	@Test
	public void devoFazerPesquisaAvancadaComXML() {
		
		given()
		.when()
			.get("/usersXML")
		.then()
			.statusCode(200)
			.body("users.user.size()", is(3))
			.body("users.user.findAll{it.age.toInteger() <= 25}.size()",is(2))
			.body("users.user.@id", hasItems("1", "2", "3"))
			.body("users.user.find{it.age == 25}.name",is("Maria Joaquina"))
			.body("users.user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina","Ana Julia"))
			.body("users.user.salary.find{it != null}.toDouble()",is(1234.5678d)) // If I want to treat as Double
			.body("users.user.salary.find{it != null}",is("1234.5678")) // Conversion not necessary
			.body("users.user.age.collect{it.toInteger() * 2}", hasItems(60, 50, 40))
			.body("users.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}",is("MARIA JOAQUINA"))
			;
		
		
	}	
	
	@Test
	public void devoFazerPesquisaAvancadaComXMLEJava() {
		
		ArrayList<NodeImpl> nomes = given()// This type of array/list didn't returns a String. The correct one is "NodeImpl"
		.when()
			.get("/usersXML")
		.then()
			.statusCode(200)
			//.extract().path("users.user.name.findAll{it.toString().startsWith('Maria')}")
			.extract().path("users.user.name.findAll{it.toString().contains('n')}")
			;
			//System.out.println(nomes);
			assertEquals(2, nomes.size());
			assertEquals("Maria Joaquina".toUpperCase(), nomes.get(0).toString().toUpperCase());
			assertTrue("Ana Julia".equalsIgnoreCase(nomes.get(1).toString().toUpperCase())); // assetTrue returns a boolean value
			System.out.println(nomes.toString().toUpperCase());
	}
	
	@Test
	public void devoFazerPesquisaAvancadaComXpath() {
		

		given()
			//.spec(reqSpec)
		.when()
			.get("/usersXML")
		.then()
		//.statusCode(200)
		  //.spec(respSpec)
		.body(hasXPath("count(/users/user)", is("3")))
		.body(hasXPath("/users/user[@id='1']"))
		.body(hasXPath("//user[@id='2']"))
		.body(hasXPath("//name[text() = 'Luizinho']/../../name", is("Ana Julia"))) // Navigation between levels (up) "/.."
		.body(hasXPath("//name[text() = 'Ana Julia']/following-sibling::filhos",allOf(containsString("Zezinho"), containsString("Luizinho")))) // Navigation between levels (down) "//" or "folowing-sibling::"
		.body(hasXPath("/users/user/name", is("João da Silva")))
		.body(hasXPath("//name", is("João da Silva")))
		.body(hasXPath("/users/user[last()]/name", is("Ana Julia"))) // Verify the last register in the list
		.body(hasXPath("count(/users/user/name[contains(., 'n')])", is("2"))) // Verify if contains 2 name with letter 'n'
		.body(hasXPath("//user[age < 24]/name", is("Ana Julia")))
		.body(hasXPath("//user[age > 20 and age < 30]/name", is("Maria Joaquina")))
		.body(hasXPath("//user[age > 20][age < 30]/name", is("Maria Joaquina"))) //Another way to verify without "and"
		;
		
	}	
	
	@Test
	public void devoTrabalharcomAtributos() {
		

		
		
		given()
			.log().all() // Logando a requisição
		.when()
			.get("/usersXML/3")
		.then()
			.statusCode(200)
			.rootPath("user")
			.body("name", is("Ana Julia"))
			.body("@id", is("3")) // When has attribute, ex: id="3", the usage is @ before attribute
			
			.rootPath("user.filhos") // The same occurs when we define a second rootPath, ex: user.filhos...
			.body("name.size()", is(2))
			
			.detachRootPath("filhos") // If I want to removed a value, ex: "filhos", I have to use the "detachRootPath" and now I'm must redefine and use filhos.name...
			.body("filhos.name[0]", is("Zezinho"))
			.body("filhos.name[1]", is("Luizinho"))
			
			.appendRootPath("filhos") // If I want to add a value again, ex: "filhos", I have to use the "appendRootPath" and now I'm must redefine and use name...
			.body("name", hasItem("Luizinho"))
			.body("name", hasItems("Luizinho","Zezinho"))
			;
	}
	
	
	@Test
	public void devoTrabalharcomRequest_ResponseSpecification() {		
		
		
		given()
			//.spec(reqSpec)
		.when()
			.get("/usersXML/3")
		.then()
			//.statusCode(200)
			  //.spec(respSpec)
			.rootPath("user")
			.body("name", is("Ana Julia"))
			.body("@id", is("3")) // When has attribute, ex: id="3", the usage is @ before attribute
			
			.rootPath("user.filhos") // The same occurs when we define a second rootPath, ex: user.filhos...
			.body("name.size()", is(2))
			
			.detachRootPath("filhos") // If I want to removed a value, ex: "filhos", I have to use the "detachRootPath" and now I'm must redefine and use filhos.name...
			.body("filhos.name[0]", is("Zezinho"))
			.body("filhos.name[1]", is("Luizinho"))
			
			.appendRootPath("filhos") // If I want to add a value again, ex: "filhos", I have to use the "appendRootPath" and now I'm must redefine and use name...
			.body("name", hasItem("Luizinho"))
			.body("name", hasItems("Luizinho","Zezinho"))
			;
	}
	

}
