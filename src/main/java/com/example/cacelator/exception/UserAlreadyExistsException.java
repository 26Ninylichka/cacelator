package com.example.cacelator.exception;

import java.util.UUID;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String email) {
    super("User with email " + email + "already exists.");
    }
}
