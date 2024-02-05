package com.example.Swiggato.DTOs.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    int cartTotal;

    List<FoodResponse> foodResponseList;
}
