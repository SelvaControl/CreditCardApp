package com.teleapps.exceptionhandler;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teleapps.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        List<String> errorDetails = new ArrayList<>();

	        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	            errorDetails.add(String.format("Field '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()));
	        }

	        ErrorResponse errorResponse = new ErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                "Validation failed for the provided customer data",
	                null,
	                errorDetails, 
	                LocalDateTime.now() 
	        );

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
}

