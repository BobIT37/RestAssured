package com.qa.httpclientAPITests;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class DeleteUserAPITest {

	@Test
	public void deleteUserTest() {
		CloseableHttpResponse response = null;

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		//1. create delete http request:
		HttpDelete deleteRequest = new HttpDelete("https://gorest.co.in/public-api/users/13");
		
		//2. add token header
		deleteRequest.addHeader("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
		
		//3. execute the API:
		try {
			response = httpClient.execute(deleteRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//4. status code:
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		
		//5. get the response body:
		HttpEntity httpEntity = response.getEntity();
		
		try {
			String responseBody = EntityUtils.toString(httpEntity);
			System.out.println(responseBody);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}