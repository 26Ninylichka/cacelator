package com.example.cacelator.repository;

import com.example.cacelator.data.entity.DessertComponentEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DessertComponentRepository extends JpaRepository<DessertComponentEntity, UUID> {

    List<DessertComponentEntity> findAllByDessertIdOrderBySortOrderAsc(UUID dessertId);

    Optional<DessertComponentEntity> findByIdAndDessertId(UUID id, UUID dessertId);
}