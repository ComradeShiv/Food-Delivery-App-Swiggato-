package com.example.Swiggato.Controller;

import com.example.Swiggato.DTOs.RequestDTO.MenuRequest;
import com.example.Swiggato.DTOs.RequestDTO.RestaurantRequest;
import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.DTOs.ResponseDTO.RestaurantResponse;
import com.example.Swiggato.Service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // add restaurant
    @PostMapping("/addRestaurant")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        try {
            RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
            return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // change opened status
    @PutMapping("/change/status/{email}")
    public ResponseEntity restaurantOpenStatus(@PathVariable("email") String email) {
        try {
            RestaurantResponse restaurantResponse = restaurantService.restaurantOpenStatus(email);
            return new ResponseEntity(restaurantResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // add menu item to restaurant
    @PostMapping("/newMenuItem")
    public ResponseEntity addMenuItem(@RequestParam("email") String email, @RequestBody MenuRequest menuRequest) {
        try {
            MenuResponse menuResponse = restaurantService.addMenuItem(email, menuRequest);
            return new ResponseEntity<>(menuResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get menu of a restaurant
    @GetMapping("/getMenu/{mobile}")
    public ResponseEntity getMenu(@PathVariable("mobile") String mobile) {
        try {
            List<MenuResponse> menuResponseList = restaurantService.getMenu(mobile);
            return new ResponseEntity<>(menuResponseList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give all the restaurants who have served more than 'x' number of orders
    @GetMapping("/restaurants/XOrders/{x}")
    public ResponseEntity listOfRestaurantsWithMoreThanXOrders(@PathVariable("x") int x) {
        try {
            List<RestaurantResponse> list = restaurantService.listOfRestaurantsWithMoreThanXOrders(x);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give the restaurants which have maximum number of items greater than x in their menu and which are opened also
    @GetMapping("/restaurants/condition")
    public ResponseEntity restaurantsWithXItemsAndGivenStatus(@RequestParam("items") int items, @RequestParam("status") boolean status) {
        try {
            List<RestaurantResponse> restaurantResponse = restaurantService.restaurantsWithXItemsAndGivenStatus(items, status);
            return new ResponseEntity<>(restaurantResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
