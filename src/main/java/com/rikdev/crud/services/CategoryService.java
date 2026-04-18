package com.rikdev.crud.services;

import com.rikdev.crud.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    List<Category> getCategories();
    Optional<Category> getCategoryById(Long Id);

}
