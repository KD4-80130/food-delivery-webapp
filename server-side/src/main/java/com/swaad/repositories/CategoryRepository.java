package com.swaad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public List<Category> findByRestaurantId(Long id);
}
