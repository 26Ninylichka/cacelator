package com.example.cacelator.controller.dto.calcrun;

import com.example.cacelator.controller.dto.InputMode;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcRunCreateRequestDto {

    @NotNull
    private UUID dessertId;

    @NotNull
    private InputMode inputMode;

    private BigDecimal targetWeight;
    private BigDecimal targetDiameter;
    private Integer targetServings;
}