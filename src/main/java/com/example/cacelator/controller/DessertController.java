
package com.example.cacelator.controller;

import com.example.cacelator.dto.dessert.DessertCreateRequestDto;
import com.example.cacelator.dto.dessert.DessertResponseDto;
import com.example.cacelator.dto.dessert.DessertUpdateRequestDto;
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
            @RequestHeader(value = "Authorization", required = false) String header,
            @RequestBody @Valid DessertCreateRequestDto requestDto
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return dessertService.createDessert(userId, requestDto);
    }

    @GetMapping
    public List<DessertResponseDto> getDesserts(
            @RequestHeader(value = "Authorization", required = false) String header
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return dessertService.getDesserts(userId);
    }

    @GetMapping("/{dessertId}")
    public DessertResponseDto getDessert(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID dessertId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return dessertService.getDessert(userId, dessertId);
    }

    @PutMapping("/{dessertId}")
    public DessertResponseDto updateDessert(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID dessertId,
            @RequestBody @Valid DessertUpdateRequestDto requestDto
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return dessertService.updateDessert(userId, dessertId, requestDto);
    }

    @DeleteMapping("/{dessertId}")
    public void deleteDessert(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID dessertId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        dessertService.deleteDessert(userId, dessertId);
    }
}