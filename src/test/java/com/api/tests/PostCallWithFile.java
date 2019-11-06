package com.api.tests;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithFile {
	
	@Test
	public void getTokenPOSTWithJsonFileTest(){
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		request.contentType("application/json");
		
		File file = new File("/Users/ilhanturkmen/Documents/workspace/"
				+ "RestAssuredFramework/src/test/java/com/api/tests/credentials.json");
		request.body(file);
		Response response = request.post("/auth");
		System.out.println(response.prettyPrint());
		
		
	}
	
	@Test
	public void getTokenPOSTWithInvalidJsonFileTest(){
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		request.contentType("application/json");
		
		File file = new File("/Users/ilhanturkmen/Documents/workspace/"
				+ "RestAssuredFramework/src/test/java/com/api/tests/invalidCredentials.json");
		request.body(file);
		Response response = request.post("/auth");
		System.out.println(response.prettyPrint());
		
		
	}

}
