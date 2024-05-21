package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.entities.User;
import jakarta.persistence.*;

public class ShoppingCartDto {
    private Long id;
    private User user;

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
}
