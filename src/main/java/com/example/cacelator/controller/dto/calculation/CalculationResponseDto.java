package com.example.cacelator.controller.dto.calculation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CalculationResponseDto {

    private UUID dessertId;
    private String dessertName;
    private String inputMode;

    private BigDecimal scaleFactor;

    private BigDecimal baseDiameterCm;
    private BigDecimal targetDiameterCm;

    private BigDecimal baseWeightG;
    private BigDecimal targetWeightG;

    private Integer baseServings;
    private Integer targetServings;

    private BigDecimal totalCost;

    private List<CalculationItemDto> items;
}