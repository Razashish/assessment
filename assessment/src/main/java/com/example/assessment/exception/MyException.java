package com.example.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
		return new ResponseEntity<String>(e.getMsg(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException e) {
		return new ResponseEntity<String>(e.getMsg(), HttpStatus.BAD_REQUEST);
	}
}
