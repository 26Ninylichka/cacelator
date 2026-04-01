package com.example.cacelator.service;

import com.example.cacelator.dto.componentitem.ComponentItemCreateRequestDto;
import com.example.cacelator.dto.componentitem.ComponentItemResponseDto;
import com.example.cacelator.dto.componentitem.ComponentItemUpdateRequestDto;
import java.util.List;
import java.util.UUID;

public interface ComponentItemService {

    ComponentItemResponseDto createItem(UUID componentId, ComponentItemCreateRequestDto requestDto);

    List<ComponentItemResponseDto> getItems(UUID componentId);

    ComponentItemResponseDto getItem(UUID componentId, UUID itemId);

    ComponentItemResponseDto updateItem(
            UUID componentId,
            UUID itemId,
            ComponentItemUpdateRequestDto requestDto
    );

    void deleteItem(UUID componentId, UUID itemId);
}