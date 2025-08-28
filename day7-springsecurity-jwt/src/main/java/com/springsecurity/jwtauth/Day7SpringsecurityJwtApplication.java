package com.springsecurity.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day7SpringsecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day7SpringsecurityJwtApplication.class, args);
	}

}
//if using HS512 as your JWT signing algorithm, but the secret key is too short.
//HS512 requires a key size at least 512 bits = 64 bytes = 64 characters (if ASCII/UTF-8).
//Your current key is 320 bits (~40 characters), so JJWT throws a WeakKeyException.