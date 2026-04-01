package com.example.cacelator.dto.componentitem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentItemCreateRequestDto {

    @NotNull
    private UUID productId;

    @NotNull
    private BigDecimal quantity;

    @NotBlank
    private String unit;

    @NotNull
    private Integer sortOrder;
}