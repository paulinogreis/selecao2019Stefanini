package com.paulinogreis.selecao2019;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testng.Assert;

import com.paulinogreis.selecao2019.dominio.enums.TipoPessoa;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePost_Get_Delete_Put_Pessoas {

	@Test
	public void testarSePessoaFoiAdicionadaNaBase() {
		RequestSpecification request = RestAssured.given();
		JSONObject json = new JSONObject();

		request.header("Content-Type", "application/json");

		json.put("nome", "Maria helena");
		json.put("email", "mariahelena@gmail.com");
		json.put("tipoPessoa", TipoPessoa.PESSOAFISICA);
		json.put("cpfCnpj", "34576589500");

		request.body(json.toJSONString());

		Response response = request.post("http://localhost:8080/pessoas");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 201);
	}

	@Test
	public void testarSePessoaFoiAtualizadaNaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();

		json.put("id", "4");
		json.put("nome", "Maria Helena Silva");
		json.put("email", "maria.helena@gmail.com");
		json.put("cpfCnpj", "76498742588");
		json.put("tipo", TipoPessoa.PESSOAFISICA);

		request.body(json.toJSONString());

		Response response = request.put("http://localhost:8080/pessoas/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 204);
	}

	@Test
	public void testarSePessoaFoiEncontradaNaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Response response = request.get("http://localhost:8080/pessoas/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 200);
	}

	@Test
	public void testarSePessoaFoiExcluidaDaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Response response = request.delete("http://localhost:8080/pessoas/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 204);
	}
}
