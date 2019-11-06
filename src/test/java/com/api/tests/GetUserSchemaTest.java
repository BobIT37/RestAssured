package com.api.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetUserSchemaTest {
	
	@Test
	public void getUserSchemaTest(){
		
		RestAssured.given().log().all()
		 .contentType("application/jason")
		 .header("Authorization" , "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF")
		 
		 .when().log().all()
		     .get("https://gorest.co.in/public-api/users/2")
		     .then().log().all()
		     .assertThat()
		     .body(matchesJsonSchemaInClasspath("UserSchema.json"));
	
	}

}
