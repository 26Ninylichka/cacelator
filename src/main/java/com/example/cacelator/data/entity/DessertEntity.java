package com.example.cacelator.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DessertEntity {

    @Id
    @Column(name = "desert_id")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "default_diameter_cm")
    private BigDecimal baseDiameter;

    @Column(name = "default_weight_g")
    private BigDecimal baseWeight;

    @Column(name = "servings_default", nullable = false)
    private Integer servingsDefault;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}