package com.example.cacelator.controller;

import com.example.cacelator.dto.componentitem.ComponentItemCreateRequestDto;
import com.example.cacelator.dto.componentitem.ComponentItemResponseDto;
import com.example.cacelator.dto.componentitem.ComponentItemUpdateRequestDto;
import com.example.cacelator.service.ComponentItemService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/components/{componentId}/items")
@RequiredArgsConstructor
public class ComponentItemController {

    private final ComponentItemService componentItemService;

    @PostMapping
    public ComponentItemResponseDto createItem(
            @PathVariable UUID componentId,
            @RequestBody @Valid ComponentItemCreateRequestDto requestDto
    ) {
        return componentItemService.createItem(componentId, requestDto);
    }

    @GetMapping
    public List<ComponentItemResponseDto> getItems(@PathVariable UUID componentId) {
        return componentItemService.getItems(componentId);
    }

    @GetMapping("/{itemId}")
    public ComponentItemResponseDto getItem(
            @PathVariable UUID componentId,
            @PathVariable UUID itemId
    ) {
        return componentItemService.getItem(componentId, itemId);
    }

    @PutMapping("/{itemId}")
    public ComponentItemResponseDto updateItem(
            @PathVariable UUID componentId,
            @PathVariable UUID itemId,
            @RequestBody @Valid ComponentItemUpdateRequestDto requestDto
    ) {
        return componentItemService.updateItem(componentId, itemId, requestDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(
            @PathVariable UUID componentId,
            @PathVariable UUID itemId
    ) {
        componentItemService.deleteItem(componentId, itemId);
    }
}