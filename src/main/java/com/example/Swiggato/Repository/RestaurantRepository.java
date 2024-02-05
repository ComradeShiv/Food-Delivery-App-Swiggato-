package com.example.Swiggato.Repository;

import com.example.Swiggato.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {


    Restaurant findByEmail(String email);

    Optional<Restaurant> findByContactNumber(String mobile);

//    @Query(value = "select * from (select * from restaurant JOIN order_entity ON restaurant.restaurant_id = order_entity.restaurant_id ORDER BY order_entity.restaurant_id) AS orders where orders >= :x", nativeQuery = true)
//    Optional<Restaurant> restaurantsWithMoreThanXOrders(int x);
}
