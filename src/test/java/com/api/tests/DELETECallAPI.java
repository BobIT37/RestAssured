package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DELETECallAPI {
	
	@Test
	public void deleteUserAPITest(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given().log().all(); //log is used to get more info
		request.header("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
		
		Response response = request.delete("/public-api/users/13");
		System.out.println(response.prettyPrint());
		
		Assert.assertEquals(response.getStatusCode(), HTTPStatus.HTTP_STATUS_CODE_200);
		
		JsonPath js = response.jsonPath();
		System.out.println(js.get("result"));
		Assert.assertNull(js.get("result"));
		
	}

}
