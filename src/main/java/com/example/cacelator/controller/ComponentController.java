package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.ComponentCreateRequestDto;
import com.example.cacelator.controller.dto.ComponentResponseDto;
import com.example.cacelator.controller.dto.ComponentUpdateRequestDto;
import com.example.cacelator.service.ComponentService;
import com.example.cacelator.service.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/components")
@RequiredArgsConstructor
public class ComponentController {

    private final ComponentService componentService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ComponentResponseDto> create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid ComponentCreateRequestDto request
    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString((jwtUtil.extractUserId(token)));

        return ResponseEntity.ok(componentService.createComponent(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<ComponentResponseDto>> getComponents(
            @RequestHeader("Authorization") String authHeader

    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));

        return ResponseEntity.ok(componentService.getComponents(userId));
    }

    @GetMapping("/{componentId}")
    public ResponseEntity<ComponentResponseDto> getComponent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID componentId
    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));

        return ResponseEntity.ok(componentService.getComponent(userId, componentId));
    }

    @PutMapping("/{componentId}")
    public ResponseEntity<ComponentResponseDto> updateComponent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID componentId,
            @RequestBody @Valid ComponentUpdateRequestDto requestDto
    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));

        return ResponseEntity.ok(componentService.updateComponent(userId, componentId, requestDto));
    }

    @DeleteMapping("/{componentId}")
    public ResponseEntity<Void> deleteComponent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable UUID componentId
    ) {
        String token = authHeader.substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        
        componentService.deleteComponent(userId, componentId);
        return ResponseEntity.noContent().build();
    }
}
