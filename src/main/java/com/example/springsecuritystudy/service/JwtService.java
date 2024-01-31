package com.example.springsecuritystudy.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String userId);

    String extractUserId(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
