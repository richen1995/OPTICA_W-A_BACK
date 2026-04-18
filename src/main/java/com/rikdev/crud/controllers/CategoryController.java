package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Category;
import com.rikdev.crud.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        try {
            Category saveCategory = categoryServiceImpl.saveCategory(category);
            return new ResponseEntity<>(saveCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR LIBRO
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        try {
            Category saveCategory = categoryServiceImpl.updateCategory(category);
            return new ResponseEntity<>(saveCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Category>> getAllCategorys(){
        return new ResponseEntity<>(categoryServiceImpl.getCategories(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN LIBRO POR ID
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Optional<Category> category = categoryServiceImpl.getCategoryById(id);
        return category.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*@DeleteMapping("/{id}")            //ELIMINAR UN LIBRO POR ID
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        Optional <Category> category = categoryServiceImpl.getCategoryById(id);
        if(category.isPresent()){
            //categoryServiceImpl.deleteCategory(category.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
    

}
