package com.example.Test2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Test2.repositories.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	private final UserRepository repository;
	
	public UserDetailsServiceImp(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repository.findByUserName(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	}

}
