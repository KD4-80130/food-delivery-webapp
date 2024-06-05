package com.swaad.service;

import com.swaad.entities.Category;
import com.swaad.entities.Food;
import com.swaad.entities.Restaurant;
import com.swaad.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {


    public Food createFood(CreateFoodRequest req , Category category , Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId , boolean isVegetarian ,boolean isNonveg, boolean isSeasonal, String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long id) throws Exception;

    public Food updateAvailibilityStatus(Long foodId) throws Exception;




}
