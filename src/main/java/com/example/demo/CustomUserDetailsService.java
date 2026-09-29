package com.example.demo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        String[] authorities;

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            authorities = new String[]{
                "STUDENT_READ",
                "STUDENT_CREATE",
                "STUDENT_UPDATE",
                "STUDENT_DELETE"
            };
        } else if ("USER".equalsIgnoreCase(user.getRole())) {
            authorities = new String[]{"STUDENT_READ"};
        } else {
            authorities = new String[]{user.getRole()};
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}