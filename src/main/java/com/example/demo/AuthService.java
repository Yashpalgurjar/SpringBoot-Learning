package com.example.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzkwNjcyMjIzLCJleHAiOjE3OTA2NzQwMjN9.RegisterRequest;

@Service
public class AuthService {

	
	public String register(RegisterRequest request) {

		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
		    throw new DuplicateUsernameException("Username already exists");
		}	    

	    User user = new User();
	    user.setUsername(request.getUsername());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
	    user.setRole("USER");

	    userRepository.save(user);

	    return "User registered successfully";
	}
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        
    }
    
}