package com.mrlii.springsecurityex.security;

import com.mrlii.springsecurityex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

     @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
