package com.example.cacelator.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductCreateRequestDto {

    @NotBlank(message = "Product name must not be empty")
    private String name;

    @NotNull(message = "Default unit id must not be null")
    private UUID defaultUnitId;

    private String status;

    private String note;

    private Boolean isActive;
}