package com.booking.api.tests;

//POJO class
public class BookingDates {
	
	String checkin;
	String checkout;
	
	public BookingDates(){
		
	}

	public BookingDates(String checkin, String checkout) {
		
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
	

}
