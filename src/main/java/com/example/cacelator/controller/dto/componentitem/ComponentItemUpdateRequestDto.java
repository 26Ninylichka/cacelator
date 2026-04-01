package com.example.cacelator.dto.componentitem;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentItemUpdateRequestDto {
    private UUID productId;
    private BigDecimal quantity;
    private String unit;
    private Integer sortOrder;
}