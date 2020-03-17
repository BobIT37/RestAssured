package com.qa.httpclientAPITests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.api.tests.Users;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class UpdateUserPUTAPITest {

	@Test
	public void updateUserTest() {

		// create a new user
		// get the id from post response body
		// use update api and append the id with the url
		// update the pojo
		// hit the put call
		// get the response

		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		// 1. create a post request with URL:
		HttpPost postRequest = new HttpPost("https://gorest.co.in/public-api/users");

		// 3. add the json payload body:
		Users user = new Users("Monika", "Garga", "female", "01-01-1999", "monika44@gmail.com", "+1-292-333-2323",
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

		// 4. hit the post api with execute method:
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
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println(responseBody);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
		List<String> idList = JsonPath.read(document, "$..id");
		System.out.println("user id is: " + idList.get(0));
		String id = idList.get(0);

		// Put call:
		String putUrl = "https://gorest.co.in/public-api/users/" + id;
		HttpPut httpPut = new HttpPut(putUrl);

		httpPut.addHeader("Authorization", "Bearer SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF");
		httpPut.setHeader("Content-Type", "application/json");
		httpPut.addHeader("accept", "application/json");

		user.setStatus("inactive");

		// get json from pojo:
		String entityput = TestUtil.getSerializedJson(user);
		System.out.println(entityput);
		StringEntity userEntityput = null;

		try {
			userEntityput = new StringEntity(entityput);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// add the json payload :
		httpPut.setEntity(userEntityput);

		// execute the put call:
		CloseableHttpResponse responsePut = null;
		try {
			responsePut = httpClient.execute(httpPut);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get the status from put call response:
		int putResponseCode = responsePut.getStatusLine().getStatusCode();
		System.out.println("put response code: " + putResponseCode);

		// get the response body:
		HttpEntity httpEntityPut = responsePut.getEntity();
		String responseBodyPut = null;
		try {
			responseBodyPut = EntityUtils.toString(httpEntityPut);
			System.out.println(responseBodyPut);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("==========put response body========");
		System.out.println(responseBodyPut);
		
		Object documentPut = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
		List<String> idListPut = JsonPath.read(documentPut, "$..id");
		System.out.println("user id after put: " + idListPut.get(0));
		String idPut = idListPut.get(0);

	}

}
