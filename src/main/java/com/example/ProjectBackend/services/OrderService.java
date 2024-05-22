package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.OrderDto;
import com.example.ProjectBackend.entities.*;
import com.example.ProjectBackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public OrderDto createOrder(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }

        User user = optionalUser.get();

        List<ShoppingCartProduct> cartProducts = shoppingCartProductRepository.findByShoppingCartUserId(userId);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());

        int total = 0;
        for (ShoppingCartProduct cartProduct : cartProducts) {
            Pokemon pokemon = cartProduct.getPokemon();
            total += cartProduct.getQuantity() * pokemon.getPrice();
        }
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        OrderDto responseDTO = new OrderDto();
        responseDTO.setId(savedOrder.getId());
        responseDTO.setOrderDate(savedOrder.getOrderDate());
        responseDTO.setTotal(savedOrder.getTotal());
        return responseDTO;
    }
}
