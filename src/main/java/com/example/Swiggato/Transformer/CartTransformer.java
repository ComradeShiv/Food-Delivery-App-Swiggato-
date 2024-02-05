package com.example.Swiggato.Transformer;


import com.example.Swiggato.DTOs.ResponseDTO.CartResponse;
import com.example.Swiggato.DTOs.ResponseDTO.FoodResponse;
import com.example.Swiggato.Model.Cart;
import com.example.Swiggato.Model.Customer;
import com.example.Swiggato.Model.FoodItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static Cart prepareCart(Customer customer) {
        return Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .foodItems(new ArrayList<>()) // initialised again coz builder class initialise with null if not given by us
                .build();
    }

    public static CartResponse cartToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodResponseList(cart.getFoodItems().stream().map(x -> FoodItemTransFormer.foodItemToFoodResponse(x)).collect(Collectors.toList()))
                .build();
    }
}
