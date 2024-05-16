package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "cart_details")
public class ShoppingCartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
}
