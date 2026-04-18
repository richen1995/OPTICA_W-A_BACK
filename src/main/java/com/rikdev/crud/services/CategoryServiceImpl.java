package com.rikdev.crud.services;

import com.rikdev.crud.entities.Category;
import com.rikdev.crud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    /*@Override
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }*/
}
