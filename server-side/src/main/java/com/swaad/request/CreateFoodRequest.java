package com.swaad.request;

import com.swaad.entities.Category;
import com.swaad.entities.IngredientsItem;
import lombok.Data;

import java.util.List;
@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Category category;
    private Long price;

    private List<String> images;
    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;



}
