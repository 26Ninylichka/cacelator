package com.example.cacelator.controller.dto.dessertcomponent;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DessertComponentCreateRequestDto {

    @NotNull
    private UUID componentId;

    private Integer sortOrder;

    private String note;
}