package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.entities.Pokemon;

public class OrderProductDto {
    private Long id;
    private Integer quantity;
    private Integer price;
    private Pokemon pokemon;

    public OrderProductDto(Long id, Integer quantity, Integer price, Pokemon pokemon) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.pokemon = pokemon;
    }

    public OrderProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
