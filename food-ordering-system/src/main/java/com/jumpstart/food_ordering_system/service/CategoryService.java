package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import java.util.List;

/**
 * CategoryService defines the contract for the service layer
 * Any class that implements this interface must provide
 * an implementation for getAllCategories()
 */
public interface CategoryService {

    // Returns a list of all categories from the database as DTOs
    List<CategoryDto> getAllCategories();
}