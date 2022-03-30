package com.sharerh.projeto.services.exceptions;

public class IntegrityViolatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IntegrityViolatedException(String msg) {
		super(msg);
	}

}
