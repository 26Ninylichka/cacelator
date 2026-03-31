package com.example.cacelator.service;

import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.UpdateUserRequestDto;
import com.example.cacelator.service.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(SignUpRequestDto requestDto);

    List<User> getUsers();

    User getUser(UUID userId);

    User activateUser(UUID userId);

    User blockUser(UUID userId);

    User updateUser(UUID userId, UpdateUserRequestDto requestDto);
}