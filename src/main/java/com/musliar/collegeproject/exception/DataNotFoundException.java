package com.musliar.collegeproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataNotFoundException extends Exception {
	public DataNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
