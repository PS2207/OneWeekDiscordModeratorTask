package com.springsecurity.jwtauth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springsecurity.jwtauth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component
public class AuthUtil {
	
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateAccessToken(User user) {
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("userId", user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60*10))
				.signWith(getSecretKey())
				.compact();
	}

	//after login - to work with filter- fill securitycontextholder with find user from database  HS512 Jwts.SIG.HS512
	public String getUsernameFromToken(String token) {
	
//		Claims claims= Jwts.builder()
//				.verifyWith(getSecretKey())
//				.build()
//				.parseSignedClaims(token)
//				.getPayload();
		 // Parse the JWT and validate signature
	    Jws<Claims> jwsClaims = Jwts.parserBuilder()
	            .setSigningKey(getSecretKey())
	            .build()
	            .parseClaimsJws(token);  // returns Jws<Claims>

	    return jwsClaims.getBody().getSubject(); // Extract username
		
		
		
//		return claims.getSubject();
	}
}
