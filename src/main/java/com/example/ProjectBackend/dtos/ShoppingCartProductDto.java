package com.example.ProjectBackend.dtos;

public class ShoppingCartProductDto {
    private Long id;
    private ShoppingCartDto shoppingCartDto;
    private PokemonDto pokemonDto;
    private Integer quantity;
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCartDto getShoppingCartDto() {
        return shoppingCartDto;
    }

    public void setShoppingCartDto(ShoppingCartDto shoppingCartDto) {
        this.shoppingCartDto = shoppingCartDto;
    }

    public PokemonDto getPokemonDto() {
        return pokemonDto;
    }

    public void setPokemonDto(PokemonDto pokemonDto) {
        this.pokemonDto = pokemonDto;
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
}
