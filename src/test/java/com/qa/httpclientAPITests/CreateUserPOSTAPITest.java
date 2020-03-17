package com.qa.httpclientAPITests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.api.tests.Users;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import com.api.tests.Users;

public class CreateUserPOSTAPITest {
	
	@Test
	public void createUserAPITest() {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		// 1. create a post request with URL:
		HttpPost postRequest = new HttpPost("https://gorest.co.in/public-api/users");

		// 3. add the json payload body:
		Users user = new Users("Varun", "S", "male", "01-01-1995", "yaruns0@gmail.com", "+1-222-333-2323",
				"http://www.google.com", "test address", "active");

		String entity = TestUtil.getSerializedJson(user);
		System.out.println(entity);
		StringEntity userEntity = null;

		try {
			userEntity = new StringEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		postRequest.setEntity(userEntity); // add the final payload - json body
											// string
		// 2. add headers:
		postRequest.addHeader("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.addHeader("accept", "application/json");

		// 4. hit the api with execute method:
		try {
			response = httpClient.execute(postRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 5. get the status code:
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("status code from response: " + statusCode);
		// Assert.assertEquals(statusCode, 200);

		// 6. print the response json:
		HttpEntity httpEntity = response.getEntity();
		try {
			String responseBody = EntityUtils.toString(httpEntity);
			System.out.println(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Test
	public void createUserTest() {
		CloseableHttpClient httpClient = MyHttpClientUtil.createHttpClient();
		HttpPost postRequest = MyHttpClientUtil.createRequestWithUrl("https://gorest.co.in/public-api/users");
		MyHttpClientUtil.addHeaders(postRequest);
		MyHttpClientUtil.addToken(postRequest, "SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");

		Users user = new Users("Varuny", "S", "male", "01-01-1985", "varuns109@gmail.com", "+1-222-333-2323",
				"http://www.google.com", "test address", "active");

		MyHttpClientUtil.addJsonPayLoadBody(postRequest, user);

		CloseableHttpResponse response = MyHttpClientUtil.executeAPI(postRequest, httpClient);
		MyHttpClientUtil.getResponseStatusCode(response);
		String responseBody = MyHttpClientUtil.getResponseBody(response);
		System.out.println(responseBody);
		
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
		List<String> result = JsonPath.read(document, "$..id");
		System.out.println(result);

	}
}
