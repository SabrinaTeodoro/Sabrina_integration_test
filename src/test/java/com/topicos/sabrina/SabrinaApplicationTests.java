package com.topicos.sabrina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.topicos.sabrina.dtos.CostumerDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SabrinaApplicationTests {
	
	private ObjectMapper mapper = new ObjectMapper();
	private final String url = "http://localhost:8084/api/customers";
	
	@Test
	public void postNewCustomerSuccess() {
		CostumerDTO newCustomer = new CostumerDTO(12345678910L, "João da Silva", new Date(), "Masculino", "joao@example.com", 0.05f, true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(newCustomer)
	        .post(url);

	    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	   	
	}
	
	@Test
	public void createCustomerExists() {
		CostumerDTO customerExists = new CostumerDTO(12345678910L, "João da Silva", new Date(), "Masculino", "joao@example.com", 0.05f, true);
	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(customerExists)
	        .post(url);
	    
	    assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode());
	}


	
	@Test
	public void createCustomerInvalidJSON() {
		CostumerDTO invalidCustomer = new CostumerDTO(12345678910L, "Maria da Silva", new Date(), "", "maria@example.com", 0.08f, true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(invalidCustomer)
	        .post(url);

	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	    
	}
	
	@Test
	public void activateCustomer() {
		Long cpf = 12345678910L;
		CostumerDTO customer = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .get(url + "/" + cpf)
	        .as(CostumerDTO.class);

	    customer.setActive(false);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(customer)
	        .put(url + "/" + cpf);
	    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());

	    CostumerDTO customerUpdated = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .get(url + "/" + cpf)
	        .as(CostumerDTO.class);
	    assertNotEquals(customer, customerUpdated.isActive());
	    
	}
	
	@Test
	public void customerNonExistent() {
		Long cpf = 12345678911L;
		
		CostumerDTO customer = new CostumerDTO(cpf, "Maria da Silva", new Date(), "", "maria@example.com", 0.08f, true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(customer)
	        .put(url + "/" + cpf);
	    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());

	    
	}

}

