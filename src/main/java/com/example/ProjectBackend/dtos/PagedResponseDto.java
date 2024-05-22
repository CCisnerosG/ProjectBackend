package com.example.ProjectBackend.dtos;

import com.example.ProjectBackend.entities.Pokemon;
import org.springframework.data.domain.Page;

import java.util.List;

public class PagedResponseDto {
    private List<PokemonDto> pokemons;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public PagedResponseDto(List<PokemonDto> pokemons, Page<Pokemon> pokemonPage) {
        this.pokemons = pokemons;
        this.currentPage = pokemonPage.getNumber();
        this.totalPages = pokemonPage.getTotalPages();
        this.totalItems = pokemonPage.getTotalElements();
    }

    public List<PokemonDto> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonDto> pokemons) {
        this.pokemons = pokemons;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
