package com.example.Swiggato.Service.Impl;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Model.OrderEntity;
import com.example.Swiggato.Repository.FoodItemRepository;
import com.example.Swiggato.Repository.OrderEntityRepository;
import com.example.Swiggato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    final FoodItemRepository foodItemRepository;
    final OrderEntityRepository orderEntityRepository;
    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository, OrderEntityRepository orderEntityRepository) {
        this.foodItemRepository = foodItemRepository;
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public FoodCategory categoryWithMostOrders() {
        List<OrderEntity> orderEntityList = orderEntityRepository.findAll();
        HashMap<FoodCategory, Integer> count = new HashMap<>();

        orderEntityList.stream().map(a -> a.getFoodItem().stream().map(b -> count.put(b.getMenuItem().getCategory(), count.getOrDefault(b.getMenuItem().getCategory(), 0) + 1)));

        FoodCategory maxOrdered = null;
        int max = 0;
        for(FoodCategory i: count.keySet()) {
            max = Math.max(max, count.get(i));
            if(maxOrdered == null || count.get(maxOrdered) < count.get(i)) {
                maxOrdered = i;
            }
        }
        return maxOrdered;
    }
}
