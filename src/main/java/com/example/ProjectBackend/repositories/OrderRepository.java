package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Order findByUserIdAndId(Long userId, Long id);
    Optional<Order> findById(Long id);

    List<Order> findByUser_Id(Long userId);
}
