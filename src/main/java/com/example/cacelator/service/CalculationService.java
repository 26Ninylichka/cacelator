package com.example.cacelator.service;

import com.example.cacelator.controller.calculation.CalculatedIngredientDto;
import com.example.cacelator.controller.calculation.ShoppingListItemDto;
import java.util.List;
import java.util.UUID;

public interface CalculationService {
    List<CalculatedIngredientDto> getCalculatedIngredients(UUID userId, UUID calcRunId);

    List<ShoppingListItemDto> getShoppingList(UUID userId, UUID calcRunId);
}
