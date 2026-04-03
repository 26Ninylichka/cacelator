package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.ComponentItemEntity;
import com.example.cacelator.controller.dto.componentitem.ComponentItemCreateRequestDto;
import com.example.cacelator.controller.dto.componentitem.ComponentItemResponseDto;
import com.example.cacelator.controller.dto.componentitem.ComponentItemUpdateRequestDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.data.repository.ComponentItemRepository;
import com.example.cacelator.service.ComponentItemService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComponentItemServiceImpl implements ComponentItemService {

    private final ComponentItemRepository componentItemRepository;

    @Override
    public ComponentItemResponseDto createItem(UUID componentId, ComponentItemCreateRequestDto requestDto) {
        ComponentItemEntity item = ComponentItemEntity.builder()
                .componentId(componentId)
                .productId(requestDto.getProductId())
                .quantity(requestDto.getQuantity())
                .unit(requestDto.getUnit())
                .sortOrder(requestDto.getSortOrder())
                .build();

        ComponentItemEntity saved = componentItemRepository.save(item);
        return mapToDto(saved);
    }

    @Override
    public List<ComponentItemResponseDto> getItems(UUID componentId) {
        return componentItemRepository
                .findAllByComponentIdOrderBySortOrderAsc(componentId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ComponentItemResponseDto getItem(UUID componentId, UUID itemId) {
        ComponentItemEntity item = componentItemRepository.findByIdAndComponentId(itemId, componentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Component item with id " + itemId + " not found"
                ));

        return mapToDto(item);
    }

    @Override
    public ComponentItemResponseDto updateItem(
            UUID componentId,
            UUID itemId,
            ComponentItemUpdateRequestDto requestDto
    ) {
        ComponentItemEntity item = componentItemRepository
                .findByIdAndComponentId(itemId, componentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Component item with id " + itemId + " not found"
                ));

        if (requestDto.getProductId() != null) {
            item.setProductId(requestDto.getProductId());
        }

        if (requestDto.getQuantity() != null) {
            item.setQuantity(requestDto.getQuantity());
        }

        if (requestDto.getUnit() != null) {
            item.setUnit(requestDto.getUnit());
        }

        if (requestDto.getSortOrder() != null) {
            item.setSortOrder(requestDto.getSortOrder());
        }

        ComponentItemEntity updated = componentItemRepository.save(item);
        return mapToDto(updated);
    }

    @Override
    public void deleteItem(UUID componentId, UUID itemId) {
        ComponentItemEntity item = componentItemRepository.findByIdAndComponentId(itemId, componentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Component item with id " + itemId + " not found"
                ));

        componentItemRepository.delete(item);
    }

    private ComponentItemResponseDto mapToDto(ComponentItemEntity entity) {
        return ComponentItemResponseDto.builder()
                .id(entity.getId())
                .componentId(entity.getComponentId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .unit(entity.getUnit())
                .sortOrder(entity.getSortOrder())
                .build();
    }
}