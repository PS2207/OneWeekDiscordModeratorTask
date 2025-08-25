package com.videostreaming.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.videostreaming.app.payload.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	Whenever ResourceNotFoundException occur in controller, this method will be executed
	
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, HttpServletRequest request){
	  String message= ex.getMessage();
	  String port= String.valueOf(request.getServerPort());
	  String serverName=request.getServerName();
	  String path= request.getRequestURI();
	  
//	  Using constructor to create object: useful for 2-3 field contructor is fine
//	  ApiResponse apiResponse=new ApiResponse(
//			                           message,               
//	                                   false,                        
//	                                   "Not found",                  
//	                                   HttpStatus.NOT_FOUND.value(), 
//	                                   LocalDateTime.now(),          
////	                                   "/api/videos/{id}" 
//	                                   path,                        
//	                                   port, );                        
    //Using builder pattern(best practice for large DTOs/APIs/many otionla field) to create object
	  ApiResponse apiResponse= ApiResponse.builder()
			                              .message(message)
			                              .success(false)
			                              .error("Not Found")
			                              .statusCode(HttpStatus.NOT_FOUND.value())
			                              .timestamp(LocalDateTime.now())
			                              .port(port)
			                              .path(path)
			                              .serverName(serverName)
			                              .build();  
	  return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);	  
  }
// -------------
  @ExceptionHandler(Exception.class)  // catches all unhandled exceptions
  public ResponseEntity<ApiResponse> handleInternalServerError(Exception ex, HttpServletRequest request) {

      String port = String.valueOf(request.getServerPort());
      String serverName = request.getServerName();
      String path = request.getRequestURI();

      ApiResponse apiResponse = ApiResponse.builder()
              .message("Internal Server Error: " + ex.getMessage())  // optionally hide ex.getMessage() in production
              .success(false)
              .error("Internal Server Error")
              .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .timestamp(LocalDateTime.now())
              .port(port)
              .path(path)
              .serverName(serverName)
              .build();

      return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

//  -----------------------------------------------------------------------------
  // 3️ Handle Generic Exceptions (fallback)
//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
//      Map<String, Object> error = new HashMap<>();
//      error.put("timestamp", LocalDateTime.now());
//      error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//      error.put("error", "Internal Server Error");
//      error.put("message", ex.getMessage());
//      return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
  
//-----------------------------------------------------------------------------------------
  
}



//----------------------------------------------------------
//JSON response if if ID not found :
//{
//    "message": "Video not found with Id : 2",
//    "success": false,
//    "error": "Not Found",
//    "statusCode": 404,
//    "timestamp": "2025-08-24T14:48:36.8453365",
//    "path": "/api/v1/videos/2",
//    "port": "8081",
//    "serverName": "localhost"
//}	

//----------------------------------------------------------

//{
//    "error": "Internal Server Error",
//    "message": "No static resource api/v1/videos.",
//    "timestamp": "2025-08-24T13:00:13.1102127",
//    "status": 500
//}
//------------------------------------------------------------

