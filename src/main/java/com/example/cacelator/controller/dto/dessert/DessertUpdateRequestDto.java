package com.example.cacelator.controller.dto.dessert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DessertUpdateRequestDto {
    private String name;
    private String description;
    private java.math.BigDecimal baseWeight;
    private java.math.BigDecimal baseDiameter;
    private Integer servingsDefault;
}