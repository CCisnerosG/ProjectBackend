package com.example.ProjectBackend.services;

import com.example.ProjectBackend.entities.Pokemon;
import com.example.ProjectBackend.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> allProducts(){
        List<Pokemon> pokemons = new ArrayList<>();

        pokemonRepository.findAll().forEach(pokemons::add);

        return pokemons;
    }
}
