package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // Filter by categoryId and/or search term (case-insensitive)
    @Query("SELECT m FROM Menu m WHERE " +
            "(:categoryId IS NULL OR m.category.id = :categoryId) AND " +
            "(:search IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Menu> findByFilters(
            @Param("categoryId") Long categoryId,
            @Param("search") String search,
            Pageable pageable);
}