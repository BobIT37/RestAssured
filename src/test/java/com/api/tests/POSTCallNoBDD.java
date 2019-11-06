package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTCallNoBDD {
	
	
	@Test
	public void tokenPostAPITest(){
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		String payload = "{\"username\" : \"admin\", \"password\" : \"password123\"}";
		
		request.contentType("application/json");
		request.body(payload);
		Response response = request.post("/auth");
		
		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());
		
		JsonPath js = response.jsonPath();
		js.get("token");
		
		String tokenId = js.get("token");
		System.out.println("token is is "+ tokenId);
		Assert.assertNotNull(tokenId);
		
		//POJO or DTO
		
		
		
	}

}
