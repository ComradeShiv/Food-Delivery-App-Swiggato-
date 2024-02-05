package com.example.Swiggato.Transformer;

import com.example.Swiggato.DTOs.RequestDTO.RestaurantRequest;
import com.example.Swiggato.DTOs.ResponseDTO.RestaurantResponse;
import com.example.Swiggato.Model.Restaurant;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restaurant restaurantRequestToRestaurant(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .restaurantName(restaurantRequest.getRestaurantName())
                .restaurantId(String.valueOf(UUID.randomUUID()))
                .email(restaurantRequest.getEmail())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .opened(true)
                .contactNumber(restaurantRequest.getContactNumber())
                .menuItem(new ArrayList<>())
                .orderEntityList(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse restaurantToRestaurantResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .location(restaurant.getLocation())
                .opened(restaurant.isOpened())
                .contactNumber(restaurant.getContactNumber())
                .menu(restaurant.getMenuItem().stream().map(x -> MenuItemTransformer.menuItemToMenuResponse(x)).collect(Collectors.toList()))
                .build();
    }
}
