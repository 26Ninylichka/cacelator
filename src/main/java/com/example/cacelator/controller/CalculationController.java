package com.example.cacelator.controller;

import com.example.cacelator.controller.calculation.CalculatedIngredientDto;
import com.example.cacelator.controller.calculation.ShoppingListItemDto;
import com.example.cacelator.service.CalculationService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculations")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping("/{calcRunId}/ingredients")
    public List<CalculatedIngredientDto> getCalculatedIngredients(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID calcRunId
    ) {
        return calculationService.getCalculatedIngredients(userId, calcRunId);
    }

    @GetMapping("/{calcRunId}/shopping-list")
    public List<ShoppingListItemDto> getShoppingList(
            @RequestHeader(value = "X-User-Id") UUID userId,
            @PathVariable UUID calcRunId
    ) {
        return calculationService.getShoppingList(userId, calcRunId);
    }
}