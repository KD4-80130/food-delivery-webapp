package com.swaad.controller;


import com.swaad.entities.Food;
import com.swaad.entities.Restaurant;
import com.swaad.entities.User;
import com.swaad.request.CreateFoodRequest;
import com.swaad.response.MessageResponse;
import com.swaad.service.FoodService;
import com.swaad.service.RestaurantService;
import com.swaad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> createFood(@RequestParam String name, @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Food> foods =foodService.searchFood(name);


        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }



    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam boolean nonveg,
                                                        @RequestParam(required = false) String food_category,
                                                        @PathVariable Long restaurantId,
                                                        @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<Food> foods =foodService.getRestaurantFood(restaurantId,vegetarian,seasonal,nonveg,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}
