package com.example.Swiggato.DTOs.RequestDTO;

import com.example.Swiggato.Enum.FoodCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequest {

    String dishName;

    FoodCategory category;

    double price;

    boolean veg;

    boolean available;
}
