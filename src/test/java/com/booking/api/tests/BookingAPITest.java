package com.booking.api.tests;

import org.omg.Messaging.SyncScopeHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.tests.HTTPStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingAPITest {
	
	
	@Test
	public void doBookingTest(){
		String bookingJson = null;
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		request.contentType("application/json");
		
		BookingDates bd = new BookingDates("2018-01-01", "2019-01-01");
		
		Booking booking = new Booking("Ilhan", "Turkmen", 111, true, "breakfast", bd);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			bookingJson = mapper.writeValueAsString(booking);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(bookingJson);
		
		request.body(bookingJson);
		
		Response response = request.post("/booking");
		
		System.out.println(response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), HTTPStatus.HTTP_STATUS_CODE_200);
		
		JsonPath js = response.jsonPath();
		int bookingID = js.get("bookingid");
		System.out.println(bookingID);
		
		Assert.assertNotNull(bookingID);
		
//		String firstName = js.get("booking.firstname");
//		System.out.println(firstName);
		
		Assert.assertEquals(js.get("booking.firstname"), booking.getFirstname());
		Assert.assertEquals(js.get("booking.lastname"), booking.getLastname());
		Assert.assertEquals(js.get("booking.totalprice"), booking.getTotalprice());
		Assert.assertEquals(js.get("booking.depositpaid"), booking.isDepositpaid());
		Assert.assertEquals(js.get("booking.additionalneeds"), booking.getAdditionalneeds());
		
		System.out.println(bd.getCheckin());
		
		Assert.assertEquals(js.get("booking.bookingdates.checkin"), bd.getCheckin());
		Assert.assertEquals(js.get("booking.bookingdates.checkout"), bd.getCheckout());
		
	}

}
