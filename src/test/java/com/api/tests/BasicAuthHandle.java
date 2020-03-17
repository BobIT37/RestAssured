package com.api.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicAuthHandle {
	
	//token
		//basic auth - un/pwd
		//outh 1.0 -- Twitter API
		//outh 2.0
		
		@Test
		public void basicAuthRestAssuredTest(){
			
			RestAssured.baseURI = "http://the-internet.herokuapp.com";
			RequestSpecification  request = RestAssured.given().log().all();
			request.auth().basic("admin", "admin");
			Response resp = request.get("/basic_auth");
			
			System.out.println(resp.prettyPrint());
			
		}
	

}
