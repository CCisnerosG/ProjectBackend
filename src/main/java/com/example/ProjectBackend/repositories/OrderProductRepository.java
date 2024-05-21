package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
