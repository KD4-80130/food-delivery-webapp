package com.swaad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

	List<IngredientCategory> findByRestaurantId(Long id);
}
