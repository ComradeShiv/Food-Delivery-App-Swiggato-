package com.example.Swiggato.Repository;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Integer> {
    @Query(value = "Select * from menu where category = :foodCategory", nativeQuery = true)
    List<MenuItem> getAllFoodWithCategory(FoodCategory foodCategory);

//    @Query(value = "select * from menu where category = :category and price >= :price", nativeQuery = true)
//    List<MenuItem> mainCourseWithOverXRupees(int price, FoodCategory category);

    List<MenuItem> findByCategory(FoodCategory category);
}
