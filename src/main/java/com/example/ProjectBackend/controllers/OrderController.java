package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.OrderDto;
import com.example.ProjectBackend.dtos.OrderRequestDto;
import com.example.ProjectBackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestParam @RequestBody OrderRequestDto orderRequestDto) {
        OrderDto createdOrder = orderService.createOrder(orderRequestDto.getUserId());
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}
