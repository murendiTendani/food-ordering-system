package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository handles all database operations for the Category entity
 * By extending JpaRepository, we get built-in methods like findAll(),
 * findById(), save(), and delete() for free
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}