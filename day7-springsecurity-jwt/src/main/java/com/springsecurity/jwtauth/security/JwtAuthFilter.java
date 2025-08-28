package com.springsecurity.jwtauth.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurity.jwtauth.entity.User;
import com.springsecurity.jwtauth.repo.UserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter{

//	private final UserRepo userRepo;
//	private final AuthUtil authUtil;
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		log.info("Incoming Request: {}", request.getRequestURI());
//		
//		final String requestTokenHeader = request.getHeader("Authorization");
////		"Bearer ruwiyriwryfugfffe"
//		if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")){
//			filterChain.doFilter(request,  response);
//			return;
//		}
//	
//		String token=requestTokenHeader.split("Bearer ")[1];
//		String username= authUtil.getUsernameFromToken(token);
//		
//		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			
//			User user= userRepo.findByUsername(username).orElseThrow();
//			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
//					user,null,user.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		}
//		filterChain.doFilter(request,response);
//	}
	

//}








