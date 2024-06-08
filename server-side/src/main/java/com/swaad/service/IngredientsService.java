package com.swaad.service;

import java.util.List;

import com.swaad.entities.IngredientCategory;
import com.swaad.entities.IngredientsItem;

public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception;

	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

	public IngredientsItem updateStock(Long id) throws Exception;
}
