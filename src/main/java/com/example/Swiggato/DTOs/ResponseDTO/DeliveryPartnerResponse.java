package com.example.Swiggato.DTOs.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPartnerResponse {

    String partnerName;

    int age;

    String email;

    String mobileNo;
}
