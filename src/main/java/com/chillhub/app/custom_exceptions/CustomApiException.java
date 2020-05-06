package com.chillhub.app.custom_exceptions;

public class CustomApiException extends RuntimeException {
	
	public CustomApiException(String message) {
		super(message);
	}

}
