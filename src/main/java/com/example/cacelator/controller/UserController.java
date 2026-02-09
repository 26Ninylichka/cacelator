package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.SignUpRequestDto;
import com.example.cacelator.controller.dto.Status;
import com.example.cacelator.controller.dto.Type;
import com.example.cacelator.controller.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private HashMap<UUID, UserDto> userDtoHashMap = new HashMap<>();

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody SignUpRequestDto signUpRequestDto) {

        UserDto userDto = new UserDto();
        userDto.setDisplayName(signUpRequestDto.getDisplayName());
        userDto.setEmail(signUpRequestDto.getEmail());
        userDto.setPhoneNumber(signUpRequestDto.getPhoneNumber());
        userDto.setType(Type.USER);
        userDto.setStatus(Status.ACTIVE);
        userDto.setId(UUID.randomUUID());
        userDto.setCreatedAt(Instant.now());
        userDto.setUpdatedAt(Instant.now());


        userDtoHashMap.put(userDto.getId(), userDto);


        return ResponseEntity.ok().body(new UserDto());
    }

  @GetMapping("/users")
    public  ResponseEntity<List<UserDto>> getUsers(){

      List<UserDto> userDtos =  userDtoHashMap.values().stream().toList();
      return ResponseEntity.ok().body(userDtos);
  }


    @GetMapping("/users/{adminId}")
    public  ResponseEntity<UserDto> getUsers(@PathVariable UUID userId){

        UserDto userDto = userDtoHashMap.get(userId);

        return ResponseEntity.ok().body(userDto);
    }

//@GetMapping("/users{auth}/login") я на цьому моменті не зрозуміла мені тут active чи логіку авторизаціі





}
