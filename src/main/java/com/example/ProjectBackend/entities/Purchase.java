package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
}
