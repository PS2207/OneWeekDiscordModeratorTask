package com.springsecurity.jwtauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwtauth.dto.LoginRequestDto;
import com.springsecurity.jwtauth.dto.LoginResponseDto;
import com.springsecurity.jwtauth.dto.SignupResponseDto;
import com.springsecurity.jwtauth.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//	@Autowired
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
		return ResponseEntity.ok(authService.login(loginRequestDto));
	}
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupRequestDto){
		return ResponseEntity.ok(authService.signup(signupRequestDto));
		
	}
}
