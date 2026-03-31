package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.UserDto;
import com.example.cacelator.mapper.UserMapper;
import com.example.cacelator.service.UserService;
import com.example.cacelator.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> userList = userService.getUsers();
        List<UserDto> userDtoList = userList.stream().map(user -> userMapper.toDto(user))
                .toList();

        return ResponseEntity.ok().body(userDtoList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
        User user = userService.getUser(userId);
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID userId, @RequestBody UserDto userDto) {
        User user = userService.updateUser(
                userId,
                userDto.getDisplayName(),
                userDto.getPhoneNumber(),
                userDto.getEmail());
        return ResponseEntity.ok().body(userMapper.toDto(user));
    }

    @PutMapping("/users/{userId}/activate")
    public ResponseEntity<UserDto> activateUser(@PathVariable UUID userId) {
        User user = userService.activateUser(userId);
        return ResponseEntity.ok().body(userMapper.toDto(user));
    }

    @PutMapping("/users/{userId}/block")
    public ResponseEntity<UserDto> blockUser(@PathVariable UUID userId) {
        User user = userService.blockUser(userId);
        return ResponseEntity.ok().body(userMapper.toDto(user));
    }
}
