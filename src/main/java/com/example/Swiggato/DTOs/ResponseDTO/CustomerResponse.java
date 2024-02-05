package com.example.Swiggato.DTOs.ResponseDTO;

import com.example.Swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    String customerName;

    String email;

    String address;

    String mobileNo;

    CartResponse cartResponse;
}
