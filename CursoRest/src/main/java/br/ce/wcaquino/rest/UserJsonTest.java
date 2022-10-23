package br.ce.wcaquino.rest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.Matchers;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import junit.framework.Assert;
//import junit.framework.Assert;

import static io.restassured.RestAssured.*;

public class UserJsonTest {
		
	@Test
	public void deveVerificarPrimeiroNivel() {
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name",containsString("Silva"))
			.body("age", greaterThan(18))
			//.body("salary", is(1234.5678))
		;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void deveVerificarPrimeiroNivelOutrasFormas() {
		
		
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");
		
		//path - With path is allowed get through json, xml, etc
		
		System.out.println((int)response.path("id"));
		assertEquals(new Integer(1), response.path("id"));
		assertEquals(new Integer(1), response.path("%s","id"));
		
		//jsonpath - With jsonpath is allowed get only json
		
		JsonPath jpath = new JsonPath(response.asString());
		assertEquals(1, jpath.getInt("id"));
		
		//from - With jsonpath is allowed get only json
		
		int id = JsonPath.from(response.asString()).getInt("id");
		assertEquals(1, id);

	}
	
	@Test
	public void devoVerificarSegundoNivel(){
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/2")
		.then()
			.statusCode(200)
			.body("id", is(2))
			.body("name",containsString("Joaquina"))
			.body("endereco.rua", is("Rua dos bobos")) // Instance as an object ex:("endereco.rua")	
			;
	}
	
	@Test
	public void devoVerificarLista() {
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/3") // Access the website
		.then()
			.statusCode(200) // Validating if Status Code is responding "SUCCESS"
			.body("id", is(3)) // Validating the id
			.body("name",containsString("Ana")) // Validating the name
			.body("filhos", hasSize(2)) // Validating the size
			.body("filhos[0].name",is("Zezinho")) // getting the first item from array/list
			.body("filhos[1].name",is("Luizinho")) // getting second the first item from array/list
			.body("filhos.name",hasItem("Zezinho")) // Using "filhos.name" we gonna get all the collection "names" in the array/list
			.body("filhos.name",hasItems("Luizinho", "Zezinho")) // Using "filhos.name" we gonna get all the collection "names" in the array/list
			;			
		
	}
	
	@Test
	public void deveRetornarErroUsuarioInexistente() {
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/4") // Access the website
		.then()
			.statusCode(404) // Validating if Status Code is responding "ERROR"
			.body("error", is("Usuário inexistente"))
			;
	}

	@Test
	public void deveVerificarListaNaRaiz() {
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users") // Access the website
		.then()
			.statusCode(200) // Validating if Status Code is responding "SUCCESS"
			.body("", hasSize(3)) // We can use "$" or "" to indicate the root
			.body("name", hasItems("João da Silva","Maria Joaquina","Ana Júlia")) // Getting all attributes "name" in the array/list
			.body("age[1]", is(25)) // Validating "age" from index 1
			.body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho"))) // Invoking an array/list inside another array/list
			.body("salary",contains(1234.5678f, 2500, null)) // Validating all contents in the list - "f" float value
			;
			
	}
	
	@Test
	public void devoFazerVerificacoesAvancadas() {
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/users") // Access the website
		.then()
			.statusCode(200) // Validating if Status Code is responding "SUCCESS"
			.body("$", hasSize(3)) // We can use "$" or "" to indicate the root
			.body("age.findAll{it <= 25}.size()",is(2))
			.body("age.findAll{it <= 25 && it > 20}.size()",is(1))
			.body("findAll{it.age <= 25 && it.age > 20}.name",hasItem("Maria Joaquina")) // If we invoke the method using "is", it will return an error because the "findAll" returns an array/list. In Reason that we will use "hasItem" 
			.body("findAll{it.age <= 25}[0].name",is("Maria Joaquina")) // Returning an specific data using "[]"
			.body("findAll{it.age <= 25}[-1].name",is("Ana Júlia")) // Returning the last data
			.body("find{it.age <= 25}.name",is("Maria Joaquina")) // Returning the first data of the array/list
			.body("findAll{it.name.contains('n')}.name",hasItems("Maria Joaquina", "Ana Júlia")) // Verifying if has 'n' in the String
			.body("findAll{it.name.length() > 10}.name",hasItems("Maria Joaquina", "João da Silva")) // Verifying if has more than 10 characters in the String
			.body("name.collect{it.toUpperCase()}",hasItem("MARIA JOAQUINA")) // Getting the first data and transforming in Capital letter (UpperCase)
			.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}",hasItem("MARIA JOAQUINA")) // Search in all array/list, find who starts with 'Maria' and transform the String in Capital letters
			.body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", anyOf(arrayContaining("MARIA JOAQUINA"),Matchers.arrayWithSize(1)))
			.body("age.collect{it * 2}", hasItems(60, 50, 40))
			.body("id.max()", is(3))
			.body("salary.min()",is(1234.5678f))
			.body("salary.findAll{it != null}.sum()",is(closeTo(3734.5678f, 0.001)))
			.body("salary.findAll{it != null}.sum()",allOf(greaterThan(3000d), lessThan(5000d)))
			;
		
	}
	
	@Test
	public void devoUnirJsonPathcomJava() {
		
		ArrayList<String> names = 
		given()
		.when()
			.get("http://restapi.wcaquino.me/users") // Access the website
		.then()
			.statusCode(200) // Validating if Status Code is responding "SUCCESS"
			.extract().path("name.findAll{it.startsWith('Maria')}")
			;
		assertEquals(1, names.size());
		assertTrue(names.get(0).equalsIgnoreCase("mArIa Joaquina"));
		assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase());
	}
	
}
