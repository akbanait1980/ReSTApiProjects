package com.java.webservice.learn.restfulwebservices.user;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CompanyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		
		CompanyException exception = new CompanyException(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(userNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
		
		CompanyException exception = new CompanyException(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
		
	}


}
