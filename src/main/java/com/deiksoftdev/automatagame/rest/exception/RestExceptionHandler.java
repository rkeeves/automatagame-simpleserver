package com.deiksoftdev.automatagame.rest.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deiksoftdev.automatagame.user.UserNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, "User not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleIntegrityViolation(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, 
				ex.getLocalizedMessage(), 
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, 
				ex.getLocalizedMessage(), 
				new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
	}
}