package com.example.cacelator.controller;

import com.example.cacelator.dto.calculation.CalculatedIngredientDto;
import com.example.cacelator.dto.calculation.ShoppingListItemDto;
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
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID calcRunId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return calculationService.getCalculatedIngredients(userId, calcRunId);
    }

    @GetMapping("/{calcRunId}/shopping-list")
    public List<ShoppingListItemDto> getShoppingList(
            @RequestHeader(value = "Authorization", required = false) String header,
            @PathVariable UUID calcRunId
    ) {
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        return calculationService.getShoppingList(userId, calcRunId);
    }
}