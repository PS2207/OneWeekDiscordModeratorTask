package com.springsecurity.jwtauth.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiException {
 private LocalDateTime timeStamp;
 private String error;
 private HttpStatus statusCode;
 
 public ApiException(){
	 this.timeStamp= LocalDateTime.now();
	 }
 
 public ApiException(String error, HttpStatus statusCode) {
	 this();
	 this.error =error;
	 this.statusCode = statusCode;
 }
}
