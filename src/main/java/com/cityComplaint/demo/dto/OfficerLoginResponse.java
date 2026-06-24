package com.cityComplaint.demo.dto;

public class OfficerLoginResponse {
	
	private Long officerId;
	private String name;
	private String email;
	
	//Getters and setters 
	
	public Long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
