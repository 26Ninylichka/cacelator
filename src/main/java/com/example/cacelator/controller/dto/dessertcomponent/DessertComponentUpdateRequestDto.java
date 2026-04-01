package com.example.cacelator.dto.dessertcomponent;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DessertComponentUpdateRequestDto {
    private UUID componentId;
    private Integer sortOrder;
    private String note;
}