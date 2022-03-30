package com.sharerh.projeto.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant timeStamps;
	private Integer status;
	private String erros;
	private String message;
	private String path;
	
	public StandardError() {
	
	}

	public Instant getTimeStamps() {
		return timeStamps;
	}
	public void setTimeStamps(Instant timeStamps) {
		this.timeStamps = timeStamps;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErros() {
		return erros;
	}
	public void setErros(String erros) {
		this.erros = erros;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
