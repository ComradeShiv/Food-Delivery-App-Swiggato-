package com.example.Swiggato.Service.Impl;

import com.example.Swiggato.DTOs.RequestDTO.MenuRequest;
import com.example.Swiggato.DTOs.RequestDTO.RestaurantRequest;
import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.DTOs.ResponseDTO.RestaurantResponse;
import com.example.Swiggato.Exception.InvalidContactNumberException;
import com.example.Swiggato.Exception.InvalidEmailException;
import com.example.Swiggato.Model.MenuItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Service.RestaurantService;
import com.example.Swiggato.Transformer.MenuItemTransformer;
import com.example.Swiggato.Transformer.RestaurantTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        // dto -> entity
        Restaurant restaurant = RestaurantTransformer.restaurantRequestToRestaurant(restaurantRequest);
        // save it
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // entity -> response
        return RestaurantTransformer.restaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public RestaurantResponse restaurantOpenStatus(String email) {
        Optional<Restaurant> restaurant = Optional.ofNullable(restaurantRepository.findByEmail(email)); // find all details using email

        if(!restaurant.isPresent())
            throw new InvalidEmailException("Invalid Email");

        if (restaurant.get().isOpened() == true) {  // change open status
            restaurant.get().setOpened(false);
        } else {
            restaurant.get().setOpened(true);
        }

        Restaurant savedRestaurant = restaurantRepository.save(restaurant.get());  // save changes

        return RestaurantTransformer.restaurantToRestaurantResponse(savedRestaurant); // converting & returning response
    }

    @Override
    public MenuResponse addMenuItem(String email, MenuRequest menuRequest) {
        Optional<Restaurant> optionalRestaurant = Optional.ofNullable(restaurantRepository.findByEmail(email));  // find restaurant by email

        if(!optionalRestaurant.isPresent()) {
            throw new InvalidEmailException("Invalid Email !!!");
        }

        MenuItem menuItem = MenuItemTransformer.menuRequestToMenuItem(menuRequest);  // dto -> entity

        menuItem.setRestaurant(optionalRestaurant.get());  // adding restaurant to menu item
        optionalRestaurant.get().getMenuItem().add(menuItem);  // adding menu item to restaurant

        Restaurant savedRestaurant = restaurantRepository.save(optionalRestaurant.get()); // saving restaurant & menu item

        return MenuItemTransformer.menuItemToMenuResponse(savedRestaurant.getMenuItem().get(savedRestaurant.getMenuItem().size() - 1)); // returning saved menu item from DB
    }

    @Override
    public List<MenuResponse> getMenu(String mobile) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByContactNumber(mobile);  // find restaurant using contact number

        if(!optionalRestaurant.isPresent()) {
            throw new InvalidContactNumberException("Invalid Contact Number !!");  // check if contact number is Invalid or not
        }

        return optionalRestaurant.get().getMenuItem().stream().map(x -> MenuItemTransformer.menuItemToMenuResponse(x)).collect(Collectors.toList());  // returning all menu responses
    }

    @Override
    public List<RestaurantResponse> listOfRestaurantsWithMoreThanXOrders(int x) {
        List<Restaurant> list = restaurantRepository.findAll();

        List<Restaurant> filteredList = list.stream().filter(y -> y.getOrderEntityList().size() >= x).collect(Collectors.toList());

        return filteredList.stream().map(a -> RestaurantTransformer.restaurantToRestaurantResponse(a)).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantResponse> restaurantsWithXItemsAndGivenStatus(int items, boolean status) {
        List<Restaurant> list = restaurantRepository.findAll();

        List<Restaurant> filteredList = list.stream().filter(a -> a.isOpened() == status && a.getMenuItem().size() >= items).collect(Collectors.toList());

        return filteredList.stream().map(b -> RestaurantTransformer.restaurantToRestaurantResponse(b)).collect(Collectors.toList());
    }
}
