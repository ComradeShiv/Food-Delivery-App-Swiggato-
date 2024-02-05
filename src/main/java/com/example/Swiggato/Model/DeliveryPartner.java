package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "delivery_partner")
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "partner_name")
    @Size(min = 4, message = "{validation.name.size.too_short}")
    @Size(max = 50, message = "{validation.name.size.too_long}")
    String partnerName;

    @Column(name = "age")
    int age;

    @Email
    @Column(name = "email", unique = true)
    String email;

    @Size(min = 10, max = 10, message = "{validation.name.size.too_short}")
    @Column(name = "mobile_number", nullable = false)
    String mobileNo;

    @Column(name = "gender")
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<OrderEntity> order = new ArrayList<>();
}
