package com.videostreaming.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//In Spring Boot, the safest way to allow multiple frontend- 'Configure CORS globally' by creating configuration class instead of using "*" on each controller.

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
    	return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins(
//                    "http://localhost:3000",   // React dev server
//                    "http://localhost:4200",   // Angular dev server
//                    "https://myapp.netlify.app" // Production React
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
    	};
    }
}
