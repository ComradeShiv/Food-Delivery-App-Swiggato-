package com.example.Swiggato.Service;

import com.example.Swiggato.DTOs.RequestDTO.MenuRequest;
import com.example.Swiggato.DTOs.RequestDTO.RestaurantRequest;
import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.DTOs.ResponseDTO.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    RestaurantResponse restaurantOpenStatus(String email);

    MenuResponse addMenuItem(String email, MenuRequest menuRequest);

    List<MenuResponse> getMenu(String mobile);

    List<RestaurantResponse> listOfRestaurantsWithMoreThanXOrders(int x);

    List<RestaurantResponse> restaurantsWithXItemsAndGivenStatus(int items, boolean status);
}
