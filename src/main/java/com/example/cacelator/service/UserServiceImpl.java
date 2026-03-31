package com.example.cacelator.service;

import com.example.cacelator.exception.UserAlreadyExistsException;
import com.example.cacelator.exception.UserNotFoundException;
import com.example.cacelator.service.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.cacelator.service.model.Status;
import com.example.cacelator.service.model.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private HashMap<UUID, com.example.cacelator.service.model.User> userHashMap = new HashMap<>();

    @Override
    public User createUser(String displayName, String phoneNumber, String email, String password) {

        log.info("Attempting to create an user with email{} ", email);

        Optional<User> optionalUser = userHashMap.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }

        User user = new User();
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        Instant now = Instant.now();

        user.setType(Type.USER);
        user.setStatus(Status.ACTIVE);
        user.setId(UUID.randomUUID());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userHashMap.put(user.getId(), user);

        log.info("Succsessfully created an user with email{} ", email);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userHashMap.values().stream().toList();

    }

    @Override
    public User getUser(UUID userId) {
        User user = userHashMap.get(userId);

        if (user != null) {
            return user;

        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public User updateUser(UUID userId, String displayName, String phoneNumber, String email) {
        User user = userHashMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        user.setDisplayName(displayName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setUpdatedAt(Instant.now());

        userHashMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User activateUser(UUID userId) {
        User user = userHashMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        user.setStatus(Status.ACTIVE);
        user.setUpdatedAt(Instant.now());
        userHashMap.put(user.getId(), user);

        return user;
    }

    @Override
    public User blockUser(UUID userId) {
        User user = userHashMap.get(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        user.setStatus(Status.BLOCKED);
        user.setUpdatedAt(Instant.now());
        userHashMap.put(user.getId(), user);

        return user;
    }
}
