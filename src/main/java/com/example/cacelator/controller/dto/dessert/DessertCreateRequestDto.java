package com.example.cacelator.controller.dto.dessert;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DessertCreateRequestDto {

    @NotBlank
    private String name;

    private String description;

    private BigDecimal baseWeight;
    private BigDecimal baseDiameter;
    private Integer servingsDefault;
}