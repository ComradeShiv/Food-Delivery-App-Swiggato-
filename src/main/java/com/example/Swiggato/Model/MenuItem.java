package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.FoodCategory;
import jakarta.persistence.*;
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
@Table(name = "menu")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "dish_name")
    String dishName;

    @Enumerated(EnumType.STRING)
    FoodCategory category;

    @Column(name = "price")
    double price;

    @Column(name = "isVeg")
    boolean veg;

    @Column(name = "isAvailable")
    boolean available;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();
}
