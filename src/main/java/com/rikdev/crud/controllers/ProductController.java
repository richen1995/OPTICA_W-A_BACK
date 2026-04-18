package com.rikdev.crud.controllers;

import com.rikdev.crud.entities.Product;
import com.rikdev.crud.services.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")

public class ProductController {

    // Crear una instancia de Logger
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping            //GUARDAR LIBRO
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try {
            Product saveProduct = productServiceImpl.saveProduct(product);
            logger.info("Se se ha guardado el libro");
            System.out.println("Hola, este es un mensaje en la consola.");
            return new ResponseEntity<>(saveProduct, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping            //ACTUALIZAR PRODUCTO
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        try {
            Product saveProduct = productServiceImpl.updateProduct(product);
            return new ResponseEntity<>(saveProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping            //OBTENER TODOS LOS LIBROS
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productServiceImpl.getProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")            //OBTENER UN LIBRO POR ID
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productServiceImpl.getProductById(id);
        return product.map(value ->new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
