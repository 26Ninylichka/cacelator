package com.example.cacelator.controller.exceptionHandler;

import com.example.cacelator.controller.dto.ErrorDto;
import com.example.cacelator.exception.UserAlreadyExistsException;
import com.example.cacelator.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerUserNotFoundException(UserNotFoundException e) {
        ErrorDto errorDto = new ErrorDto(e.getMessage());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handlerUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorDto errorDto = new ErrorDto(e.getMessage());
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}