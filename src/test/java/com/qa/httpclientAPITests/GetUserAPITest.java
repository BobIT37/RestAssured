package com.qa.httpclientAPITests;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserAPITest {
	
	@Test
	public void goRestGetUserAPITest(){
		
	CloseableHttpResponse response = null;	
		
	CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	//1. create a request with url
	HttpGet getRequest = new HttpGet("https://gorest.co.in/public-api/users");
	
	//2. add header
	getRequest.addHeader("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
	
	//3. execute the API
	
	try {
		response = httpClient.execute(getRequest);
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	//4. get teh status code
	int statusCode = response.getStatusLine().getStatusCode();
	System.out.println(statusCode);
	
	Assert.assertEquals(statusCode, 200);
	
	//5. get the response body
	HttpEntity httpEntitiy = response.getEntity();
	
	try {
		String responseBody = EntityUtils.toString(httpEntitiy);
		System.out.println(responseBody);
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	}

}
