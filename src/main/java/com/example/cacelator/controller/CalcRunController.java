package com.example.cacelator.controller;

import com.example.cacelator.dto.calcrun.CalcRunCreateRequestDto;
import com.example.cacelator.dto.calcrun.CalcRunResponseDto;
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
            @RequestHeader(value = "Authorization", required = false) String header,
            @RequestBody @Valid CalcRunCreateRequestDto requestDto
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return calcRunService.createCalcRun(userId, requestDto);
    }

    @GetMapping
    public List<CalcRunResponseDto> getCalcRuns(
            @RequestHeader(value = "Authorization", required = false) String header
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return calcRunService.getCalcRuns(userId);
    }

    @GetMapping("/{calcRunId}")
    public CalcRunResponseDto getCalcRun(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID calcRunId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return calcRunService.getCalcRun(userId, calcRunId);
    }

    @DeleteMapping("/{calcRunId}")
    public void deleteCalcRun(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID calcRunId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        calcRunService.deleteCalcRun(userId, calcRunId);
    }
}