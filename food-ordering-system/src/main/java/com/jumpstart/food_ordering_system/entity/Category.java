package com.jumpstart.food_ordering_system.entity;

import jakarta.persistence.*;

/**
 * Category entity represents the 'category' table in the database.
 * Each instance of this class corresponds to a row in the category table.
 */
@Entity
@Table(name = "category")
public class Category {

    // Primary key that is auto-incremented by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the category
    private String name;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}