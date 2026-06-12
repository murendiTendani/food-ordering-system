package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryServiceImpl implements the CategoryService interface.
 * It contains the business logic for retrieving categories.
 * It fetches Category entities from the database and converts
 * them into CategoryDto objects before returning them.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    // Inject the repository to access the database
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {

        // Fetch all Category entities from the database
        List<Category> categories = categoryRepository.findAll();

        // Convert each Category entity to a CategoryDto
        return categories.stream().map(category -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}