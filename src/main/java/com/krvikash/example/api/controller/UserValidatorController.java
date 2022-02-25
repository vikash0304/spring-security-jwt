package com.krvikash.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krvikash.example.api.model.LoginRequest;
import com.krvikash.example.api.util.JwtUtility;

@RestController
public class UserValidatorController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility utility;

	@PostMapping("/auth")
	public String generateToken(@RequestBody LoginRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid Username and Password.");
		}

		return utility.generateToken(request.getUserName());
	}
}
