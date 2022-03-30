package com.sharerh.projeto.services.exceptions;

public class AccountConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AccountConflictException(String msg) {
		super(msg);
	}

}
