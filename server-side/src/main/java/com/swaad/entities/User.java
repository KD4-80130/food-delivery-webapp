package com.swaad.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swaad.dto.RestaurantDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private USER_ROLE role;
	
	//one user can order multiple times
//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//	private List<Order> orders = new ArrayList<>();
	
	@ElementCollection
	private List<RestaurantDto> favourites = new ArrayList<>();
	
	//when you delete the user all addresses associated with user must be deleted
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses  = new ArrayList<>();
	
}