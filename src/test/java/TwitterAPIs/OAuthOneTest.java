package TwitterAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuthOneTest {

	@Test
	public void postATweetTest() {

		RequestSpecification request = RestAssured.
				given().
				auth().
				oauth(
				"UQHrsJmIQEZ6taVS90fef082P",
				"R8bnIO4EKpkXtXhfXK8duixzRbeZFdAwHVWGCebudX7bS4yIHU",
				"2469597739-wPfyqFmzkGSbdQZDJpoS0jmNhkYENMfagG1DGWg", 
				"uNL8QrjKIViZpbuKAIj6WtVXRwV73R11fuQbTF0CCdFRe");

		Response response = request
				.post("https://api.twitter.com/1.1/statuses/update.json?status=Hey this my seco tweet using API");

		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	
	@Test
	public void ReTweetPOSTAPITest() {

		RequestSpecification request = RestAssured.
				given().
				auth().
				oauth(
						"UQHrsJmIQEZ6taVS90fef082P",
						"R8bnIO4EKpkXtXhfXK8duixzRbeZFdAwHVWGCebudX7bS4yIHU",
						"2469597739-wPfyqFmzkGSbdQZDJpoS0jmNhkYENMfagG1DGWg", 
						"uNL8QrjKIViZpbuKAIj6WtVXRwV73R11fuQbTF0CCdFRe");

		Response response = request
				.post("https://api.twitter.com/1.1/statuses/retweet/1197536320928833536.json");

		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
	}

}
