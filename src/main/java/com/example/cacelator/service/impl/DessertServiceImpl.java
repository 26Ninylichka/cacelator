package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.DessertEntity;
import com.example.cacelator.controller.dto.dessert.DessertCreateRequestDto;
import com.example.cacelator.controller.dto.dessert.DessertResponseDto;
import com.example.cacelator.controller.dto.dessert.DessertUpdateRequestDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.data.repository.DessertRepository;
import com.example.cacelator.service.DessertService;

import java.time.OffsetDateTime;
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

        DessertEntity dessert = new DessertEntity();
        dessert.setUserId(userId);
        dessert.setName(requestDto.getName());
        dessert.setBaseWeight(requestDto.getBaseWeight());
        dessert.setBaseDiameter(requestDto.getBaseDiameter());
        dessert.setServingsDefault(requestDto.getServingsDefault());
        dessert.setId(UUID.randomUUID());
        dessert.setCreatedAt(OffsetDateTime.now());

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
        DessertEntity desert = dessertRepository.findByIdAndUserId(dessertId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Dessert with id " + dessertId + " not found"
                ));

        return mapToDto(desert);
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

        if (requestDto.getBaseWeight() != null) {
            dessert.setBaseWeight(requestDto.getBaseWeight());
        }

        if (requestDto.getBaseDiameter() != null) {
            dessert.setBaseDiameter(requestDto.getBaseDiameter());
        }

        if (requestDto.getServingsDefault() != null) {
            dessert.setServingsDefault(requestDto.getServingsDefault());
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
                .baseWeight(entity.getBaseWeight())
                .baseDiameter(entity.getBaseDiameter())
                .servingsDefault(entity.getServingsDefault())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .build();
    }
}
