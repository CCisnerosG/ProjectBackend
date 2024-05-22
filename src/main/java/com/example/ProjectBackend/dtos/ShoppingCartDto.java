package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.dtos.ShoppingCartProductDto;

import java.util.List;

public class ShoppingCartDto {
    private Long id;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}