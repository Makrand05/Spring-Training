package com.makrand.rest.webservices.resrfullwebservice.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.makrand.rest.webservices.resrfullwebservice.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponceEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorDetails> handleAllExceptipn(Exception e,WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),  request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);		
	}

	@ExceptionHandler(UserNotFoundException.class)	
	ResponseEntity<ErrorDetails> handleUserNotFoundExceptipn(Exception e,WebRequest request){		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(),  request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);		
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
		"Total Errors :"	+ex.getErrorCount()	+" First Error" +ex.getFieldError().getDefaultMessage(),  request.getDescription(false));
		
		
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);	
		
	}
	
}
