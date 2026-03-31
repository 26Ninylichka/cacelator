package com.example.cacelator.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID userId) {
        super("User with id " + userId.toString() + " was not found.");

    }
    public UserNotFoundException(String message){
        super(message);
    }

}
