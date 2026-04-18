package com.rikdev.crud.services;

import com.rikdev.crud.entities.Product;
import com.rikdev.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product){return productRepository.save(product);}

    @Override
    public List<Product> getProducts(){return productRepository.findAll();}

    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    /*@Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }*/

}
