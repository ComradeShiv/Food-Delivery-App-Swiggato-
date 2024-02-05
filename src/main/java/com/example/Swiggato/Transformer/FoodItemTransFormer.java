package com.example.Swiggato.Transformer;

import com.example.Swiggato.DTOs.ResponseDTO.FoodResponse;
import com.example.Swiggato.Model.FoodItem;

public class FoodItemTransFormer {

    public static FoodResponse foodItemToFoodResponse(FoodItem foodItem) {
        return FoodResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .category(foodItem.getMenuItem().getCategory())
                .price(foodItem.getTotalCost())
                .isVeg(foodItem.getMenuItem().isVeg())
                .build();
    }
}
