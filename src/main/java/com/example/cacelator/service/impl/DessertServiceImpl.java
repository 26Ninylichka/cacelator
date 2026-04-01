package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.DessertEntity;
import com.example.cacelator.dto.dessert.DessertCreateRequestDto;
import com.example.cacelator.dto.dessert.DessertResponseDto;
import com.example.cacelator.dto.dessert.DessertUpdateRequestDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.repository.DessertRepository;
import com.example.cacelator.service.DessertService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DessertServiceImpl implements DessertService {

    private final DessertRepository dessertRepository;

    @Override
    public DessertResponseDto createDessert(UUID userId, DessertCreateRequestDto requestDto) {
        if (dessertRepository.existsByNameAndUserId(requestDto.getName(), userId)) {
            throw new IllegalArgumentException(
                    "Dessert with name " + requestDto.getName() + " already exists"
            );
        }

        DessertEntity dessert = DessertEntity.builder()
                .userId(userId)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .baseWeight(requestDto.getBaseWeight())
                .build();

        DessertEntity savedDessert = dessertRepository.save(dessert);

        return mapToDto(savedDessert);
    }

    @Override
    public List<DessertResponseDto> getDesserts(UUID userId) {
        return dessertRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();

    }

    @Override
    public DessertResponseDto getDessert(UUID userId, UUID dessertId) {
        DessertEntity dessert = dessertRepository.findByIdAndUserId(dessertId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert with id " + dessertId + " not found"
                ));

        return mapToDto(dessert);
    }

    @Override
    public DessertResponseDto updateDessert(UUID userId, UUID dessertId, DessertUpdateRequestDto requestDto) {
        DessertEntity dessert = dessertRepository.findByIdAndUserId(dessertId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert with id " + dessertId + " not found"
                ));

        if (requestDto.getName() != null) {
            dessert.setName(requestDto.getName());
        }

        if (requestDto.getDescription() != null) {
            dessert.setDescription(requestDto.getDescription());
        }

        DessertEntity updatedDessert = dessertRepository.save(dessert);

        return mapToDto(updatedDessert);
    }

    @Override
    public void deleteDessert(UUID userId, UUID dessertId) {
        DessertEntity dessert = dessertRepository.findByIdAndUserId(dessertId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert with id " + dessertId + " not found"
                ));

        dessertRepository.delete(dessert);
    }

    private DessertResponseDto mapToDto(DessertEntity entity) {
        return DessertResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
