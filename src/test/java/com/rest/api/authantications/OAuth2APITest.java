package com.rest.api.authantications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth2APITest {

	@Test
	public void checkOAuth2_APITest(){
		
		RequestSpecification request =
		RestAssured
		   .given()
		      .auth()
		         .oauth2("cd013f195d9e8cdac0bbf904eac3e84c2b7ece9f");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/api/564/chickens-feed");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	//1. Generate a token at the run time by using token API
	//2. String tokenID from the response 
	//3. hit the next api with this tokenID
	
	@Test
	public void getAuth2TokenAPITest(){
		
		RequestSpecification request = 
		RestAssured.given()
		 .formParam("client_id", "BobTestAPI")
		 .formParam("client_secret", "877ba43683d4cc8c6ecbd9e992e0a9f8")
		 .formParam("grant_type", "client_credentials");
		
		Response response = request.post("http://coop.apps.symfonycasts.com/token");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		String tokenID = response.jsonPath().getString("access_token");
		System.out.println("API Token ID is: "+ tokenID);
		
		//feed chicken API
		RequestSpecification request1 =
				RestAssured
				   .given()
				      .auth()
				         .oauth2(tokenID);
				
				Response response1 = request1.post("http://coop.apps.symfonycasts.com/api/564/chickens-feed");
				
				System.out.println(response1.getStatusCode());
				System.out.println(response1.prettyPrint());
		     
		
		
	}
	
	
	
}
