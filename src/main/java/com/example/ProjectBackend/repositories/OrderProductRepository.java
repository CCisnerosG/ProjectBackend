package com.example.ProjectBackend.repositories;

import com.example.ProjectBackend.entities.Order;
import com.example.ProjectBackend.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    OrderProduct findByOrderIdAndPokemonId(Long orderId, Integer pokemonId);
    List<OrderProduct> findByOrderId(Long orderId);
//    List<OrderProduct> findAllOrdersByUserId(Long userId);
    List<OrderProduct> findByOrder_User_Id(Long userId);

    List<OrderProduct> findByOrder_Id(Long id);
    List<OrderProduct> findByOrder(Order order);
}
