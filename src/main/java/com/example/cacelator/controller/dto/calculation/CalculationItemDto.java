package com.example.cacelator.controller.dto.calculation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CalculationItemDto {

    private String componentName;
    private String productName;

    private BigDecimal baseQty;
    private BigDecimal scaledQty;

    private String unitCode;

    private BigDecimal packagePrice;
    private BigDecimal packageQty;

    private BigDecimal cost;
}
