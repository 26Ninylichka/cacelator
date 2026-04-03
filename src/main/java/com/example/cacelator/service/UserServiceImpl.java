package com.example.cacelator.service;

import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.UpdateUserRequestDto;
import com.example.cacelator.data.entity.UserEntity;
import com.example.cacelator.data.repository.UserRepository;
import com.example.cacelator.exception.UserAlreadyExistsException;
import com.example.cacelator.exception.UserNotFoundException;
import com.example.cacelator.service.model.Status;
import com.example.cacelator.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(SignUpRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException(
                    "User with email " + requestDto.getEmail() + " already exists"
            );
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID());
        userEntity.setName(requestDto.getName());
        userEntity.setEmail(requestDto.getEmail());
        userEntity.setPassword(requestDto.getPassword());
        userEntity.setPhoneNumber(requestDto.getPhoneNumber());
        userEntity.setStatus(Status.CREATED.name());
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity savedUser = userRepository.save(userEntity);
        return mapToUser(savedUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUser)
                .toList();
    }

    @Override
    public User getUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        return mapToUser(userEntity);
    }

    @Override
    public User activateUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        userEntity.setStatus(Status.ACTIVE.name());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUser(updatedUser);
    }

    @Override
    public User blockUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        userEntity.setStatus(Status.BLOCKED.name());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUser(updatedUser);
    }

    @Override
    public User updateUser(UUID userId, UpdateUserRequestDto requestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        if (requestDto.getName() != null && !requestDto.getName().isBlank()) {
            userEntity.setName(requestDto.getName());
        }

        if (requestDto.getEmail() != null && !requestDto.getEmail().isBlank()) {
            userEntity.setEmail(requestDto.getEmail());
        }

        if (requestDto.getPhoneNumber() != null && !requestDto.getPhoneNumber().isBlank()) {
            userEntity.setPhoneNumber(requestDto.getPhoneNumber());
        }

        if (requestDto.getStatus() != null) {
            userEntity.setStatus(requestDto.getStatus().name());
        }

        userEntity.setUpdatedAt(LocalDateTime.now());

        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUser(updatedUser);
    }

    private User mapToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getUserId())
                .email(userEntity.getEmail())
                .status(Status.valueOf(userEntity.getStatus()))
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }
}