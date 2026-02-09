package com.example.cacelator.mapper;

import com.example.cacelator.service.model.Status;
import com.example.cacelator.controller.dto.UserDto;
import com.example.cacelator.service.model.Type;
import com.example.cacelator.service.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toDomain(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setDisplayName(dto.getDisplayName());
        user.setEmail(dto.getEmail());

        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setType(Type.valueOf(dto.getType().name()));
        user.setStatus(Status.valueOf(dto.getStatus().name()));
        return user;
    }


    public UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setType(com.example.cacelator.controller.dto.Type.valueOf(user.getType().name()));
        dto.setStatus(com.example.cacelator.controller.dto.Status.valueOf(user.getStatus().name()));
        return dto;
    }
}
