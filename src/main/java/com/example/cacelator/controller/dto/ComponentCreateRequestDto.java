package com.example.cacelator.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentCreateRequestDto {

    @NotBlank
    private String name;

    private String description;
}