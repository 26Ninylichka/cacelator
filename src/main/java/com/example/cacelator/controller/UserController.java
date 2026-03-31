package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.UpdateUserRequestDto;
import com.example.cacelator.model.User;
import com.example.cacelator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid SignUpRequestDto requestDto) {
        return ResponseEntity.ok(userService.createUser(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId,
                                           @RequestBody @Valid UpdateUserRequestDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(userId, requestDto));
    }

    @PutMapping("/{userId}/activate")
    public ResponseEntity<User> activateUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.activateUser(userId));
    }

    @PutMapping("/{userId}/block")
    public ResponseEntity<User> blockUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.blockUser(userId));
    }
}