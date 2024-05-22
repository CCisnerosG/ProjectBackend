package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.entities.Wishlist;

public class WishlistProductDto {
    private Long id;
    private Wishlist wishlist;
    private Pokemon pokemon;

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
