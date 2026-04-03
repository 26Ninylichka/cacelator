
package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.dessert.DessertCreateRequestDto;
import com.example.cacelator.controller.dto.dessert.DessertResponseDto;
import com.example.cacelator.controller.dto.dessert.DessertUpdateRequestDto;
import com.example.cacelator.service.DessertService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desserts")
@RequiredArgsConstructor
public class DessertController {

    private final DessertService dessertService;

    @PostMapping
    public DessertResponseDto createDessert(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @RequestBody @Valid DessertCreateRequestDto requestDto
    ) {
        return dessertService.createDessert(userId, requestDto);
    }

    @GetMapping
    public List<DessertResponseDto> getDesserts(
            @RequestHeader(value = "X-User-Id") UUID userId
    ) {
        return dessertService.getDesserts(userId);
    }

    @GetMapping("/{dessertId}")
    public DessertResponseDto getDessert(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID dessertId
    ) {
        return dessertService.getDessert(userId, dessertId);
    }

    @PutMapping("/{dessertId}")
    public DessertResponseDto updateDessert(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID dessertId,
            @RequestBody @Valid DessertUpdateRequestDto requestDto
    ) {
        return dessertService.updateDessert(userId, dessertId, requestDto);
    }

    @DeleteMapping("/{dessertId}")
    public void deleteDessert(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID dessertId
    ) {
        dessertService.deleteDessert(userId, dessertId);
    }
}