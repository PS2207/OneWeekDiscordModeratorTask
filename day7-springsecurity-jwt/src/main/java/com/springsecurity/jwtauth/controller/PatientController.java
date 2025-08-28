package com.springsecurity.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class PatientController {

	@GetMapping("/patient")
	public String get() {
		return "patient 1";
	}
}
