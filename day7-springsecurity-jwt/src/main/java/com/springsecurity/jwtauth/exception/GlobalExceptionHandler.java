package com.springsecurity.jwtauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiException> handleUsenameNotFoundException(
			UsernameNotFoundException ex){
		ApiException apiException = new ApiException(
				"Username not found with username: "+ ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiException, apiException.getStatusCode());
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiException> handleAuthenticationException(
			AuthenticationException ex){
		ApiException apiException = new ApiException(
				"Authentication failed: "+ ex.getMessage(), HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiException> handleJwtException(
			JwtException ex){
		ApiException apiException = new ApiException(
				"Invalid JWT token: "+ ex.getMessage(), HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiException> handleAccessDeniedException(
			AccessDeniedException ex){
		ApiException apiException = new ApiException(
				"Access denied: Insufficient permissions "+ ex.getMessage(), HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiException> handleGenericException(
			Exception ex){
		ApiException apiException = new ApiException(
				"An unexpected error occurred: "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
//For exception handle- craeteing  this class & APiException
//Currently, this code handles errors in the filter, but they are not propagated to the MVC layer after the controller.
//To allow errors from the filter to reach the MVC layer, additional coding is required.
//so code  in AuthFilter class try-catch



















