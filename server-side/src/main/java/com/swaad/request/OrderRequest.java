package com.swaad.request;

import com.swaad.entities.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long restaurantId;
	
	private Address deliveryAddress;
}
