package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		Response response = request(Method.GET,"http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		//Assert.assertTrue(response.statusCode() == 201);
		Assert.assertTrue("The Status code must be 200",response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		/*Response response = RestAssured.request(Method.GET,"http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		*/
		//get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given() // Where we insert many pre conditions
		.when() // The action		
			.get("http://restapi.wcaquino.me/ola")		
		.then() // The validations / Assertions
			.statusCode(200);
		
	}
	
	
	// Versao 4.13.0 (Jan, 2020) do JUnit - Aparentemente o HamCrest não é mais utilizado.
	// O Método "Assert.assertEquals" faz o papel do "Assert.assertThat"
	//Assert.assertEquals("Maria", "Maria");
	//Assert.assertEquals(128, 115);	

	@Test
	public void devoConhecerMatchersHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class)); // Verify if is integer
		Assert.assertThat(128d, Matchers.isA(Double.class)); // Verify if is double
		Assert.assertThat(128d, Matchers.greaterThan(124d)); // Verify if is greater than (Double)
		Assert.assertThat(128d, Matchers.lessThan(130d)); // Verify if is less than (Double)
		
		// Creating "Array/list"
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9); // Array/list
		assertThat(impares, hasSize(5)); // Verify the size of content (exact size)
		assertThat(impares, contains(1,3,5,7,9)); // Verify the content of Array/list (exact content)
		assertThat(impares, containsInAnyOrder(3,9,1,7,5)); // Verify the content of Array/list (exact content, but in any order)
		assertThat(impares, hasItem(3)); // Verify if has a content of the Array/list (one by one)
		assertThat(impares, hasItems(3,1)); // Verify if has a content of the Array/list (more than one)
		
		// Creating many "matchers" nested
		
		assertThat("Maria", is(not("João"))); // Verify the negative comparison
		assertThat("Maria", not("João")); // Without "is" works the same way
		assertThat("Maria", anyOf(is("Maria"), is("Joaquina"))); // Verify condition "or" (one or other)
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qu"))); // Verify condition "and"
	
	}
	
	@Test
	public void devoValidarBody(){
		
		given() // Where we insert many pre conditions
		.when() // The action		
			.get("http://restapi.wcaquino.me/ola")		
		.then() // The validations / Assertions
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())));
		
	}



}
