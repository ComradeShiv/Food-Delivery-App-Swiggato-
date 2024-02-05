package com.example.Swiggato.DTOs.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {

    String restaurantId;

    String restaurantName;

    String location;

    boolean opened;

    String contactNumber;

    List<MenuResponse> menu;

}
