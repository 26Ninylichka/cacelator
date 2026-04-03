
package com.example.cacelator.data.entity;

import com.example.cacelator.controller.dto.InputMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "calc_run")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcRunEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(name = "dessert_id", nullable = false)
    private UUID dessertId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InputMode inputMode;

    @Column(precision = 10, scale = 2)
    private BigDecimal targetWeight;

    @Column(precision = 10, scale = 2)
    private BigDecimal targetDiameter;

    private Integer targetServings;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal scaleFactor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}