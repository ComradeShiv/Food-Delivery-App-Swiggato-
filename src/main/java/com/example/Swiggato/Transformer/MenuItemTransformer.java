package com.example.Swiggato.Transformer;

import com.example.Swiggato.DTOs.RequestDTO.MenuRequest;
import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.Model.MenuItem;

import java.util.ArrayList;

public class MenuItemTransformer {

    public static MenuItem menuRequestToMenuItem(MenuRequest menuRequest) {
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .category(menuRequest.getCategory())
                .price(menuRequest.getPrice())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .foodItemList(new ArrayList<>())
                .build();
    }

    public static MenuResponse menuItemToMenuResponse(MenuItem menuItem) {
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .category(menuItem.getCategory())
                .price(menuItem.getPrice())
                .veg(menuItem.isVeg())
                .available(menuItem.isAvailable())
                .restaurantName(menuItem.getRestaurant().getRestaurantName())
                .build();
    }
}
