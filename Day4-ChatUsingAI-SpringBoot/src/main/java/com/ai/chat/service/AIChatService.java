package com.ai.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;

@Service
public class AIChatService {

//    @Value("${hf.api.token}")
//    private String hfApiToken;
//
//    @Value("${hf.model.url}")
//    private String hfModelUrl;
//
//    public String generateText(String prompt) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + hfApiToken);
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("inputs", prompt);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(hfModelUrl, entity, String.class);
//
//        return response.getBody();
//    }
	
//	---------------------
	  @Value("${hf.api.token}")
	    private String hfApiToken;

	    @Value("${hf.model.name}")
	    private String hfModelName;

//	    private static final String HF_API_URL = "https://api-inference.huggingface.co/v1/chat/completions";
	    private static final String HF_API_URL = "https://router.huggingface.co/v1/chat/completions";
	


	    public String generateChat(List<Map<String, String>> messages) {
	        try {
	            RestTemplate restTemplate = new RestTemplate();

	            // Headers
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            headers.setBearerAuth(hfApiToken);

	            // Request body
	            Map<String, Object> body = new HashMap<>();
	            body.put("model", hfModelName);
	            body.put("messages", messages);

	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

	            // Send request
	            ResponseEntity<String> response =
	                    restTemplate.exchange(HF_API_URL, HttpMethod.POST, entity, String.class);

	            // Parse JSON response
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode root = mapper.readTree(response.getBody());

	            if (root.has("choices") && root.get("choices").isArray()) {
	                JsonNode choice = root.get("choices").get(0);
	                if (choice.has("message")) {
	                    return choice.get("message").get("content").asText();
	                }
	            }

	            // Fallback raw response if unexpected
	            return response.getBody();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "❌ Error: " + e.getMessage();
	        }
	    }

}

