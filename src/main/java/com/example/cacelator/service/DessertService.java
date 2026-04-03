
package com.example.cacelator.service;

import com.example.cacelator.controller.dto.dessert.DessertCreateRequestDto;
import com.example.cacelator.controller.dto.dessert.DessertResponseDto;
import com.example.cacelator.controller.dto.dessert.DessertUpdateRequestDto;
import java.util.List;
import java.util.UUID;

public interface DessertService {

    DessertResponseDto createDessert(UUID userId, DessertCreateRequestDto requestDto);

    List<DessertResponseDto> getDesserts(UUID userId);

    DessertResponseDto getDessert(UUID userId, UUID dessertId);

    DessertResponseDto updateDessert(UUID userId, UUID dessertId, DessertUpdateRequestDto requestDto);

    void deleteDessert(UUID userId, UUID dessertId);
}