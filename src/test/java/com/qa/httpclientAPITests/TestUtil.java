package com.qa.httpclientAPITests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestUtil {
	
	/**
	 * this method is used to convert pojo to json string
	 * @param obj
	 * @return jsonString
	 */
	public static String getSerializedJson(Object obj){
		String jsonString = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return jsonString;
		}
		
		return jsonString;
		
	}
}
