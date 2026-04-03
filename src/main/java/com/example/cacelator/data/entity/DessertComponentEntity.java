package com.example.cacelator.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dessert_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DessertComponentEntity {

    @Id
    private UUID id;

    @Column(name = "desert_id", nullable = false)
    private UUID dessertId;

    @Column(nullable = false)
    private UUID componentId;

    private UUID dessertComponentId;

    private Integer sortOrder;

    private String note;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}