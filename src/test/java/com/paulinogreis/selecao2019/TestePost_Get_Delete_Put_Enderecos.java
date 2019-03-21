package com.paulinogreis.selecao2019;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePost_Get_Delete_Put_Enderecos {

	@Test
	public void testarSeEnderecoFoiAdicionadoNaBase() {
		RequestSpecification request = RestAssured.given();
		JSONObject json = new JSONObject();

		request.header("Content-Type", "application/json");

		json.put("logradouro", "Condominio Morada dos Nobres");
		json.put("numero", "43");
		json.put("complemento", "Conjunto morada 1");
		json.put("bairro", "Sobradinho");
		json.put("cep", "73252200");
		json.put("pessoaId", 1);
		json.put("cidadeId", 1);

		request.body(json.toJSONString());

		Response response = request.post("http://localhost:8080/enderecos");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 201);
	}

	@Test
	public void testarSeEnderecoFoiAtualizadoNaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();

		json.put("id", "4");
		json.put("logradouro", "Condominio Morada dos Nobres");
		json.put("numero", "65");
		json.put("complemento", "Conjunto morada 3");
		json.put("bairro", "Sobradinho");
		json.put("cep", "75432900");
		json.put("pessoaId", 1);
		json.put("cidadeId", 1);

		request.body(json.toJSONString());

		Response response = request.put("http://localhost:8080/enderecos/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 204);
	}

	@Test
	public void testarSeEnderecoFoiEncontradoNaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Response response = request.get("http://localhost:8080/enderecos/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 200);
	}

	@Test
	public void testarSeEnderecoFoiExcluidoDaBase() {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Response response = request.delete("http://localhost:8080/enderecos/4");

		int code = response.getStatusCode();

		Assert.assertEquals(code, 204);
	}
}
