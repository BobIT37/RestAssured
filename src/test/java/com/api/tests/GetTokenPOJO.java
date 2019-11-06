package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetTokenPOJO {
	
	@Test
	public void getTokenWithPOJOTest(){
		
		String credJason = null;
//		String credJason2 = null;
//		String credJason3 = null;
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		request.contentType("application/json");
		
		Credentials cred = new Credentials("admin", "password123");
//		Credentials cred2 = new Credentials("naveen", "password123");
//		Credentials cred3 = new Credentials("tom", "password123");
		
		//Pojo to json conversion:
		//Jackson library
		//jackson-databind maven
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			credJason = mapper.writeValueAsString(cred);
//			credJason2 = mapper.writeValueAsString(cred2);
//			credJason3 = mapper.writeValueAsString(cred3);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Credentials jason payload: " + credJason);
//		System.out.println("Credentials jason payload: " + credJason2);
//		System.out.println("Credentials jason payload: " + credJason3);
		
		//hit the API
		request.body(credJason);
		Response response = request.post("/auth");
		
		System.out.println(response.prettyPrint());
		//System.out.println(response.statusCode());
		
		Assert.assertEquals(response.statusCode(), HTTPStatus.HTTP_STATUS_CODE_200);
		
		JsonPath js = response.jsonPath();
		String tokenID = js.get("token");
		System.out.println("token id is: "+ tokenID);
		
		Assert.assertNotNull(tokenID);
	
	}
	
	@Test
	public void tokenAPIWithWrongCredentialsTest(){
		String credJason = null;
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		request.contentType("application/json");
		
		Credentials cred = new Credentials("admin", "admin");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			credJason = mapper.writeValueAsString(cred);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Credentials jason payload: " + credJason);

		
		//hit the API
		request.body(credJason);
		Response response = request.post("/auth");
		
		System.out.println(response.prettyPrint());
		//System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), HTTPStatus.HTTP_STATUS_CODE_200);
		
		
		JsonPath js = response.jsonPath();
		String messg = js.get("reason");
		System.out.println("reason is "+ messg);
		
		Assert.assertEquals(messg, HTTPStatus.WRONG_CREDENTIALS_MESSAGE);
		
	}
	
	@Test
	public void getTokenAPIWithBadJsonPayLoadTest(){
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		String payload = "{\"username\" : \"admin\" \"password\" : \"password123\"}"; //we removed comma dont forget
		
		request.contentType("application/json");
		request.body(payload);
		Response response = request.post("/auth");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		
		Assert.assertEquals(response.getStatusCode(), HTTPStatus.HTTP_STATUS_CODE_400);
		
	}

}
