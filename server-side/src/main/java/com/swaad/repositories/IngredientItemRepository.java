package com.swaad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

	List<IngredientsItem> findRestaurantById(Long id);

}
