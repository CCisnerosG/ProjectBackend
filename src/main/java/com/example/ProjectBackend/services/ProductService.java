package com.example.ProjectBackend.services;

import com.example.ProjectBackend.entities.Product;
import com.example.ProjectBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts(){
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);

        return products;
    }
}
