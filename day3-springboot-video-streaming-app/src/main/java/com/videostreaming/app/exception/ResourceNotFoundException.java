package com.videostreaming.app.exception;

import lombok.Getter;
import lombok.Setter;

//step1: Create class to handle exception, then throw this exception class in serviceImpl class
//This is just handle exception but gives long error so it needs to be shown our own custom message 
//Step 3: Add Global Exception Handler:
//This avoids raw stack traces and formats error responses neatly.

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	String fieldValue;
//	public ResourceNotFoundException(String message) {
//		super(message);
//	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
    // Getters if you need to use these in custom error responses but using annotation @Getter 
//    public String getResourceName() {
//        return resourceName;
//    }
//
//    public String getFieldName() {
//        return fieldName;
//    }
//
//    public Object getFieldValue() {
//        return fieldValue;
//    }
}
