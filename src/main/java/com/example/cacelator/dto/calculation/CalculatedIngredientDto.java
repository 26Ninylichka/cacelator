package com.example.cacelator.dto.calculation;

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
public class CalculatedIngredientDto {
    private UUID componentId;
    private String componentName;

    private UUID productId;
    private String productName;

    private String unit;
    private Integer sortOrder;
    private BigDecimal originalQuantity;
    private BigDecimal scaledQuantity;
}