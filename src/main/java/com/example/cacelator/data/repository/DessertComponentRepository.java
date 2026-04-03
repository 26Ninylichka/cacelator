package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.DessertComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DessertComponentRepository extends JpaRepository<DessertComponentEntity, UUID> {

    List<DessertComponentEntity> findAllByDessertId(UUID dessertId);

    Optional<DessertComponentEntity> findByDessertComponentIdAndDessertId(UUID dessertComponentId, UUID dessertId);

    List<DessertComponentEntity> findAllByDessertIdOrderBySortOrderAsc(UUID dessertId);

}