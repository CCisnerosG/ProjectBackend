package com.example.ProjectBackend.controllers;

import com.example.ProjectBackend.dtos.OrderAndProductsDto;
import com.example.ProjectBackend.entities.Order;
import com.example.ProjectBackend.entities.OrderProduct;
import com.example.ProjectBackend.entities.User;
import com.example.ProjectBackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequestMapping("api/v1/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/from-cart")
    public void fromCartToOrder(@RequestParam("address") String address,
                                @RequestParam("paymentMethod") String paymentMethod) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        orderService.fromCartToOrder(currentUser.getId(), address, paymentMethod);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderAndProductsDto>> allOrders(){
        List<OrderAndProductsDto> orders = orderService.allOrders();

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/new-state")
    public void updateState(@RequestParam Long orderId, @RequestParam Integer stateId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        orderService.updateState(orderId, stateId);
    }

    @GetMapping("/{orderId}/products")
    public List<OrderProduct> getAllProductsInOrder(@PathVariable Long orderId) {
        return orderService.getAllProductsInOrder(orderId);
    }

//    @GetMapping("/user/with-products")
//    public ResponseEntity<List<OrderAndProductsDTO>> getOrdersWithProductsByUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//
//        List<OrderAndProductsDTO> ordersWithProducts = orderService.getOrdersAndProductsByUser(currentUser.getId());
//        return ResponseEntity.ok(ordersWithProducts);
//    }

    @GetMapping("/user/with-products")
    public List<OrderAndProductsDto> getOrdersWithProductsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return orderService.getAllOrdersByUser(currentUser.getId());
    }

}
