package com.example.cacelator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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
    private Instant createdAt;
    private Instant updatedAt;

}
