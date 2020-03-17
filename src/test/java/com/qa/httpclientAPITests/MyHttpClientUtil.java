package com.qa.httpclientAPITests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

public class MyHttpClientUtil {
	
		/**
		 * 
		 * @return
		 */
		public static CloseableHttpClient createHttpClient() {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			return httpClient;
		}

		/**
		 * 
		 * @param url
		 * @return
		 */
		public static HttpPost createRequestWithUrl(String url) {
			HttpPost postRequest = new HttpPost(url);
			return postRequest;
		}

		public static void addHeaders(HttpPost postRequest) {
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.addHeader("accept", "application/json");
		}

		public static void addToken(HttpPost postRequest, String token) {
			postRequest.addHeader("Authorization", "Bearer " + token);
		}

		public static void addJsonPayLoadBody(HttpPost postRequest, Object obj) {
			String entity = TestUtil.getSerializedJson(obj);
			System.out.println(entity);
			StringEntity userEntity = null;

			try {
				userEntity = new StringEntity(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			postRequest.setEntity(userEntity);
		}

		public static CloseableHttpResponse executeAPI(HttpPost postRequest, CloseableHttpClient httpClient) {
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(postRequest);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}

		public static int getResponseStatusCode(CloseableHttpResponse response) {
			return response.getStatusLine().getStatusCode();
		}

		public static String getResponseBody(CloseableHttpResponse response) {
			String responseBody = null;
			HttpEntity httpEntity = response.getEntity();
			try {
				responseBody = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return responseBody;
		}

	

}
