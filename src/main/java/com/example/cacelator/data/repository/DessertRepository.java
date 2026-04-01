package com.example.cacelator.repository;

import com.example.cacelator.data.entity.DessertEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertRepository extends JpaRepository<DessertEntity, UUID> {

    boolean existsByNameAndUserId(String name, UUID userId);

    List<DessertEntity> findAllByUserId(UUID userId);

    Optional<DessertEntity> findByIdAndUserId(UUID id, UUID userId);
}