package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



import org.testng.Assert;
import org.testng.annotations.Test;

public class GETAPINoBDD {
	
	@Test(enabled=false)
	public void getUserAPITest(){
		
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request = RestAssured.given().log().all(); //log is used to get more info
		request.header("Authorization", "Bearer D1CA1oUxrA4Q7KvwXlXYkgpcC3dEoiKXpPP3");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.asString());  //to print all result in JSON
		System.out.println(response.prettyPrint()); //to print on console appropriately
		//System.out.println(response.getStatusCode()); //to see which code 200, 400 etc.
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		String server = response.getHeader("Server");
		Assert.assertEquals(server, "nginx");
	
	}
	@Test
	public void getUserByParamsTest(){
		
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request = RestAssured.given().log().all(); //log is used to get more info
		request.header("Authorization", "Bearer D1CA1oUxrA4Q7KvwXlXYkgpcC3dEoiKXpPP3");
		request.queryParam("first_name", "John");
		request.queryParam("gender", "male");
		request.queryParam("status", "active");
		
		Response response = request.get("/public-api/users");
		
		System.out.println(response.asString());  //to print all result in JSON
		System.out.println(response.prettyPrint()); //to print on console appropriately
		//System.out.println(response.getStatusCode()); //to see which code 200, 400 etc.
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		String server = response.getHeader("Server");
		Assert.assertEquals(server, "nginx");
	
	}
	

}
