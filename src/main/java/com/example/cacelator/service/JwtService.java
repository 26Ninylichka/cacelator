package com.example.cacelator.service;

import java.util.UUID;

public interface JwtService {

    String generateToken(UUID userId,String email);

    String extractEmail(String token);

    boolean isTokenValid(String token, String email);

    String extractUserId(String token);
}