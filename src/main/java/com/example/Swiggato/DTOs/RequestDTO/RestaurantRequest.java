package com.example.Swiggato.DTOs.RequestDTO;

import com.example.Swiggato.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {

    String restaurantName;

    String email;

    String location;

    RestaurantCategory restaurantCategory;

//    boolean opened;

    String contactNumber;
}
