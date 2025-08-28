package com.springsecurity.jwtauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtAuthFilter jwtAuthFilter;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/auth/**").permitAll() // Public endpoints
//                .requestMatchers("/admin/**").hasRole("ADMIN") // All others require authentication
//                .requestMatchers("/doctors/**").hasAnyRole("DOCTORS", "ADMIN")
                .anyRequest().authenticated()
             )
             .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());//dont want its login form, i want to make our own form like using angular or react etc so comment out this
           

        return http.build();
    }

}
