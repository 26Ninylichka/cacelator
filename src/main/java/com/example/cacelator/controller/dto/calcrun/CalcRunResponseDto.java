package com.example.cacelator.controller.dto.calcrun;

import com.example.cacelator.controller.dto.InputMode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalcRunResponseDto {
    private UUID id;
    private UUID userId;
    private UUID dessertId;
    private InputMode inputMode;
    private BigDecimal targetWeight;
    private BigDecimal targetDiameter;
    private Integer targetServings;
    private BigDecimal scaleFactor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}