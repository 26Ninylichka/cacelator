package com.example.cacelator.service.impl;

import com.example.cacelator.controller.dto.InputMode;
import com.example.cacelator.data.entity.CalcRunEntity;
import com.example.cacelator.dto.calcrun.CalcRunCreateRequestDto;
import com.example.cacelator.dto.calcrun.CalcRunResponseDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.repository.CalcRunRepository;
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

    private final com.example.cacelator.repository.DessertRepository dessertRepository;




    @Override
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

        // пока только BY_WEIGHT
        if (requestDto.getInputMode() == InputMode.BY_WEIGHT) {

            BigDecimal baseWeight = getBaseWeight(requestDto.getDessertId());

            if (baseWeight == null || baseWeight.compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException("Base weight is not set for dessert");
            }

            return requestDto.getTargetWeight()
                    .divide(baseWeight, 4, RoundingMode.HALF_UP);
        }

        return BigDecimal.ONE;
    }

    private BigDecimal getBaseWeight(UUID dessertId) {
        return dessertRepository.findById(dessertId)
                .orElseThrow(() -> new EntityNotFoundException("Dessert not found"))
                .getBaseWeight();
    }
//    private BigDecimal calculateScaleFactor(CalcRunCreateRequestDto requestDto) {
//        return  BigDecimal.ONE;
//    }

    @Override
    public List<CalcRunResponseDto> getCalcRuns(UUID userId) {
        return calcRunRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public CalcRunResponseDto getCalcRun(UUID userId, UUID calcRunId) {
        CalcRunEntity entity = calcRunRepository.findByIdAndUserId(calcRunId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Calc run with id " + calcRunId + " not found"
                ));

        return mapToDto(entity);
    }

    @Override
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