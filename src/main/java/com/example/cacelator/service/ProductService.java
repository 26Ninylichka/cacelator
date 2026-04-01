package com.example.cacelator.service;

import com.example.cacelator.controller.dto.ProductCreateRequestDto;
import com.example.cacelator.controller.dto.ProductResponseDto;
import com.example.cacelator.controller.dto.ProductUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDto createProduct(UUID userId, ProductCreateRequestDto requestDto);

    List<ProductResponseDto> getProducts(UUID userId);

    ProductResponseDto getProduct(UUID userId, UUID productId);

    ProductResponseDto updateProduct(UUID userId, UUID productId, ProductUpdateRequestDto requestDto);

    void deleteProduct(UUID userId, UUID productId);
}