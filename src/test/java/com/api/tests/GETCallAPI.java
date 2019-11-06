package com.api.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


import io.restassured.RestAssured;

public class GETCallAPI {
	
	//Rest Assured - BDD Approach
	@Test(priority=1, enabled=false)
	public void getUsersAPI(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		  .contentType("application/jason")
		  .header("Authorization", "Bearer D1CA1oUxrA4Q7KvwXlXYkgpcC3dEoiKXpPP3")
		.when().log().all()
		  .get("/public-api/users")
		.then().log().all()
		   .statusCode(200)
		   .and()
		   .header("Server", "nginx")
		   .header("X-Rate-Limit-Limit", "30");
	
	}
	
	@Test(priority=2, enabled=false)
	public void getSingleUsersAPI(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		  .contentType("application/jason")
		  .header("Authorization", "Bearer D1CA1oUxrA4Q7KvwXlXYkgpcC3dEoiKXpPP3")
		.when().log().all()
		  .get("/public-api/users?first_name=Antonette")
		.then().log().all()
		   .statusCode(200)
		   .and()
		   .header("Server", "nginx")
		   .header("X-Rate-Limit-Limit", "30");
	
	}
	
	@Test(priority=3)
	public void getUserWithOneQueryParameterAPITest(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		  .contentType("application/jason")
		  .header("Authorization", "Bearer D1CA1oUxrA4Q7KvwXlXYkgpcC3dEoiKXpPP3")
		  .queryParam("first_name", "John")
		  .queryParam("gender", "male")
		.when().log().all()
		  .get("/public-api/users")
		.then().log().all()
		   .statusCode(200)
		   .and()
		   .header("Server", "nginx")
		   .header("X-Rate-Limit-Limit", "30");
	
	}

}
