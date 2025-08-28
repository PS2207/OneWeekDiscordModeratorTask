package com.springsecurity.jwtauth.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {

	@Id
	private Long id;
	private String username;
}
