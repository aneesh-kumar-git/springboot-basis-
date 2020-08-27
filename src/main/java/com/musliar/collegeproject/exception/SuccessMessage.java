package com.musliar.collegeproject.exception;

import lombok.Data;

@Data
public class SuccessMessage {
	String message;
	public SuccessMessage(String message) {
		super();
		this.message = message;
	}
	
}
