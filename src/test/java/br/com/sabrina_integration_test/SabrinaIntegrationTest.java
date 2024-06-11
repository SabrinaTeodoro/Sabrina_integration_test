package br.com.sabrina_integration_test;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sabrina.order.dto.OrderDTO;
import sabrina.OrderNewDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;*/

public class SabrinaIntegrationTest {

	private ObjectMapper mapper = new ObjectMapper();
	private final String customerURL = "http://localhost:8080/api/customers";//CONFERIR

	@Test
	public void testGetOrderById() {
		int orderNumber = 1;
		try {
			Response resp = RestAssured.get(orderURL + "/" + orderNumber);
			assertEquals(HttpStatus.OK.value(), resp.getStatusCode());
			
			String jsonBody = resp.getBody().asString();
			OrderDTO order = mapper.readValue(jsonBody, OrderDTO.class);
			
			assertNotNull(order);
			assertEquals(1, order.getNumber());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			fail("Fail to get order with id: " + orderNumber, e);
		}
	}

	@Test
	public void testPostOrder() {//Sucesso
		CustomerNewDTO customer = new CustomerNewDTO(12345678912, "Maria", new Date(), "feminino", "maria@emai.com", 5.0, active);
		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(customer)
				.post(customerURL);
		assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());
	}
	@Test
	public void testPostOrder() {//Ja existe
		int orderNumber = 1;
		try {
			Response resp = RestAssured.get(orderURL + "/" + orderNumber);
			assertEquals(HttpStatus.OK.value(), resp.getStatusCode());
			
			String jsonBody = resp.getBody().asString();
			OrderDTO order = mapper.readValue(jsonBody, OrderDTO.class);
			
			assertNotNull(order);
			assertEquals(1, order.getNumber());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			fail("Fail to get order with id: " + orderNumber, e);
		}
	}

}
