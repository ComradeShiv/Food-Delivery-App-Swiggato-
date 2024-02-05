package com.example.Swiggato.Controller;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    final FoodItemService foodItemService;
    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    // give the food category which is ordered most
    @GetMapping("/category/mostOrders")
    public ResponseEntity categoryWithMostOrders() {
        try {
            FoodCategory foodCategory = foodItemService.categoryWithMostOrders();
            return new ResponseEntity(foodCategory, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
