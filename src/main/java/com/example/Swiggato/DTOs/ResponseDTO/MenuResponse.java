package com.example.Swiggato.DTOs.ResponseDTO;

import com.example.Swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {

    String dishName;

    FoodCategory category;

    double price;

    boolean veg;

    boolean available;

    String restaurantName;
}
