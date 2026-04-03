package com.example.cacelator.controller;

import com.example.cacelator.controller.dto.calcrun.CalcRunCreateRequestDto;
import com.example.cacelator.controller.dto.calcrun.CalcRunResponseDto;
import com.example.cacelator.service.CalcRunService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc-runs")
@RequiredArgsConstructor
public class CalcRunController {

    private final CalcRunService calcRunService;

    @PostMapping
    public CalcRunResponseDto createCalcRun(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @RequestBody @Valid CalcRunCreateRequestDto requestDto
    ) {
        return calcRunService.createCalcRun(userId, requestDto);
    }

    @GetMapping
    public List<CalcRunResponseDto> getCalcRuns(
            @RequestHeader(value = "X-User-Id") UUID userId
    ) {
        return calcRunService.getCalcRuns(userId);
    }

    @GetMapping("/{calcRunId}")
    public CalcRunResponseDto getCalcRun(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID calcRunId
    ) {
        return calcRunService.getCalcRun(userId, calcRunId);
    }

    @DeleteMapping("/{calcRunId}")
    public void deleteCalcRun(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID calcRunId
    ) {
        calcRunService.deleteCalcRun(userId, calcRunId);
    }
}