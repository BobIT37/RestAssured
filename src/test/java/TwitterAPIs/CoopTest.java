package TwitterAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CoopTest {

	String tokenId;

	@BeforeMethod
	public void setUp() {

		RequestSpecification request = RestAssured.given()
				 .formParam("client_id", "BobTestAPI")
				 .formParam("client_secret", "877ba43683d4cc8c6ecbd9e992e0a9f8")
				 .formParam("grant_type", "client_credentials");

		Response resp = request.post("http://coop.apps.symfonycasts.com/token");

		System.out.println(resp.getStatusCode());
		System.out.println(resp.prettyPrint());

		tokenId = resp.jsonPath().getString("access_token");
		System.out.println("API token id is: " + tokenId);
	}
	
	
	
	@Test(priority=1)
	public void feedChickensTest(){
		RequestSpecification request1 = RestAssured.
				given().
				auth().
				oauth2(tokenId);
		
		Response resp1 = request1.post("http://coop.apps.symfonycasts.com/api/564/chickens-feed");
		
		System.out.println(resp1.getStatusCode());
		System.out.println(resp1.prettyPrint());
	}
	
	
	@Test(priority=2)
	public void eggsCollectTest(){
		RequestSpecification request1 = RestAssured.
				given().
				auth().
				oauth2(tokenId);
		
		Response resp1 = request1.post("http://coop.apps.symfonycasts.com/api/564/eggs-collect");
		
		System.out.println(resp1.getStatusCode());
		System.out.println(resp1.prettyPrint());
	}

}
