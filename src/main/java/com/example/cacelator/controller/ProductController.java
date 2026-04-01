package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.ProductCreateRequestDto;
import com.example.cacelator.controller.dto.ProductResponseDto;
import com.example.cacelator.controller.dto.ProductUpdateRequestDto;
import com.example.cacelator.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestParam UUID userId,
            @RequestBody @Valid ProductCreateRequestDto requestDto
    ) {
        return ResponseEntity.ok(productService.createProduct(userId, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(
            @RequestParam UUID userId
    ) {
        return ResponseEntity.ok(productService.getProducts(userId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(
            @RequestParam UUID userId,
            @PathVariable UUID productId
    ) {
        return ResponseEntity.ok(productService.getProduct(userId, productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @RequestParam UUID userId,
            @PathVariable UUID productId,
            @RequestBody @Valid ProductUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(productService.updateProduct(userId, productId, requestDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @RequestParam UUID userId,
            @PathVariable UUID productId
    ) {
        productService.deleteProduct(userId, productId);
        return ResponseEntity.noContent().build();
    }
}