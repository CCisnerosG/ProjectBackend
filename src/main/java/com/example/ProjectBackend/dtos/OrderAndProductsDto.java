package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.entities.State;
import com.example.ProjectBackend.entities.User;

import java.util.Date;
import java.util.List;

public class OrderAndProductsDto {
    private Long id;
    private User user;
    private Date orderDate;
    private String address;
    private Integer total;
    private String paymentMethod;
    private List<OrderProductDto> products;
    private State state;

    public OrderAndProductsDto(Long id, User user, Date orderDate, String address, Integer total, String paymentMethod, List<OrderProductDto> products, State state) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.address = address;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.products = products;
        this.state = state;
    }

    public OrderAndProductsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDto> products) {
        this.products = products;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
