package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}
