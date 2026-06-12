package com.jumpstart.food_ordering_system.exception;

/**
 * CategoryNotFoundException is a custom exception thrown when
 * a requested category is not found in the database
 * Custom exceptions make error handling more specific and meaningful
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
