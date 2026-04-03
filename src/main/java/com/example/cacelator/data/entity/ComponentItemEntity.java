package com.example.cacelator.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desert_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentItemEntity {

    @Id
    @Column(name = "desert_component_id")
    private UUID id;

    @Column(name = "component_id", nullable = false)
    private UUID componentId;

    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    private UUID componentItemId;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Integer sortOrder;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}