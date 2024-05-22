package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
