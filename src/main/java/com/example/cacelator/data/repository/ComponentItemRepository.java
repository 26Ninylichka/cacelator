package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.ComponentItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComponentItemRepository extends JpaRepository<ComponentItemEntity, UUID> {

    List<ComponentItemEntity> findAllByComponentIdOrderBySortOrderAsc(UUID componentId);

    Optional<ComponentItemEntity> findByIdAndComponentId(UUID id, UUID componentId);
}