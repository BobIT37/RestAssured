package com.api.tests;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUTAPICall {
	
	//Update the resources data
	
	@Test
	public void updateUserWithPUTTest(){
	
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given().log().all(); //log is used to get more info
		request.header("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
		request.contentType("application/json");
		
		File file = new File("/Users/ilhanturkmen/Documents/workspace/"
				+ "RestAssuredFramework/src/test/java/com/api/tests/updateuser.json");
		
		request.body(file);
		Response response = request.put("/public-api/users/3");
		
		System.out.println(response.prettyPrint());
		
		
	
	
	
	
	
	}

}
