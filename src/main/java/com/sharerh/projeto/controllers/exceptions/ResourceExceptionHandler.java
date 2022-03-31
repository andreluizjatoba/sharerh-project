package com.sharerh.projeto.controllers.exceptions;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sharerh.projeto.services.exceptions.AccountConflictException;


/**
 * Classe para tratar as exceções (erros) utilizando um retorno de erro padrão (Classe StandardError)
 * 
 * @author andrejatoba
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setErros("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AccountConflictException.class)
	public ResponseEntity<StandardError> accountConflict(AccountConflictException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setErros("Account Conflict");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> accountConflict(MethodArgumentNotValidException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setErros("Account Conflict");
		err.setMessage("Invalid field: "+ e.getBindingResult().getFieldError().getField() + " - " + e.getBindingResult().getFieldError().getDefaultMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> accountConflict(DataIntegrityViolationException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setErros("Account Conflict");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> nullPointerException(NullPointerException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setErros("Account Conflict");
		err.setMessage("Invalid field: "+ e.getLocalizedMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> badRequest(HttpMessageNotReadableException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimeStamps(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setErros("Bad Request");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
