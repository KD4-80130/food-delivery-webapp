package com.swaad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swaad.entities.Order;
import com.swaad.entities.User;
import com.swaad.service.OrderService;
import com.swaad.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id, @RequestParam String orderStatus,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);
		List<Order> orders = orderService.getUsersOrder(user.getId());

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @PathVariable String orderStatus,
			@RequestHeader("Authorization") String jwt) throws Exception {

//		User user = userService.findUserByJwtToken(jwt);
		Order order = orderService.updateOrder(id, orderStatus);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
