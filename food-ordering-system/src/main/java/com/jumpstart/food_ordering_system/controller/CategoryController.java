package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * CategoryController handles incoming HTTP requests for categories.
 * It delegates business logic to the CategoryService layer
 * and returns the response to the client.
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // Inject the service layer
    @Autowired
    private CategoryService categoryService;

    /**
     GET /api/category
     Returns a list of all categories from the database
     */
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}