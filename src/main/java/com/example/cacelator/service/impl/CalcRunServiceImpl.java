package com.example.cacelator.service.impl;

import com.example.cacelator.controller.dto.InputMode;
import com.example.cacelator.controller.dto.calculation.CalcRunRequestDto;
import com.example.cacelator.controller.dto.calculation.CalculationResponseDto;
import com.example.cacelator.data.entity.CalcRunEntity;
import com.example.cacelator.controller.dto.calcrun.CalcRunCreateRequestDto;
import com.example.cacelator.controller.dto.calcrun.CalcRunResponseDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.data.repository.CalcRunRepository;
import com.example.cacelator.service.CalcRunService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalcRunServiceImpl implements CalcRunService {

    private final CalcRunRepository calcRunRepository;

    private final com.example.cacelator.data.repository.DessertRepository dessertRepository;


    public CalcRunResponseDto createCalcRun(UUID userId, CalcRunCreateRequestDto requestDto) {
        BigDecimal scaleFactor = calculateScaleFactor(requestDto);

        CalcRunEntity entity = CalcRunEntity.builder()
                .userId(userId)
                .dessertId(requestDto.getDessertId())
                .inputMode(requestDto.getInputMode())
                .targetWeight(requestDto.getTargetWeight())
                .targetDiameter(requestDto.getTargetDiameter())
                .targetServings(requestDto.getTargetServings())
                .scaleFactor(scaleFactor)
                .build();
        CalcRunEntity saved = calcRunRepository.save(entity);
        return mapToDto(saved);
    }

    private BigDecimal calculateScaleFactor(CalcRunCreateRequestDto requestDto) {
        com.example.cacelator.data.entity.DessertEntity dessert = dessertRepository.findById(requestDto.getDessertId())
                .orElseThrow(() -> new EntityNotFoundException("Dessert not found"));

        if (requestDto.getInputMode() == InputMode.BY_WEIGHT) {
            BigDecimal baseWeight = dessert.getBaseWeight();
            if (baseWeight == null || baseWeight.compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException("Base weight is not set for dessert");
            }
            return requestDto.getTargetWeight()
                    .divide(baseWeight, 4, RoundingMode.HALF_UP);
        }

        if (requestDto.getInputMode() == InputMode.BY_SERVINGS) {
            Integer baseServings = dessert.getServingsDefault();
            if (baseServings == null || baseServings == 0) {
                throw new IllegalArgumentException("Base servings are not set for dessert");
            }
            return new BigDecimal(requestDto.getTargetServings())
                    .divide(new BigDecimal(baseServings), 4, RoundingMode.HALF_UP);
        }

        if (requestDto.getInputMode() == InputMode.BY_DIAMETER) {
            BigDecimal baseDiameter = dessert.getBaseDiameter();
            if (baseDiameter == null || baseDiameter.compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException("Base diameter is not set for dessert");
            }
            // Formula: (D_target / D_base)^2
            BigDecimal ratio = requestDto.getTargetDiameter()
                    .divide(baseDiameter, 4, RoundingMode.HALF_UP);
            return ratio.multiply(ratio).setScale(4, RoundingMode.HALF_UP);
        }

        return BigDecimal.ONE;
    }

//    private BigDecimal calculateScaleFactor(CalcRunCreateRequestDto requestDto) {
//        return  BigDecimal.ONE;
//    }

    public List<CalcRunResponseDto> getCalcRuns(UUID userId) {
        return calcRunRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


    public CalcRunResponseDto getCalcRun(UUID userId, UUID calcRunId) {
        CalcRunEntity entity = calcRunRepository.findByIdAndUserId(calcRunId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Calc run with id " + calcRunId + " not found"
                ));

        return mapToDto(entity);
    }


    public void deleteCalcRun(UUID userId, UUID calcRunId) {
        CalcRunEntity entity = calcRunRepository.findByIdAndUserId(calcRunId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Calc run with id " + calcRunId + " not found"
                ));

        calcRunRepository.delete(entity);
    }

    private CalcRunResponseDto mapToDto(CalcRunEntity entity) {
        return CalcRunResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .dessertId(entity.getDessertId())
                .inputMode(entity.getInputMode())
                .targetWeight(entity.getTargetWeight())
                .targetDiameter(entity.getTargetDiameter())
                .targetServings(entity.getTargetServings())
                .scaleFactor(entity.getScaleFactor())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}