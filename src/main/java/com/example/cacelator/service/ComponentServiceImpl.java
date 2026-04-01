package com.example.cacelator.service;

import com.example.cacelator.controller.dto.ComponentCreateRequestDto;
import com.example.cacelator.controller.dto.ComponentResponseDto;
import com.example.cacelator.controller.dto.ComponentUpdateRequestDto;
import com.example.cacelator.data.entity.ComponentEntity;
import com.example.cacelator.data.repository.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;

    @Override
    public ComponentResponseDto createComponent(UUID userId, ComponentCreateRequestDto requestDto) {
        if (componentRepository.existsByNameAndUserId(requestDto.getName(), userId)) {
            throw new IllegalArgumentException(
                    "Component with name " + requestDto.getName() + " already exists"
            );
        }

        ComponentEntity componentEntity = ComponentEntity.builder()
                .userId(userId)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();

        ComponentEntity savedComponent = componentRepository.save(componentEntity);

        return mapToDto(savedComponent);
    }

    @Override
    public List<ComponentResponseDto> getComponents(UUID userId) {
        return componentRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ComponentResponseDto getComponent(UUID userId, UUID componentId) {
        ComponentEntity componentEntity = componentRepository.findByIdAndUserId(componentId, userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Component with id " + componentId + " not found"
                ));

        return mapToDto(componentEntity);
    }

    @Override
    public ComponentResponseDto updateComponent(UUID userId, UUID componentId, ComponentUpdateRequestDto requestDto) {
        ComponentEntity componentEntity = componentRepository.findByIdAndUserId(componentId, userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Component with id " + componentId + " not found"
                ));

        if (requestDto.getName() != null && !requestDto.getName().isBlank()) {
            componentEntity.setName(requestDto.getName());
        }

        if (requestDto.getDescription() != null) {
            componentEntity.setDescription(requestDto.getDescription());
        }

        ComponentEntity updatedComponent = componentRepository.save(componentEntity);

        return mapToDto(updatedComponent);
    }

    @Override
    public void deleteComponent(UUID userId, UUID componentId) {
        ComponentEntity componentEntity = componentRepository.findByIdAndUserId(componentId, userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Component with id " + componentId + " not found"
                ));

        componentRepository.delete(componentEntity);
    }

    private ComponentResponseDto mapToDto(ComponentEntity entity) {
        return ComponentResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}