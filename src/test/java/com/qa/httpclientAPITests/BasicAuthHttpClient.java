package com.qa.httpclientAPITests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class BasicAuthHttpClient {
	
	@Test
	public void basicAuthHandleTest() {

		// basic auth: username/password
		CredentialsProvider creds = new BasicCredentialsProvider();

		creds.setCredentials(new AuthScope("httpbin.org", 80),
				new UsernamePasswordCredentials("user", "passwd"));

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(creds).build();

		HttpGet httpget = new HttpGet("http://httpbin.org/basic-auth/user/passwd");
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(response.getStatusLine().getStatusCode());
		try {
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
