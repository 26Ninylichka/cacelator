package com.example.cacelator.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;

import lombok.*;

@Entity
@Table(name = "component")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    @Column(nullable = false)
    private UUID userId;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}