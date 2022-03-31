package com.sharerh.projeto.entities;

/**
 * Classe para retorno uma messagem utilizada no método de deletar conta
 * 
 * @author andrejatoba
 *
 */

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
