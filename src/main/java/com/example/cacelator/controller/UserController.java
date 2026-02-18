package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.*;
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

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody SignUpRequestDto signUpRequestDto) {

        User user = userService.createUser(signUpRequestDto.getDisplayName(),
                signUpRequestDto.getPhoneNumber(),
                signUpRequestDto.getEmail(),
                signUpRequestDto.getPassword());
        UserDto userdto = userMapper.toDto(user);


        return ResponseEntity.ok().body(userdto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> userList = userService.getUsers();
        List<UserDto> userDtoList = userList.stream().map(user -> userMapper.toDto(user))
                .toList();


        return ResponseEntity.ok().body(userDtoList);
    }


//    @GetMapping("/users/{adminId}")
//    public ResponseEntity<UserDto> getUsers(@PathVariable UUID userId) {
//
//
//        return ResponseEntity.ok().body(userDto);
//    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {

        User user = userService.getUser(userId);

        UserDto userDto = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDto);

    }

    @PutMapping("/users/{userId}/activate")
    public ResponseEntity<UserDto> activeUser(@PathVariable UUID userId) {

        User user = userService.activateUser(userId);

        UserDto userDto = userMapper.toDto(user);

        return ResponseEntity.ok().body(userDto);
    }

}
