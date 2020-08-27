package com.musliar.collegeproject.exception;

import lombok.Data;

@Data
public class ErrorMessage {
	String message;

	public ErrorMessage(String message) {
		super();
		this.message = message;
	}
	
}
