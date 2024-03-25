package com.example.Test2.service;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Test2.database.Database;
import com.example.Test2.models.AuthenticationResponse;
import com.example.Test2.models.Usser;
import com.example.Test2.repositories.UserRepository;

@Service
public class AuthenticationService {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	
	public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,AuthenticationManager authenticationManager) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	public AuthenticationResponse register(Usser request) {
		Usser user = new Usser();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUserName(request.getUserName());
		user.setPassWord(passwordEncoder.encode(request.getPassWord()));
		user.setRole(request.getRole());
		
		user = repository.save(user);
		logger.info("insert data:" +repository.save(user));
		String token = jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(Usser request) {
	    if (request.getUserName() == null || request.getPassWord() == null || request.getPassWord().isEmpty()) {
	        throw new IllegalArgumentException("Username and password must not be empty");
	    }
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassWord()));
	    Usser user = repository.findByUserName(request.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    String token = jwtService.generateToken(user);
	    return new AuthenticationResponse(token);
	}
	

}
