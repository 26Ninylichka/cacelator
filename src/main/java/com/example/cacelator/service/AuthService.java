package com.example.cacelator.service;

import com.example.cacelator.controller.dto.SignInRequestDto;
import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.TokenResponseDto;

public interface AuthService {

    TokenResponseDto signUp(SignUpRequestDto requestDto);

    TokenResponseDto signIn(SignInRequestDto requestDto);
}