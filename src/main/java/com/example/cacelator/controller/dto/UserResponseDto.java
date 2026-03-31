package com.example.cacelator.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private String email;
    private Status status;
    private Type type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
