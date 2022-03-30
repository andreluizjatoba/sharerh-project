package com.sharerh.projeto.entities;

public class AccountResponseMessage {
	
	private String message;
	
	public AccountResponseMessage(String message) {
		this.message = message;
	}
	
	public AccountResponseMessage() {
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
