package TwitterAPIs;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuthTwoTest {

	@Test
	public void checkOAuth2Test() {

		RequestSpecification request = RestAssured.
				given().
				auth().
				oauth2("cd013f195d9e8cdac0bbf904eac3e84c2b7ece9f");
		
		Response resp = 	request.post("http://coop.apps.symfonycasts.com/api/396/chickens-feed");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.prettyPrint());

	}
	
	@Test
	public void GetOAuth2TokenTest() {

		RequestSpecification request = RestAssured.given()
				 .formParam("client_id", "BobTestAPI")
				 .formParam("client_secret", "877ba43683d4cc8c6ecbd9e992e0a9f8")
				 .formParam("grant_type", "client_credentials");
		
		Response resp = 	request.post("http://coop.apps.symfonycasts.com/token");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.prettyPrint());
		
		String tokenId = resp.jsonPath().getString("access_token");
		System.out.println("API token id is: "+ tokenId);
		
		
		RequestSpecification request1 = RestAssured.
				given().
				auth().
				oauth2(tokenId);
		
		Response resp1 = request1.post("http://coop.apps.symfonycasts.com/api/396/chickens-feed");
		
		System.out.println(resp1.getStatusCode());
		System.out.println(resp1.prettyPrint());
		

	}


}
