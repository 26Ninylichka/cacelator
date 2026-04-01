package com.example.cacelator.service;

import com.example.cacelator.controller.dto.ProductCreateRequestDto;
import com.example.cacelator.controller.dto.ProductResponseDto;
import com.example.cacelator.controller.dto.ProductUpdateRequestDto;
import com.example.cacelator.data.entity.ProductEntity;
import com.example.cacelator.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(UUID userId, ProductCreateRequestDto requestDto) {
        if (productRepository.existsByNameAndUserId(requestDto.getName(), userId)) {
            throw new IllegalArgumentException("Product with name " + requestDto.getName() + " already exists");
        }

        ProductEntity productEntity = ProductEntity.builder()
                .userId(userId)
                .name(requestDto.getName())
                .defaultUnitId(requestDto.getDefaultUnitId())
                .status(requestDto.getStatus())
                .note(requestDto.getNote())
                .isActive(requestDto.getIsActive())
                .build();

        ProductEntity savedProduct = productRepository.save(productEntity);

        return mapToDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getProducts(UUID userId) {
        return productRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ProductResponseDto getProduct(UUID userId, UUID productId) {
        ProductEntity productEntity = productRepository.findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        return mapToDto(productEntity);
    }

    @Override
    public ProductResponseDto updateProduct(UUID userId, UUID productId, ProductUpdateRequestDto requestDto) {
        ProductEntity productEntity = productRepository.findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        if (requestDto.getName() != null && !requestDto.getName().isBlank()) {
            productEntity.setName(requestDto.getName());
        }

        if (requestDto.getDefaultUnitId() != null) {
            productEntity.setDefaultUnitId(requestDto.getDefaultUnitId());
        }

        if (requestDto.getStatus() != null && !requestDto.getStatus().isBlank()) {
            productEntity.setStatus(requestDto.getStatus());
        }

        if (requestDto.getNote() != null) {
            productEntity.setNote(requestDto.getNote());
        }

        if (requestDto.getIsActive() != null) {
            productEntity.setIsActive(requestDto.getIsActive());
        }

        ProductEntity updatedProduct = productRepository.save(productEntity);

        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(UUID userId, UUID productId) {
        ProductEntity productEntity = productRepository.findByIdAndUserId(productId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        productRepository.delete(productEntity);
    }

    private ProductResponseDto mapToDto(ProductEntity entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .defaultUnitId(entity.getDefaultUnitId())
                .status(entity.getStatus())
                .note(entity.getNote())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}