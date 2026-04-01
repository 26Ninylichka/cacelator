package com.example.cacelator.service;

import com.example.cacelator.dto.calcrun.CalcRunCreateRequestDto;
import com.example.cacelator.dto.calcrun.CalcRunResponseDto;
import java.util.List;
import java.util.UUID;

public interface CalcRunService {

    CalcRunResponseDto createCalcRun(UUID userId, CalcRunCreateRequestDto requestDto);

    List<CalcRunResponseDto> getCalcRuns(UUID userId);

    CalcRunResponseDto getCalcRun(UUID userId, UUID calcRunId);

    void deleteCalcRun(UUID userId, UUID calcRunId);
}