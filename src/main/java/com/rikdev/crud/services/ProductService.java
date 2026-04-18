package com.rikdev.crud.services;

import com.rikdev.crud.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getProducts();                  //obtener todos los productos
    Optional<Product> getProductById(Long id);
    /*void deleteProduct(Long id);*/
}
