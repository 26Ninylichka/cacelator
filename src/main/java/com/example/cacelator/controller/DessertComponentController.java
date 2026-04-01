package com.example.cacelator.controller;

import com.example.cacelator.dto.dessertcomponent.DessertComponentCreateRequestDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentResponseDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentUpdateRequestDto;
import com.example.cacelator.service.DessertComponentService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desserts/{dessertId}/components")
@RequiredArgsConstructor
public class DessertComponentController {

    private final DessertComponentService dessertComponentService;

    @PostMapping
    public DessertComponentResponseDto createDessertComponent(
            @PathVariable UUID dessertId,
            @RequestBody @Valid DessertComponentCreateRequestDto requestDto
    ) {
        return dessertComponentService.createDessertComponent(dessertId, requestDto);
    }

    @GetMapping
    public List<DessertComponentResponseDto> getDessertComponents(
            @PathVariable UUID dessertId
    ) {
        return dessertComponentService.getDessertComponents(dessertId);
    }

    @GetMapping("/{dessertComponentId}")
    public DessertComponentResponseDto getDessertComponent(
            @PathVariable UUID dessertId,
            @PathVariable UUID dessertComponentId
    ) {
        return dessertComponentService.getDessertComponent(dessertId, dessertComponentId);
    }

    @PutMapping("/{dessertComponentId}")
    public DessertComponentResponseDto updateDessertComponent(
            @PathVariable UUID dessertId,
            @PathVariable UUID dessertComponentId,
            @RequestBody @Valid DessertComponentUpdateRequestDto requestDto
    ) {
        return dessertComponentService.updateDessertComponent(
                dessertId, dessertComponentId, requestDto
        );
    }

    @DeleteMapping("/{dessertComponentId}")
    public void deleteDessertComponent(
            @PathVariable UUID dessertId,
            @PathVariable UUID dessertComponentId
    ) {
        dessertComponentService.deleteDessertComponent(dessertId, dessertComponentId);
    }
}