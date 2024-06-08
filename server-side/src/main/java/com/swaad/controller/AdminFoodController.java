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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);

		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		foodService.deleteFood(id);

		MessageResponse res = new MessageResponse();
		res.setMessage("food deleted successfully");
		return new ResponseEntity<>(res, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailibiltyStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.updateAvailibilityStatus(id);

		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}
}
