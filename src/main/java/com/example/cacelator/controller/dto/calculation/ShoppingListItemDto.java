package com.example.cacelator.controller.dto.calculation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ShoppingListItemDto {

    private String productName;
    private BigDecimal totalQty;
    private String unitCode;
}
