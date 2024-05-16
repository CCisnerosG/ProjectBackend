package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
}
