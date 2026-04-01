package com.example.cacelator.repository;

import com.example.cacelator.data.entity.ComponentItemEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentItemRepository extends JpaRepository<ComponentItemEntity, UUID> {

    List<ComponentItemEntity> findAllByComponentIdOrderBySortOrderAsc(UUID componentId);

    Optional<ComponentItemEntity> findByIdAndComponentId(UUID id, UUID componentId);
}