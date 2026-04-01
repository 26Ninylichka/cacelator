package com.example.cacelator.service;

import com.example.cacelator.controller.dto.ComponentCreateRequestDto;
import com.example.cacelator.controller.dto.ComponentResponseDto;
import com.example.cacelator.controller.dto.ComponentUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface ComponentService {

    ComponentResponseDto createComponent(UUID userId, ComponentCreateRequestDto requestDto);

    List<ComponentResponseDto> getComponents(UUID userId);

    ComponentResponseDto getComponent(UUID userId, UUID componentId);

    ComponentResponseDto updateComponent(UUID userId, UUID componentId, ComponentUpdateRequestDto requestDto);

    void deleteComponent(UUID userId, UUID componentId);
}