package com.example.cacelator.service;

import com.example.cacelator.service.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(String displayName, String phoneNumber, String email, String password);

    List<User> getUsers();

    User getUser(UUID userId);

    User activateUser(UUID userId);

}
