package com.musliar.collegeproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{
	@ExceptionHandler(DataNotFoundException.class)
	ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex,WebRequest webRequest){
		String errorMsg=ex.getLocalizedMessage();
		ErrorMessage errorMessage=new ErrorMessage(errorMsg);
		return new ResponseEntity<Object>(errorMessage,HttpStatus.NOT_FOUND);
	}
}
