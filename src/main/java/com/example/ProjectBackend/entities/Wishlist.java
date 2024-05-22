package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "wishlist")
@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
//    private List<WishlistProduct> products;

    public Wishlist() {
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

//    public List<WishlistProduct> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<WishlistProduct> products) {
//        this.products = products;
//    }
}
