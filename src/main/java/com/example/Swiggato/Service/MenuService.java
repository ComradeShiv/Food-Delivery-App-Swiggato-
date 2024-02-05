package com.example.Swiggato.Service;

import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.Enum.FoodCategory;

import java.util.List;

public interface MenuService {


    List<MenuResponse> getAllFoodWithCategory(FoodCategory foodCategory);

    List<MenuResponse> itemsWithCategoryAndOverXRupees(int price, FoodCategory category);

    List<MenuResponse> getAllVegFood(String email);

    List<MenuResponse> getAllNonVegFood(String email);

    List<MenuResponse> getFiveCheapestFood(String email);

    List<MenuResponse> getFiveCostliestFood(String email);

    List<MenuResponse> getFiveCostliestCategoryFood(FoodCategory category);
}
