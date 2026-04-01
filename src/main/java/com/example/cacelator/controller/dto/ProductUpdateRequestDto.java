package com.example.cacelator.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductUpdateRequestDto {

    private String name;

    private UUID defaultUnitId;

    private String status;

    private String note;

    private Boolean isActive;
}