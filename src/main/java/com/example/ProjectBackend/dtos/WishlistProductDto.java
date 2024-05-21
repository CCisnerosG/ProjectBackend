package com.example.ProjectBackend.dtos;

public class WishlistProductDto {
    private Long id;
    private WishlistDto wishlistDto;
    private PokemonDto pokemonDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WishlistDto getWishlistDto() {
        return wishlistDto;
    }

    public void setWishlistDto(WishlistDto wishlistDto) {
        this.wishlistDto = wishlistDto;
    }

    public PokemonDto getPokemonDto() {
        return pokemonDto;
    }

    public void setPokemonDto(PokemonDto pokemonDto) {
        this.pokemonDto = pokemonDto;
    }
}
