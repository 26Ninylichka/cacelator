package com.example.cacelator.service.impl;

import com.example.cacelator.data.entity.*;
import com.example.cacelator.data.repository.ComponentRepository;
import com.example.cacelator.data.repository.ProductRepository;
import com.example.cacelator.controller.calculation.CalculatedIngredientDto;
import com.example.cacelator.controller.calculation.ShoppingListItemDto;
import com.example.cacelator.exception.EntityNotFoundException;
import com.example.cacelator.data.repository.CalcRunRepository;
import com.example.cacelator.data.repository.ComponentItemRepository;

import java.util.UUID;


import com.example.cacelator.data.repository.DessertComponentRepository;

import com.example.cacelator.service.CalculationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final CalcRunRepository calcRunRepository;
    private final DessertComponentRepository dessertComponentRepository;
    private final ComponentItemRepository componentItemRepository;
    private final ComponentRepository componentRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CalculatedIngredientDto> getCalculatedIngredients(UUID userId, UUID calcRunId) {
        CalcRunEntity calcRun = calcRunRepository.findByIdAndUserId(calcRunId, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Calc run with id " + calcRunId + " not found"
                ));

        List<DessertComponentEntity> dessertComponents =
                dessertComponentRepository.findAllByDessertIdOrderBySortOrderAsc(
                        calcRun.getDessertId()
                );

        List<CalculatedIngredientDto> result = new ArrayList<>();

        for (DessertComponentEntity dessertComponent : dessertComponents) {
            ComponentEntity component = componentRepository.findById(dessertComponent.getComponentId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Component with id " + dessertComponent.getComponentId() + " not found"
                    ));

            List<ComponentItemEntity> items =
                    componentItemRepository.findAllByComponentIdOrderBySortOrderAsc(
                            dessertComponent.getComponentId()
                    );

            for (ComponentItemEntity item : items) {
                ProductEntity product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Product with id " + item.getProductId() + " not found"
                        ));

                BigDecimal scaledQuantity = item.getQuantity()
                        .multiply(calcRun.getScaleFactor())
                        .setScale(2, RoundingMode.HALF_UP);

                result.add(CalculatedIngredientDto.builder()
                        .componentId(component.getId())
                        .componentName(component.getName())
                        .productId(product.getId())
                        .productName(product.getName())
                        .unit(item.getUnit())
                        .sortOrder(item.getSortOrder())
                        .originalQuantity(item.getQuantity())
                        .scaledQuantity(scaledQuantity)
                        .build());
            }
        }

        return result;
    }

    @Override
    public List<ShoppingListItemDto> getShoppingList(UUID userId, UUID calcRunId) {
        List<CalculatedIngredientDto> ingredients = getCalculatedIngredients(userId, calcRunId);

        Map<String, ShoppingListItemDto> groupedItems = new LinkedHashMap<>();

        for (CalculatedIngredientDto ingredient : ingredients) {
            String key = ingredient.getProductId() + "_" + ingredient.getUnit();

            if (!groupedItems.containsKey(key)) {
                groupedItems.put(key, ShoppingListItemDto.builder()
                        .productId(ingredient.getProductId())
                        .productName(ingredient.getProductName())
                        .unit(ingredient.getUnit())
                        .totalQuantity(ingredient.getScaledQuantity())
                        .build());
            } else {
                ShoppingListItemDto existingItem = groupedItems.get(key);
                existingItem.setTotalQuantity(
                        existingItem.getTotalQuantity().add(ingredient.getScaledQuantity())
                );
            }
        }

        return new ArrayList<>(groupedItems.values());
    }
}