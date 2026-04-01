
package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.DessertComponentEntity;
import com.example.cacelator.dto.dessertcomponent.DessertComponentCreateRequestDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentResponseDto;
import com.example.cacelator.dto.dessertcomponent.DessertComponentUpdateRequestDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.repository.DessertComponentRepository;
import com.example.cacelator.service.DessertComponentService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DessertComponentServiceImpl implements DessertComponentService {

    private final DessertComponentRepository dessertComponentRepository;

    @Override
    public DessertComponentResponseDto createDessertComponent(
            UUID dessertId,
            DessertComponentCreateRequestDto requestDto
    ) {
        DessertComponentEntity entity = DessertComponentEntity.builder()
                .dessertId(dessertId)
                .componentId(requestDto.getComponentId())
                .sortOrder(requestDto.getSortOrder())
                .note(requestDto.getNote())
                .build();

        DessertComponentEntity saved = dessertComponentRepository.save(entity);
        return mapToDto(saved);
    }

    @Override
    public List<DessertComponentResponseDto> getDessertComponents(UUID dessertId) {
        return dessertComponentRepository.findAllByDessertIdOrderBySortOrderAsc(dessertId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public DessertComponentResponseDto getDessertComponent(UUID dessertId, UUID dessertComponentId) {
        DessertComponentEntity entity = dessertComponentRepository.findByIdAndDessertId(
                        dessertComponentId, dessertId
                )
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert component with id " + dessertComponentId + " not found"
                ));

        return mapToDto(entity);
    }

    @Override
    public DessertComponentResponseDto updateDessertComponent(
            UUID dessertId,
            UUID dessertComponentId,
            DessertComponentUpdateRequestDto requestDto
    ) {
        DessertComponentEntity entity = dessertComponentRepository.findByIdAndDessertId(
                        dessertComponentId, dessertId
                )
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert component with id " + dessertComponentId + " not found"
                ));

        if (requestDto.getComponentId() != null) {
            entity.setComponentId(requestDto.getComponentId());
        }

        if (requestDto.getSortOrder() != null) {
            entity.setSortOrder(requestDto.getSortOrder());
        }

        if (requestDto.getNote() != null) {
            entity.setNote(requestDto.getNote());
        }

        DessertComponentEntity updated = dessertComponentRepository.save(entity);
        return mapToDto(updated);
    }

    @Override
    public void deleteDessertComponent(UUID dessertId, UUID dessertComponentId) {
        DessertComponentEntity entity = dessertComponentRepository.findByIdAndDessertId(
                        dessertComponentId, dessertId
                )
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert component with id " + dessertComponentId + " not found"
                ));

        dessertComponentRepository.delete(entity);
    }

    private DessertComponentResponseDto mapToDto(DessertComponentEntity entity) {
        return DessertComponentResponseDto.builder()
                .id(entity.getId())
                .dessertId(entity.getDessertId())
                .componentId(entity.getComponentId())
                .sortOrder(entity.getSortOrder())
                .note(entity.getNote())
                .build();
    }
}