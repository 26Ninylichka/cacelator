package com.example.cacelator.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ProductResponseDto {

    private UUID id;

    private UUID userId;

    private String name;

    private UUID defaultUnitId;

    private String status;

    private String note;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}