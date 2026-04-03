package com.example.cacelator.controller.dto.calculation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CalcRunRequestDto {

    private UUID dessertId;

    // BY_DIAMETER / BY_WEIGHT / BY_SERVINGS
    private String inputMode;

    private BigDecimal targetDiameterCm;

    private BigDecimal targetWeightG;

    private Integer targetServings;
}