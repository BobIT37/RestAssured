package com.rest.api.authantications;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuth1APITest {
	
	@Test
	public void twitterStatusAPI_OAuth1_Test(){
		
		RequestSpecification request = 
		RestAssured.given()
		   .auth()
		      .oauth("1yS9zmMv59NlNqFDc4tRLT83W",
						"Ds7pJUB36kh1YQ3cjS4527IosDf5bsbKKs6FuA4JsKl8dYjDHe",
						"220976784-tIFJ7W6sRt55mrM5kK9XW0PQYoeVjfCdTIGxxsR9", 
						"gsa9eEDl1eRfgLEW7GJSa06wFK1E64KMd3DPT92LJvzpa");
		Response response = 
				request.post("https://api.twitter.com/1.1/statuses/update.json?status=Hey this my third tweet using API");
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	

}
