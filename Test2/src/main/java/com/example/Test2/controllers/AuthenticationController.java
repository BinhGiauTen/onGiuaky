package com.example.Test2.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test2.models.AuthenticationResponse;
import com.example.Test2.models.Usser;
import com.example.Test2.service.AuthenticationService;

@RestController
public class AuthenticationController {
	private final AuthenticationService authService;

	public AuthenticationController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody Usser request
			){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody Usser request
			) throws AuthenticationException{
		return ResponseEntity.ok(authService.authenticate(request));
	}
	

}
