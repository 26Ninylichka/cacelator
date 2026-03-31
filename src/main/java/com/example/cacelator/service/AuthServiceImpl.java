package com.example.cacelator.service;

import com.example.cacelator.controller.dto.SignInRequestDto;
import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.TokenResponseDto;
import com.example.cacelator.data.entity.UserEntity;
import com.example.cacelator.data.repository.UserRepository;
import com.example.cacelator.exception.UserAlreadyExistsException;
import com.example.cacelator.exception.UserNotFoundException;
import com.example.cacelator.service.model.Status;
import com.example.cacelator.service.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public TokenResponseDto signUp(SignUpRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException(
                    "User with email " + requestDto.getEmail() + " already exists"
            );
        }

        UserEntity userEntity = UserEntity.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .status(Status.CREATED)
                .type(Type.CUSTOMER)
                .build();

        userRepository.save(userEntity);

        String jwtToken = jwtService.generateToken(userEntity.getEmail());

        return new TokenResponseDto(jwtToken);
    }

    @Override
    public TokenResponseDto signIn(SignInRequestDto requestDto) {
        UserEntity userEntity = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email " + requestDto.getEmail() + " not found"
                ));

        boolean passwordMatches = passwordEncoder.matches(
                requestDto.getPassword(),
                userEntity.getPassword()
        );

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String jwtToken = jwtService.generateToken(userEntity.getEmail());

        return new TokenResponseDto(jwtToken);
    }
}