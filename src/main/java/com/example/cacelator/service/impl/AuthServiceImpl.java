package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.UserEntity;
import com.example.cacelator.data.repository.UserRepository;
import com.example.cacelator.service.AuthService;
import com.example.cacelator.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public String login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        return jwtService.generateToken(user.getUserId(), email);
    }
}