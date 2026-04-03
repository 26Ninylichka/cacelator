package com.example.cacelator.data.repository;

import com.example.cacelator.data.entity.CalcRunEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcRunRepository extends JpaRepository<CalcRunEntity, UUID> {

    List<CalcRunEntity> findAllByUserIdOrderByCreatedAtDesc(UUID userId);

    Optional<CalcRunEntity> findByIdAndUserId(UUID id, UUID userId);
}