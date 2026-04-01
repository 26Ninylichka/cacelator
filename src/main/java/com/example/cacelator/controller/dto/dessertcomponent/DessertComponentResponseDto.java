package com.example.cacelator.dto.dessertcomponent;

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
public class DessertComponentResponseDto {
    private UUID id;
    private UUID dessertId;
    private UUID componentId;
    private Integer sortOrder;
    private String note;
}