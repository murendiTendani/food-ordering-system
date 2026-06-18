package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryServiceImpl contains all business logic for Category operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Helper method to convert Entity to DTO
    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    // Helper method to convert DTO to Entity
    private Category mapToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        return mapToDto(category);
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = mapToEntity(dto);
        Category saved = categoryRepository.save(category);
        return mapToDto(saved);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        category.setName(dto.getName());
        Category updated = categoryRepository.save(category);
        return mapToDto(updated);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + id));
        categoryRepository.delete(category);
    }
}