package com.example.cacelator.mapper;

import com.example.cacelator.dto.component.ComponentCreateRequestDto;
import com.example.cacelator.dto.component.ComponentResponseDto;

@org.springframework.stereotype.Component
public class ComponentMapper {

    public Component toModel(ComponentCreateRequestDto requestDto) {
        Component component = new Component();
        component.setName(requestDto.getName());
        component.setDescription(requestDto.getDescription());
        return component;
    }

    public ComponentResponseDto toDto(Component component) {
        ComponentResponseDto dto = new ComponentResponseDto();
        dto.setId(component.getId());
        dto.setName(component.getName());
        dto.setDescription(component.getDescription());
        return dto;
    }
}