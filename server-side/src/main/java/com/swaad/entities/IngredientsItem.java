package com.swaad.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem extends BaseEntity {
	
	private String name;
	
	@ManyToOne
	private IngrediantCategory category;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean inStock = true;
}
