package com.videostreaming.app.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//change CustomMessage to ApiResponse class
public class ApiResponse {
  
	private String message;
	private boolean success=false;
	
	private String error;
    private int statusCode;
	private LocalDateTime timestamp;
	private String path; // request path
	private String port;
	private String serverName;

}
















/*
If video exists →"Video deleted successfully!"
If video does NOT exist → it shows long RuntimeException error
but i want custom exception like below:
{
	  "timestamp": "2025-08-23T15:35:53.273",
	  "status": 404,
	  "error": "Not Found",
	  "message": "Video not found with id: 43f806ba-141b-47e5-8e1d-8131b9855ce3"
	}
*/