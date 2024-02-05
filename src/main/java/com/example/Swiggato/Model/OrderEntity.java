package com.example.Swiggato.Model;

import com.example.Swiggato.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "order_id", unique = true)
    String orderId; // UUID

    @Column(name = "order_total")
    double orderTotal;

    @CreationTimestamp
    Date orderTime;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<FoodItem> foodItem = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    Transaction transaction;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;
}
