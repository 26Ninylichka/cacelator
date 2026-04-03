package com.example.cacelator.controller.calculation;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingListItemDto {
    private UUID productId;
    private String productName;
    private String unit;
    private BigDecimal totalQuantity;
}