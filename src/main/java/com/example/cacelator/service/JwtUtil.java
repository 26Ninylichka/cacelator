package com.example.cacelator.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtService jwtService;
    public String extractUserId(String token) {
        return jwtService.extractClaim(token, Claims::getSubject);
    }
}
