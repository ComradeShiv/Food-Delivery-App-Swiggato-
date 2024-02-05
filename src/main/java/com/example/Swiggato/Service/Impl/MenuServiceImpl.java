package com.example.Swiggato.Service.Impl;

import com.example.Swiggato.DTOs.ResponseDTO.MenuResponse;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Exception.InvalidEmailException;
import com.example.Swiggato.Model.MenuItem;
import com.example.Swiggato.Model.Restaurant;
import com.example.Swiggato.Repository.MenuRepository;
import com.example.Swiggato.Repository.RestaurantRepository;
import com.example.Swiggato.Service.MenuService;
import com.example.Swiggato.Transformer.MenuItemTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    final MenuRepository menuRepository;
    final RestaurantRepository restaurantRepository;
    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<MenuResponse> getAllFoodWithCategory(FoodCategory foodCategory) {
        List<MenuItem> menuItemList = menuRepository.getAllFoodWithCategory(foodCategory);

        return menuItemList.stream().map(x -> MenuItemTransformer.menuItemToMenuResponse(x)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> itemsWithCategoryAndOverXRupees(int price, FoodCategory category) {
//        List<MenuItem> menuItemList = menuRepository.mainCourseWithOverXRupees(price, category);
        List<MenuItem> menuItemList = menuRepository.findByCategory(category);

        return menuItemList.stream().filter(a -> a.getPrice() >= price).map(x -> MenuItemTransformer.menuItemToMenuResponse(x)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getAllVegFood(String email) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if(restaurant == null) {
            throw new InvalidEmailException("Invalid Email !!!");
        }

        List<MenuItem> menuItemList = restaurant.getMenuItem().stream().filter(x -> x.isVeg() == true).collect(Collectors.toList());

        return menuItemList.stream().map(y -> MenuItemTransformer.menuItemToMenuResponse(y)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getAllNonVegFood(String email) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if(restaurant == null) {
            throw new InvalidEmailException("Invalid Email !!!");
        }

        List<MenuItem> menuItemList = restaurant.getMenuItem().stream().filter(x -> x.isVeg() == false).collect(Collectors.toList());

        return menuItemList.stream().map(y -> MenuItemTransformer.menuItemToMenuResponse(y)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getFiveCheapestFood(String email) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if(restaurant == null) {
            throw new InvalidEmailException("Invalid Email !!!");
        }

        List<MenuItem> menuItemList = restaurant.getMenuItem().stream().sorted( (a,b) -> (int) (a.getPrice() - b.getPrice()) ).limit(5).collect(Collectors.toList());

        return menuItemList.stream().map(y -> MenuItemTransformer.menuItemToMenuResponse(y)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getFiveCostliestFood(String email) {
        Restaurant restaurant = restaurantRepository.findByEmail(email);

        if(restaurant == null) {
            throw new InvalidEmailException("Invalid Email !!!");
        }

        List<MenuItem> menuItemList = restaurant.getMenuItem().stream().sorted( (a,b) -> (int) (b.getPrice() - a.getPrice()) ).limit(5).collect(Collectors.toList());

        return menuItemList.stream().map(y -> MenuItemTransformer.menuItemToMenuResponse(y)).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getFiveCostliestCategoryFood(FoodCategory category) {
        List<MenuItem> menuItemList = menuRepository.findByCategory(category);

        List<MenuItem> topFiveItems = menuItemList.stream().filter(x -> x.getCategory().equals(category)).limit(5).collect(Collectors.toList());

        return topFiveItems.stream().map(x -> MenuItemTransformer.menuItemToMenuResponse(x)).collect(Collectors.toList());
    }
}
