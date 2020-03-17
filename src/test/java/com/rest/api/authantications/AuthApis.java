package com.rest.api.authantications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.RestAssured;

public class AuthApis {
	
	//basic auth:
	//username/passowrd
	@Test
	public void basic_auth_API_Test(){
		
		given().log().all()
		 .auth()
		 .preemptive()
		     .basic("admin", "admin")
		 .when().log().all()
		     .get("http://the-internet.herokuapp.com/basic_auth")
		 .then().log().all()
		     .assertThat()
		         .statusCode(200);
	}
	
	//Oauth2.0
	//If you are using
	//1. with header: append your token with Bearer keyword
	//2. with oauth2() method: No need to add Bearer, just pass the token value
	
	@Test
	public void OAuth2_API_Test(){
		
		given().log().all()
		     .auth()
		          .oauth2("SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF")
		.when().log().all()
		     .get("https://gorest.co.in/public-api/users?first_name=Floyd")
		.then().log().all()
		    .assertThat()
		        .statusCode(200);
	}
	
	@Test
	public void Oauth_API_With_AuthHeader(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		    .contentType("application/json")
		    .header("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF")
		.when().log().all()
		    .get("/public-api/users?first_name=Floyd")
		.then().log().all()
		    .statusCode(200)
		    .and()
		    .header("Server", "nginx");
	
	}
	
	@Test
	public void OAuth_API_WithTwoQueryParams_API_Test(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
	       .contentType("application/json")
	       .header("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF")
	       .queryParam("first_name", "Floyd")
	       .queryParam("gender", "female")
	    .when().log().all()
	       .get("/public-api/users")
	    .then().log().all()
	       .statusCode(200)
	       .and()
	       .header("Server", "nginx");
	}
	
	
	

}
