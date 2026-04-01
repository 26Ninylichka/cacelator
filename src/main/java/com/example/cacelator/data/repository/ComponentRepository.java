package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComponentRepository extends JpaRepository<ComponentEntity, UUID> {

    List<ComponentEntity> findAllByUserId(UUID userId);

    Optional<ComponentEntity> findByIdAndUserId(UUID id, UUID userId);

    boolean existsByNameAndUserId(String name, UUID userId);
}