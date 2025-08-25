package com.ai.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.chat.service.AIChatService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIChatService aiService;

    public AIController(AIChatService aiService) {
        this.aiService = aiService;
    }

//    @PostMapping("/generate")
//    public String generate(@RequestBody Map<String, String> request) {
//        String prompt = request.get("prompt");
//        return aiService.generateText(prompt);
//    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, Object> request) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> messages = (List<Map<String, String>>) request.get("messages");

        return aiService.generateChat(messages);
    }

}


