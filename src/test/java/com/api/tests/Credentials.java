package com.api.tests;

public class Credentials {
	
	//POJO = Plain Old Java Object
	
	String username;
	String password;
	
	public Credentials(String username, String password){
		this.username = username;
		this.password = password;
	
	}
	
	//getter and setter:

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
