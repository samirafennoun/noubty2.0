package com.chillhub.app.custom_exceptions;

public class CustomError {
	
	private int status;
    private String error;
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
