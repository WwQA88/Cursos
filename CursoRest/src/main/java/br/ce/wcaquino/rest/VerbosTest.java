package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

public class VerbosTest {
	
	@Test
	public void deveSalvarUsuario() {
		
		given()
			.log().all()
			.contentType("application/json")
			.body("{\"name\": \"Jose\",\"age\": 50}")
		.when()
			.post("http://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Jose"))
			.body("age", is(50))
		;
		
	}
	
	@Test
	public void naoDeveSalvarUsuarioSemNome() {
		
		given()
			.log().all()
			.contentType("application/json")
			.body("{\"age\": 50}")
		.when()
			.post("http://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(400)
			.body("id", is(nullValue()))
			.body("error", is("Name é um atributo obrigatório"))
		;
		
	}
	
	// Aula 27 - POST com XML
	
	@Test
	public void deveSalvarUsuarioViaXML() {
		
		given()
			.log().all()
			.contentType(ContentType.XML) // Another way to inform "Content Type"
			.body("<user><name>Jose</name><age>50</age></user>")
		.when()
			.post("http://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.body("user.@id", is(notNullValue()))
			.body("user.name", is("Jose"))
			.body("user.age", is("50"))
		;
		
	}
	
	// Aula 28 - Alterando com PUT
	
	@Test
	public void deveAlterarUsuario() {
		
		given()
			.log().all()
			.contentType("application/json")
			.body("{\"name\": \"Usuario Alterado\",\"age\": 80}")
		.when()
			.put("http://restapi.wcaquino.me/users/1")
		.then()
			.log().all()
			.statusCode(200)
			.body("id", is(1))
			.body("name", is("Usuario Alterado"))
			.body("age", is(80))
			.body("salary", is(1234.5678f))
			
		;
		
	}
	
	// Aula 30 - URL Parametrizável
	
		@Test
		public void devoCustomizarURL() {
			
			given()
				.log().all()
				.contentType("application/json")
				.body("{\"name\": \"Usuario Alterado\",\"age\": 80}")
			.when()
				.put("http://restapi.wcaquino.me/{entidade}/{UserId}","users","1")
			.then()
				.log().all()
				.statusCode(200)
				.body("id", is(1))
				.body("name", is("Usuario Alterado"))
				.body("age", is(80))
				.body("salary", is(1234.5678f))
				
			;
			
		}
		
		@Test
		public void devoCustomizarURLParte2() {
			
			given()
				.log().all()
				.contentType("application/json")
				.body("{\"name\": \"Usuario Alterado\",\"age\": 80}")
				.pathParam("entidade", "users")
				.pathParam("UserId", 1)
			.when()
				.put("http://restapi.wcaquino.me/{entidade}/{UserId}")
			.then()
				.log().all()
				.statusCode(200)
				.body("id", is(1))
				.body("name", is("Usuario Alterado"))
				.body("age", is(80))
				.body("salary", is(1234.5678f))
				
			;
			
		}
		
	// Aula 30 - Removendo com DELETE
		
		@Test
		public void deveRemoverUsuario() {
			
			given()
				.log().all()
			.when()
				.delete("http://restapi.wcaquino.me/users/1")
			.then()
				.log().all()
				.statusCode(204)
				
			;
			
		}
		
		@Test
		public void naoDeveRemoverUsuarioInexistente() {
			
			given()
				.log().all()
			.when()
				.delete("http://restapi.wcaquino.me/users/1000")
			.then()
				.log().all()
				.statusCode(400)
				.body("error", is("Registro inexistente"))
				
			;
			
		}
	
	// Aula 31 - Serializando com Map (Convertendo Map para Json)
	
	@Test
	public void deveSalvarUsuarioUsandoMap() {
		
		Map<String, Object> params = new HashMap<String, Object>(); // The "Map" is a list of pairs
		params.put("name", "Usuario Via Map");
		params.put("age", 25);
		
		given()
			.log().all()
			.contentType("application/json")
			.body(params)
		.when()
			.post("http://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario Via Map"))
			.body("age", is(25))
		;
		
	}
	
	// Aula 32 - Serializando o objeto (Convertendo um Objeto para Json)
	
	@Test
	public void deveSalvarUsuarioUsandoObjeto() {
		
		User user = new User("Usuario Via Objeto", 35);

		
		given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("http://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario Via Objeto"))
			.body("age", is(35))
		;
		
	}
	
	// Aula 33 - Deserializando um Objeto (Convertendo o Json da requisição para um objeto)
	
	@Test
	public void deveDeserializarObjetoAoSalvarUsuario() {
		
		User user = new User("Usuario Deserializado", 35);

		
		User usuarioInserido = given()
			.log().all()
			.contentType("application/json")
			.body(user)
		.when()
			.post("http://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(User.class)
		;
		
		System.out.println(usuarioInserido);
		
		Assert.assertThat(usuarioInserido.getId(), is(notNullValue()));
		assertEquals("Usuario Deserializado", usuarioInserido.getName());
		Assert.assertThat(usuarioInserido.getAge(), is(35));
		
	}

	
	// Aula 34 - Serializando para XML
	
	@Test
	public void deveSalvarUsuarioViaXMLUsandoObjeto() {
		
		User user = new User("Usuario XML", 40);
		
		given()
			.log().all()
			.contentType(ContentType.XML) // Another way to inform "Content Type"
			.body(user)
		.when()
			.post("http://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.body("user.@id", is(notNullValue()))
			.body("user.name", is("Usuario XML"))
			.body("user.age", is("40"))
		;
		
	}
	
	// Aula 35 - Desarializando para XML
	
	@Test
	public void deveDeserializarXMLAoSalvarUsuario() {
		
		User user = new User("Usuario XML", 40);
		
		User usuarioInserido = given()
			.log().all()
			.contentType(ContentType.XML) // Another way to inform "Content Type"
			.body(user)
		.when()
			.post("http://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.extract().body().as(User.class)
		;
		
		//System.out.println(usuarioInserido);
		Assert.assertThat(usuarioInserido.getId(), is(notNullValue()));
		Assert.assertThat(usuarioInserido.getName(), is("Usuario XML"));
		Assert.assertThat(usuarioInserido.getAge(), is(40));
		Assert.assertThat(usuarioInserido.getSalary(), is(nullValue()));
	}
	
	
}

