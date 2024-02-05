package com.example.Swiggato.Controller;

import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // get all foods of a particular category
    @GetMapping("/getMenu/category/{category}")
    public ResponseEntity getAllFoodWithCategory(@PathVariable("category") FoodCategory foodCategory) {
        try {
            List<MenuResponse> menuResponseList = menuService.getAllFoodWithCategory(foodCategory);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all MAIN_COURSE items with price above x rupees from a particular restaurant.
    @GetMapping("/items/category/{cate}/price/{p}")
    public ResponseEntity itemsWithCategoryAndOverXRupees(@PathVariable("p") int price, @PathVariable("cate") FoodCategory category) {
        try {
            List<MenuResponse> menuResponseList = menuService.itemsWithCategoryAndOverXRupees(price, category);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // get all veg foods of a restaurant
    @GetMapping("/getAllVeg/{email}")
    public ResponseEntity getAllVegFood(@PathVariable("email") String email) {
        try {
            List<MenuResponse> menuResponseList = menuService.getAllVegFood(email);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all non veg foods of a restaurant
    @GetMapping("/getAllNonVeg/{email}")
    public ResponseEntity getAllNonVegFood(@PathVariable("email") String email) {
        try {
            List<MenuResponse> menuResponseList = menuService.getAllNonVegFood(email);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get cheapest 5 food items of a particular restaurant
    @GetMapping("/fiveCheapestFood/{email}")
    public ResponseEntity getFiveCheapestFood(@PathVariable("email") String email) {
        try {
            List<MenuResponse> menuResponseList = menuService.getFiveCheapestFood(email);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get costliest 5 food items of a particular restaurant
    @GetMapping("/fiveCostliestFood/{email}")
    public ResponseEntity getFiveCostliestFood(@PathVariable("email") String email) {
        try {
            List<MenuResponse> menuResponseList = menuService.getFiveCostliestFood(email);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get costliest 5 food items of a particular category -> name of dish and rest which restaurant serves that dish
    @GetMapping("/fiveCostliestFood/category")
    public ResponseEntity getFiveCostliestCategoryFood(@RequestParam("category") FoodCategory category) {
        try {
            List<MenuResponse> menuResponseList = menuService.getFiveCostliestCategoryFood(category);
            return new ResponseEntity<>(menuResponseList.stream().map(x -> "Dish name: " + x.getDishName() + " and Restaurant: " + x.getRestaurantName()), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
