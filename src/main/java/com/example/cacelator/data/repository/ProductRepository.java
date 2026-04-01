package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findAllByUserId(UUID userId);

    Optional<ProductEntity> findByIdAndUserId(UUID id, UUID userId);

    boolean existsByNameAndUserId(String name, UUID userId);
}