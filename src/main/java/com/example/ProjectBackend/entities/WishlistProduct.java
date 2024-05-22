package com.example.ProjectBackend.entities;

import jakarta.persistence.*;

@Table(name = "wishlist_product")
@Entity
public class WishlistProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    public WishlistProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
