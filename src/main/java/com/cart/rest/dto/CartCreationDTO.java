package com.cart.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartCreationDTO {
	@JsonProperty("fullName")
	private String fullName;
	@JsonProperty("email")
	private String email;
	
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}