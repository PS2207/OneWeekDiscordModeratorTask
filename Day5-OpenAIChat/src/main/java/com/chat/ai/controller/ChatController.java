package com.chat.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.ai.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@GetMapping
	public ResponseEntity<String> generateResponse(@RequestParam(value="inputText") String inputText){
		String responseText=chatService.generateResponse(inputText);
		return ResponseEntity.ok(responseText);
	}
	
	
}
