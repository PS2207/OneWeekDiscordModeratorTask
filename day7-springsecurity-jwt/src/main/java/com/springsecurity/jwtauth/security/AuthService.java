package com.springsecurity.jwtauth.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.jwtauth.dto.LoginRequestDto;
import com.springsecurity.jwtauth.dto.LoginResponseDto;
import com.springsecurity.jwtauth.dto.SignupResponseDto;
import com.springsecurity.jwtauth.entity.User;
import com.springsecurity.jwtauth.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

//	@Autowired
	private final AuthenticationManager authenticationManager;
//	@Autowired
	private final AuthUtil authUtil;
//	@Autowired
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
					                                    loginRequestDto.getPassword())
				);
	
	    User user = (User) authentication.getPrincipal();
	    String token = authUtil.generateAccessToken(user);
	    
	    return new LoginResponseDto(token, user.getId());
	}
	
	public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
		User user= userRepo.findByUsername(signupRequestDto.getUsername()).orElse(null);
		
		if(user != null) throw new IllegalArgumentException("User already exists");
		user =userRepo.save(User.builder()
				                .username(signupRequestDto.getUsername())
				                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
				                .build());
		return new SignupResponseDto(user.getId(), user.getUsername());
	}
	
	
	
	
}
















