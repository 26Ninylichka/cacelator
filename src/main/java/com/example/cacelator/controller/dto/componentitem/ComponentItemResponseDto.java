package com.example.cacelator.dto.componentitem;

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
public class ComponentItemResponseDto {
    private UUID id;
    private UUID componentId;
    private UUID productId;
    private BigDecimal quantity;
    private String unit;
    private Integer sortOrder;
}