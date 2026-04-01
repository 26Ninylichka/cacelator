package com.example.cacelator.service;

import com.example.cacelator.dto.dessertcomponent.DessertComponentCreateRequestDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentResponseDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentUpdateRequestDto;
import java.util.List;
import java.util.UUID;

public interface DessertComponentService {

    DessertComponentResponseDto createDessertComponent(
            UUID dessertId,
            DessertComponentCreateRequestDto requestDto
    );

    List<DessertComponentResponseDto> getDessertComponents(UUID dessertId);

    DessertComponentResponseDto getDessertComponent(UUID dessertId, UUID dessertComponentId);

    DessertComponentResponseDto updateDessertComponent(
            UUID dessertId,
            UUID dessertComponentId,
            DessertComponentUpdateRequestDto requestDto
    );

    void deleteDessertComponent(UUID dessertId, UUID dessertComponentId);
}