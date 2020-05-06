package com.chillhub.app.custom_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<CustomError> customResourceNotFound(CustomApiException ex) {
		CustomError err = new CustomError();
		
		err.setError(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

}
