package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.ProductPackagePriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPackagePriceRepository extends JpaRepository<ProductPackagePriceEntity, UUID> {

    List<ProductPackagePriceEntity> findAllByProduct_IdOrderByCreatedAtDesc(UUID productId);

    Optional<ProductPackagePriceEntity> findTopByProduct_IdOrderByCreatedAtDesc(UUID productId);
}