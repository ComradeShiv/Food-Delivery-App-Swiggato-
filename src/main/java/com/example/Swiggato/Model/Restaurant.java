package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.RestaurantCategory;
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
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String restaurantId; // UUID

    @Column(name = "restaurant_name", nullable = false)
    @Size(min = 4, message = "{validation.name.size.too_short}")
    @Size(max = 100, message = "{validation.name.size.too_long}")
    String restaurantName;

    @Email
    @Column(name = "email", unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

    @Column(nullable = false)
    String location;

    boolean opened;

    @Column(name = "contact_number", nullable = false, unique = true)
    @Size(min = 10, max = 10)
    String contactNumber;

    @OneToMany(mappedBy = "restaurant", cascade =  CascadeType.ALL)
    List<MenuItem> menuItem = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList = new ArrayList<>();
}
