package com.example.ProjectBackend.dtos;


import com.example.ProjectBackend.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class WishlistDto {
    private Long id;
    private User user;
    private List<WishlistProductDto> productsDto;

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

    public List<WishlistProductDto> getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(List<WishlistProductDto> productsDto) {
        this.productsDto = productsDto;
    }
}
