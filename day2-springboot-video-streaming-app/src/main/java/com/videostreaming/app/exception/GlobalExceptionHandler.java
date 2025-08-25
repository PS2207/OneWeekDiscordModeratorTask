package com.videostreaming.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.videostreaming.app.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	Whenever ResourceNotFoundException occur in controller, this method will be executed
	
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	  String message= ex.getMessage();
	  ApiResponse apiResponse=new ApiResponse(
			                           message,                      // message
	                                   false,                        // success
	                                   "Not found",                  // error
	                                   HttpStatus.NOT_FOUND.value(), // statusCode
	                                   LocalDateTime.now(),          // timestamp
	                                   "/api/videos/{id}"            // path (you can inject HttpServletRequest to get real URL)
	    );
	  return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);	  
  }
  
  

  
}
