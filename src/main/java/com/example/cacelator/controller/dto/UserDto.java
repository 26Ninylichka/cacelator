package com.example.cacelator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String displayName;
    private String phoneNumber;
    private String email;
    private Status status;
    private Type type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
