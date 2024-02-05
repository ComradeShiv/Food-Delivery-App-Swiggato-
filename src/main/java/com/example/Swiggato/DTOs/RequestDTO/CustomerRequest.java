package com.example.Swiggato.DTOs.RequestDTO;

import com.example.Swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    String customerName;

    Gender gender;

    String email;

    String address;

    String mobileNo;
}
